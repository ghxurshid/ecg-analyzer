package tutorials.android.mobileecg.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import tutorials.android.mobileecg.R;
import com.github.barteksc.pdfviewer.PDFView;

public class FragmentAbout extends Fragment {

      public View onCreateView (@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

          View root = inflater.inflate(R.layout.fragment_chiqish, container, false);
          final PDFView pdfView= root.findViewById(R.id.pdfv);
          pdfView.fromAsset("mihaqida.pdf").load();

          return root;
      }
}