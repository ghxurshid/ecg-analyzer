package com.scichart.examples.fragments.showcase.vitalSignsMonitor;

import android.util.Log;
import android.content.Context;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothAdapter;

import com.scichart.core.model.DoubleValues;
import com.scichart.data.model.DoubleRange;
import com.scichart.examples.fragments.base.DataProviderBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Predicate;

public class BluetoothVitalSignsDataProvider extends DataProviderBase<VitalSignsData> {
    //1. Heart rate or pulse rate (ECG HR)
    //2. Blood Pressure (NI BP)
    //3. Blood Volume (SV ml)
    //4. Blood Oxygenation (SPo2)
    private static final String ECG_TRACES = "data/EcgTraces.csv";
    private static final float SAMPLE_RATE = 800f;
    private static final int REQUEST_ENABLE_BT = 1;

    private final int currentIndex = 0;
    private int totalIndex = 0;
    private boolean isATrace = false;

    private final DoubleValues xValues = new DoubleValues();
    private final DoubleValues ecgHeartRate= new DoubleValues();
    private final DoubleValues bloodPressure = new DoubleValues();
    private final DoubleValues bloodVolume = new DoubleValues();
    private final DoubleValues bloodOxygenation = new DoubleValues();

    BluetoothAdapter bt_adapter = null;
    BluetoothSocket  bt_socket  = null;
    BluetoothDevice  bt_device  = null;

    InputStream      inputStream  = null;
    OutputStream     outputStream = null;

    // SPP UUID сервиса
    private static final UUID sppUuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // MAC-адрес Bluetooth модуля
    private static final String address = "00:BA:90:00:16:95";


    public BluetoothVitalSignsDataProvider(Context context) {
        super(1250L, TimeUnit.MICROSECONDS);

        bt_adapter = BluetoothAdapter.getDefaultAdapter();
        if (bt_adapter != null && bt_adapter.isEnabled()) {
            bt_device = bt_adapter.getRemoteDevice(address);

            try {
                bt_socket = bt_device.createRfcommSocketToServiceRecord(sppUuid);
                bt_socket.connect();
                if (bt_socket.isConnected()) {
                    inputStream = bt_socket.getInputStream();
                    outputStream = bt_socket.getOutputStream();

                    byte[] strt = {'s', 't', 'r', 't'};
                    outputStream.write(strt);
                } else {
                    Log.e("ECG", "Can't connect to address!");
                }
            } catch (Exception e){
                Log.e("ECG", "Failed to get SPP socket", e);
            }
        } else {
            Log.e("ECG", "Bluetooth adapter is invalid!");
        }
    }

    double map(byte val) {
        return (double) val / (double) 255;
    }

    @Override
    protected VitalSignsData onNext() {

        totalIndex++;

        if(totalIndex % 8000 == 0) {
            isATrace = !isATrace;
        }

        try {
            if (inputStream != null && inputStream.available() > 0) {
                final int available = inputStream.available();
                byte[] buffer = new byte[available];
                final int actually =  inputStream.read(buffer, 0, available);

                final double time = (double)((((int) buffer[0]) << 8) | ((int) buffer[1])) / 1000.0;

                final double ecgHeartRate = actually > 2 ? map( buffer[2] ) : 0.0;
                final double bloodPressure = actually > 3 ? map( buffer[3] ) : 0.0;
                final double bloodVolume = actually > 4 ? map( buffer[4] ) : 0.0;
                final double bloodOxygenation = actually > 5 ? map( buffer[5] ) : 0.0;

                Log.d("ECG1", String.format("actually[%1$], time[%2$], ecgHeartRate[%3$], bloodPressure[%4$], bloodVolume[%5$], bloodOxygenation[%6$]", actually, time, ecgHeartRate, bloodPressure, bloodVolume, bloodOxygenation));

                return new VitalSignsData(time, ecgHeartRate, bloodPressure, bloodVolume, bloodOxygenation, isATrace, true);
            }
        } catch (IOException e) {
            Log.e("ECG", "Failed to read from socket", e);
        }

        return new VitalSignsData(0, 0.0, 0.0, 0.0, 0.0, false, false);
    }

    @Override
    protected boolean canCantinue(VitalSignsData item) {
        return item.isValid;
    }


    public final DoubleRange getEcgHeartRateRange() {
        return getMinMaxRange(ecgHeartRate);
    }

    public final DoubleRange getBloodPressureRange() {
        return getMinMaxRange(bloodPressure);
    }

    public final DoubleRange getBloodVolumeRange() {
        return getMinMaxRange(bloodVolume);
    }

    public final DoubleRange getBloodOxygenationRange() {
        return getMinMaxRange(bloodOxygenation);
    }

    private static DoubleRange getMinMaxRange(DoubleValues values) {
        final DoubleRange range = new DoubleRange();
        //SciListUtil.instance().minMax(values.getItemsArray(), 0, values.size(), range);
        range.setMax(1.);
        range.setMin(-1.);

        range.growBy(0.1, 0.1);
        return range;
    }
}
