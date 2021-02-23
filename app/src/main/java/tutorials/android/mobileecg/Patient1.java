package tutorials.android.mobileecg;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Patient1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat1);

//        // Get a layout declared in "activity_main.xml" by id
//        ConstraintLayout chartLayout = (ConstraintLayout) findViewById(R.id.pat1);
//        final SciChartSurface surface = new SciChartSurface(this);
//
//        // Add the SciChartSurface to the layout
//        chartLayout.addView(surface);
//
//        // Initialize the SciChartBuilder
//        SciChartBuilder.init(this);
//
//        // Obtain the SciChartBuilder instance
//        final SciChartBuilder sciChartBuilder = SciChartBuilder.instance();
//
//        // Create a numeric X axis
//        final IAxis xAxis = sciChartBuilder.newNumericAxis()
//                .withAxisTitle("X Axis Title")
//                .withDrawLabels(false)
//                .withDrawMinorTicks(true)
//                .withDrawMajorTicks(true)
//                .withDrawMajorGridLines(true)
//                .withDrawMinorGridLines(false)
//                .withDrawMajorBands(false)
//                .build();
//
//        // Create a numeric Y axis
//        final IAxis yAxis = sciChartBuilder.newNumericAxis()
//                .withAxisTitle("Y Axis Title")
//                .withVisibleRange(9000, 12000)
//                .withDrawMinorTicks(true)
//                .withDrawMajorTicks(true)
//                .withDrawMajorGridLines(true)
//                .withDrawMinorGridLines(false)
//                .withDrawMajorBands(false)
//                .build();
//
//        // Create interactivity modifiers
//        ModifierGroup chartModifiers = sciChartBuilder.newModifierGroup()
//                .withPinchZoomModifier().withReceiveHandledEvents(true).build()
//                .withZoomPanModifier().withReceiveHandledEvents(true).build()
//                .withZoomExtentsModifier().withReceiveHandledEvents(false).build()
//                .withXAxisDragModifier().withReceiveHandledEvents(false)
//                .withDragMode(AxisDragModifierBase.AxisDragMode.Scale).withClipModeX(ClipMode.None).build()
//                .withYAxisDragModifier().withReceiveHandledEvents(false)
//                .withDragMode(AxisDragModifierBase.AxisDragMode.Pan).build()
//                .build();
//
//
//
//        final DoubleSeries data = DataManager.getInstance().getDampedSinewave(1.0, 0.01, 1000, 10);
//        final DoubleSeries moreData = DataManager.getInstance().getDampedSinewave(1.0, 0.005, 1000, 12);
//
//        final XyyDataSeries<Double, Double> dataSeries = sciChartBuilder.newXyyDataSeries(Double.class, Double.class).build();
//        dataSeries.append(data.xValues, data.yValues, moreData.yValues);
//
//        final FastBandRenderableSeries rSeries = sciChartBuilder.newBandSeries()
//                .withDataSeries(dataSeries)
//                .withFillColor(0x33279B27).withFillY1Color(0x33FF1919)
//                .withStrokeStyle(0xFFFF1919, 1f, true).withStrokeY1Style(0xFF279B27, 1f, true)
//                .build();
//
//        UpdateSuspender.using(surface, new Runnable() {
//            @Override
//            public void run() {
//                Collections.addAll(surface.getXAxes(), xAxis);
//                Collections.addAll(surface.getYAxes(), yAxis);
//                Collections.addAll(surface.getRenderableSeries(), rSeries);
//                Collections.addAll(surface.getChartModifiers(), sciChartBuilder.newModifierGroupWithDefaultModifiers().build());
//
//                sciChartBuilder.newAnimator(rSeries).withScaleTransformation().withInterpolator(new EasingInterpolator(Ease.ELASTIC_OUT)).withDuration(3000).withStartDelay(350).start();
//            }
//        });
//
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//
//        runnable.run();
//
//        surface.zoomExtents();
    }
}