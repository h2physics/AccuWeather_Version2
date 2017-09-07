package h2physics.accuweather_version2.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.ui.base.BaseSlidingMenuActivity;
import h2physics.accuweather_version2.ui.customview.Toolbar;
import h2physics.accuweather_version2.ui.forecast.ForecastFragment;

public class MainActivity extends BaseSlidingMenuActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        showLoading();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new ForecastFragment());
        ft.commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initToolbar() {

    }
}
