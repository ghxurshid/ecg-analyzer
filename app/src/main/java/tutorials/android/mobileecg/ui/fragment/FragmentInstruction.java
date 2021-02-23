package tutorials.android.mobileecg.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tutorials.android.mobileecg.R;
import tutorials.android.mobileecg.Patient1;
import tutorials.android.mobileecg.Patient2;

public class FragmentInstruction extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_asosiy, container, false);
        final TextView textView = root.findViewById(R.id.instruction);
        final ImageButton button1 = root.findViewById(R.id.bluetooth);
        final ImageButton button2 = root.findViewById(R.id.settings);
        final ImageButton button3 = root.findViewById(R.id.user);
        final ImageButton button4 = root.findViewById(R.id.info);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Patient1.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FragmentSettings.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FragmentAbout.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Patient2.class);
                startActivity(intent);
            }
        });

        return root;
    }
}