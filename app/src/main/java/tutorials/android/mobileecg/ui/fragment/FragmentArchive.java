package tutorials.android.mobileecg.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tutorials.android.mobileecg.R;
import tutorials.android.mobileecg.Patient1;
import tutorials.android.mobileecg.Patient2;

public class FragmentArchive extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_arxiv, container, false);
        final TextView patsi1 =root.findViewById(R.id.patsiyent1);
        final TextView patsi2 = root.findViewById(R.id.patsiyent2);

        patsi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pat1 = new Intent(getActivity(), Patient1.class);
                startActivity(pat1);
            }
        });
        patsi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pat2 = new Intent(getActivity(), Patient2.class);
                startActivity(pat2);
            }
        });
        return root;
    }
}