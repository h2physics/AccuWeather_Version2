package h2physics.accuweather_version2.ui.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.ui.base.BaseSlidingMenuActivity;
import h2physics.accuweather_version2.ui.customview.Toolbar;

public class DemoSlidingMenuActivity extends BaseSlidingMenuActivity {
    private static final String LOG_TAG = DemoSlidingMenuActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getContentLayout() {
        return R.layout.activity_demo_sliding_menu;
    }

    @Override
    public void initView() {
        showLoading();
    }

    @Override
    public void initData() {
        toolbar.show();
    }

    @Override
    public void initToolbar() {

    }
}
