package tutorials.android.mobileecg;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            com.scichart.charting.visuals.SciChartSurface.setRuntimeLicenseKey("Rr1UZ/76oWr1GN7dElEmIuGk444He4WYv+Oa1Jf8goDZsUanbniZenm/Pyaev+xccfka+Lgy6cApu2ncmt6EgadFmrFFr+Ax6lrfNLuV9hGqyp8qZlXa9n6fF/esPyqpqA3oic+2LmgqVL6h5OWcgIm45nHSBmyISdHBHUkV+YcPSWvUJWCIxMYLlvxqfxy5qxWdOQdmuyCnCzQxsAy3YagOdlB1mO12sQ4G8NAReKkyAnEih3dQcOrYsUL7O5uE0cwB2LxhqA/cjMpzrqAthecYuGJrLiSi9u8PC5WUcwNO6f3CKTpAzHfsXvW1t70fnYz7zOOnBWFqF2ek1O9UaoxtrQQZlF/oCEJVSQzaI+ho8wcRgfseHku0wAhAAlNVBa2QudmxQkLd4WngIXQF4Hw1wPGnwB9Uf+wkgSlqkPGCY8WtvE7K9Qi+iH/rtSEbmo//+F4xPy9TopD9+gXaUvyX2W58t6dZjwjFCVRCAEKGRweeIrZUHZ+NTcJSKwvUQV8TkLfDANwTqvbt2C/d8Hw=");
        } catch (Exception e) {
            Log.e("SciChart", "Error when setting the license", e);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Telefon qilinmoqda", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_yoriqnoma, R.id.nav_programmaHaqida,R.id.nav_arxiv, R.id.nav_sozlamalar,  R.id.nav_patsiyentlar, R.id.nav_chiqish, R.id.nav_tashxis )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
