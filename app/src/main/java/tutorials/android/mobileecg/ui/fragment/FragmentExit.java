package tutorials.android.mobileecg.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class FragmentExit extends Fragment {

    public void onCreate(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState){
            // TODO Auto-generated method stub
            getActivity().finish();
            System.exit(0);
        }
    }
