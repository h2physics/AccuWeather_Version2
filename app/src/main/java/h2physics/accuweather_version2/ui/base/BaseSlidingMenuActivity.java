package h2physics.accuweather_version2.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.adapter.LeftMenuAdapter;
import h2physics.accuweather_version2.constants.MenuFlags;
import h2physics.accuweather_version2.model.MenuItem;

/**
 * Created by YukiNoHara on 8/30/2017.
 */

public abstract class BaseSlidingMenuActivity extends BasePushStackActivity {
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;

    @BindView(R.id.lv_left_menu)
    public ListView mLvLeftMenu;

    public LeftMenuAdapter mAdapter;
    private List<MenuItem> mListMenuItems;
    int countingExitApp = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_menu_base_layout);
        getLayoutInflater().inflate(getContentLayout(), (ViewGroup) findViewById(R.id.root_container), true);
        ButterKnife.bind(this);
        initView();
        initLeftMenu();
        initToolbar();
        initData();

    }

    @Override
    public boolean hasLeftMenu() {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (backStack.size() <= 1){
            countingExitApp++;
            if (isOpenDrawer()){
                closeDrawer();
            } else {
                if (countingExitApp < 2){
                    Toast.makeText(getApplicationContext(), getString(R.string.message_condition_exit_app), Toast.LENGTH_SHORT).show();
                } else {
                    BaseSlidingMenuActivity.this.finish();
                }
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        countingExitApp = 0;
                    }
                }, 1000);
            }
        } else {
            super.onBackPressed();
        }
    }

    private void initLeftMenu() {
        toolbar.showLeftMenuButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(toggle);
        mListMenuItems = new ArrayList<>();
        mListMenuItems.add(new MenuItem(R.drawable.ic_drawer_list, getString(R.string.drawer_list), MenuFlags.CITY_MANAGER));
        mListMenuItems.add(new MenuItem(R.drawable.ic_drawer_setting, getString(R.string.drawer_setting), MenuFlags.SETTING));
        mAdapter = new LeftMenuAdapter(this);
        mAdapter.clear();
        mAdapter.addAll(mListMenuItems);
        mLvLeftMenu.setAdapter(mAdapter);
        setMenuItemClickListener();
    }

    private void setMenuItemClickListener(){
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (mListMenuItems.get(i).flags){
                    case CITY_MANAGER: {
                        break;
                    }
                    case SETTING:{
                        break;
                    }
                }
                mLvLeftMenu.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                }, 300);
            }
        });
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeDrawer(){
        mDrawerLayout.closeDrawers();
    }

    public boolean isOpenDrawer(){
        return mDrawerLayout.isDrawerOpen(Gravity.LEFT);
    }

    public void disableSwipeLeftToRight(){
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void enableSwipeLeftToRight(){
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
    }


}
