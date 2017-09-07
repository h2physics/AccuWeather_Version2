package h2physics.accuweather_version2.ui.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.ui.base.BaseActivity;
import h2physics.accuweather_version2.ui.customview.Toolbar;

public class DemoActivity extends BaseActivity {
    private static final String LOG_TAG = DemoActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_demo;
    }

    @Override
    public void initView() {
        showLoading();
    }

    @Override
    public void initData() {
        toolbar.show();
        toolbar.showLeftBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toolbar.setTitle("Demo Activity");
        toolbar.addRightButton("Set", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toolbar.addRightButton(R.drawable.ic_drawer_setting, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initToolbar() {

    }
}
