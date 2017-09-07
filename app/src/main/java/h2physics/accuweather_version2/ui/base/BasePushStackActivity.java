package h2physics.accuweather_version2.ui.base;

import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import butterknife.BindView;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.ui.customview.Toolbar;

/**
 * Created by YukiNoHara on 8/28/2017.
 */

public abstract class BasePushStackActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    protected Stack<BaseFragment> backStack = new Stack<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Push fragment into container
     * @param fragment
     * @param animated
     */
    public void pushFragment(@IdRes int container, BaseFragment fragment, boolean animated){
        backStack.push(fragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (animated){
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
        }
        ft.replace(container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
        toolbar.reset();
        fragment.initToolbar(container, toolbar);
    }

    /**
     * Push fragment into container
     * @param fragment
     */
    public void pushFragment(@IdRes int container, BaseFragment fragment){
        pushFragment(container, fragment, true);
    }

    /**
     * Pop fragment out container
     * @param animated
     */
    public void popFragment(@IdRes int container, boolean animated){
        if (isFinishing()){
            return;
        }

        if (backStack.size() <= 1){
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            return;
        }

        hideKeyboard();
        BaseFragment fragment = backStack.pop();
        if (fragment != null){
            fragment.onPopFromBackStack();
        }

        fragment = backStack.peek();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (animated){
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
        }
        ft.replace(container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
        toolbar.reset();
        fragment.initToolbar(container, toolbar);
    }

    /**
     * Pop fragment out container
     */
    public void popFragment(@IdRes int container){
        popFragment(container, true);
    }

    /**
     * Replace fragment
     * @param fragment
     */
    public void replaceFragment(@IdRes int container, BaseFragment fragment){
        BaseFragment currentFragment = backStack.pop();
        if (currentFragment != null){
            currentFragment.onPopFromBackStack();
        }
        pushFragment(container, fragment, true);
    }
}
