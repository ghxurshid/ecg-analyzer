package tutorials.android.mobileecg;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.content.res.Configuration;

import com.scichart.core.common.Action1;
import com.scichart.charting.visuals.SciChartSurface;
import com.scichart.charting.model.ChartModifierCollection;
import com.scichart.charting.modifiers.ZoomExtentsModifier;

import com.scichart.examples.utils.SideMenuHelper;
import com.scichart.examples.utils.widgetgeneration.Widget;
import com.scichart.examples.utils.widgetgeneration.ImageViewWidget;
import com.scichart.examples.components.CustomDrawerLayout;
import com.scichart.examples.components.modifiers.CustomRotateChartModifier;
import com.scichart.examples.components.modifiers.FlipAxesCoordsChartModifier;
import com.scichart.examples.fragments.base.ExampleBaseFragment;
import com.scichart.extensions.builders.SciChartBuilder;
import com.scichart.extensions3d.builders.SciChart3DBuilder;

import java.util.ArrayList;
import java.util.List;

public class Patient2 extends AppCompatActivity {
    private ExampleBaseFragment exampleFragment;

    private CustomDrawerLayout drawerLayout;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle bundle) {
        SciChartBuilder.init(this);
        SciChart3DBuilder.init(this);

        super.onCreate(bundle);
        setContentView(R.layout.pat2);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setUpDrawerAndToolbar();

        drawerLayout.setViewsClickableAction(new Action1<Boolean>() {
            @Override
            public void execute(Boolean isEnabled) {
                settingsButton.setEnabled(isEnabled);
            }
        });

        exampleFragment = (ExampleBaseFragment) getFragmentManager().findFragmentById(R.id.fragment_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SciChartBuilder.dispose();
        SciChart3DBuilder.dispose();
    }

    @Override
    protected void onPause() {
        super.onPause();

        drawerLayout.clearMenuItems();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        generateSideMenuItems();
    }

    private void setUpDrawerAndToolbar() {
        drawerLayout = (CustomDrawerLayout) findViewById(R.id.drawer_layout_example);
    }

    private void generateSideMenuItems() {
        List<Widget> exampleToolbarItems = exampleFragment.getToolbarItems();
        if (exampleToolbarItems.size() > 0) {
            addWidgetsToMenuList(exampleToolbarItems);
        }

        boolean showDefaultModifiers = exampleFragment.showDefaultModifiersInToolbar();
        if (showDefaultModifiers) {
            if (exampleToolbarItems.size() > 0) {
                drawerLayout.addMenuSeparator();
            }
            addWidgetsToMenuList(getDefaultModifiers());
        }

        if (exampleToolbarItems.size() == 0 && !showDefaultModifiers) {
            settingsButton.setVisibility(View.INVISIBLE);
        }
    }

    private void addWidgetsToMenuList(List<Widget> widgets) {
        for (int i = 0, size = widgets.size(); i < size; i++) {
            Widget widget = widgets.get(i);
            drawerLayout.addMenuItem(widget);
        }
    }

    private List<Widget> getDefaultModifiers() {
        List<Widget> widgets = new ArrayList<>();

        final SciChartSurface surface = (SciChartSurface) findViewById(R.id.chart);
        if(surface != null) {
            final ChartModifierCollection chartModifiers = surface.getChartModifiers();

            widgets.add(attachModifierAndCreateWidget(ZoomExtentsModifier.class, R.drawable.example_toolbar_zoom_extents, surface, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    surface.animateZoomExtents(ZoomExtentsModifier.DEFAULT_ANIMATION_DURATION);
                }
            }));
            widgets.add(attachModifierAndCreateWidget(FlipAxesCoordsChartModifier.class, R.drawable.example_toolbar_flip_x, surface, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FlipAxesCoordsChartModifier) SideMenuHelper.getModifier(FlipAxesCoordsChartModifier.class, chartModifiers)).flipXAxes();
                }
            }));
            widgets.add(attachModifierAndCreateWidget(FlipAxesCoordsChartModifier.class, R.drawable.example_toolbar_flip_y, surface, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FlipAxesCoordsChartModifier) SideMenuHelper.getModifier(FlipAxesCoordsChartModifier.class, chartModifiers)).flipYAxes();
                }
            }));
            widgets.add(attachModifierAndCreateWidget(CustomRotateChartModifier.class, R.drawable.example_toolbar_rotate, surface, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CustomRotateChartModifier) SideMenuHelper.getModifier(CustomRotateChartModifier.class, chartModifiers)).rotateChart();
                }
            }));
        }

        return widgets;
    }

    private Widget attachModifierAndCreateWidget(Class modifierType, @DrawableRes int drawableRes, SciChartSurface surface, View.OnClickListener listener) {
        if (modifierType != null) {
            SideMenuHelper.attachModifierToSurface(modifierType, surface);
        }

        return new ImageViewWidget.Builder().setId(drawableRes).setListener(listener).build();
    }
}