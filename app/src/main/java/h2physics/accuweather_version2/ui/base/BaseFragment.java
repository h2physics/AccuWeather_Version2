package h2physics.accuweather_version2.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import butterknife.ButterKnife;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.ui.customview.Toolbar;

/**
 * Created by YukiNoHara on 8/28/2017.
 */

public abstract class BaseFragment extends Fragment{
    private static final String LOG_TAG = "BaseFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getContentLayout(), container, false);
        ButterKnife.bind(this, root);
        initView(root);
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void onPopFromBackStack(){

    }

    public abstract int getContentLayout();

    public abstract void initView(View view);

    public abstract void initData();

    public void initToolbar(@IdRes final int container, Toolbar toolbar){
        toolbar.showLeftBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BasePushStackActivity) getActivity()).popFragment(container);
            }
        });
    }

    public void runOnUiThread(Runnable runnable){
        if (getActivity() != null){
            getActivity().runOnUiThread(runnable);
        }
    }

    /**
     * Show keyboard in fragment
     * @param target
     */
    public void showKeyboard(View target) {
        if (target == null) {
            return;
        }
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(target,
                InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Hide keyboard
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * Show loading in fragment
     * @param loadingMessage
     */
    public void showLoading(final String loadingMessage, final View root){
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (root == null){
                        Log.e(LOG_TAG, "Root view is null");
                        return;
                    }
                    final View v = root.findViewById(R.id.loading_view);

                    if (v == null){
                        Log.e(LOG_TAG, "Loading view is null");
                        return;
                    }

                    TextView mess = root.findViewById(R.id.loading_title);
                    if (TextUtils.isEmpty(loadingMessage)){
                        mess.setVisibility(View.GONE);
                    } else {
                        mess.setText(loadingMessage);
                        mess.setVisibility(View.VISIBLE);
                    }
                    try{
                        if (isAdded()){
                            Log.e(LOG_TAG, "Loading view is visible");
                            v.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Show loading in fragment
     */
    protected void showLoading(View root){
        showLoading(null, root);
    }

    /**
     * Show loading in fragment
     * @param resId
     */
    public void showLoading(@StringRes int resId, View root){
        showLoading(getString(resId), root);
    }

    /**
     * Hide loading
     */
    public void hideLoading(){
        try {
            if (getView() == null){
                return;
            }
            final View v = getView().findViewById(R.id.loading_view);
            if (v == null){
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (isAdded()){
                            v.setVisibility(View.GONE);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Define content alert in fragment
     * @param title
     * @param mess
     */
    public void showAlert(final String title, final String mess){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (getActivity() != null){
                        new AlertDialog.Builder(getActivity())
                                .setTitle(title)
                                .setMessage(mess)
                                .setNegativeButton(android.R.string.ok, null)
                                .create()
                                .show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define content alert in fragment
     * @param resTitleId
     * @param resMessId
     */
    public void showAlert(@StringRes final int resTitleId, @StringRes final int resMessId){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (getActivity() != null){
                        new AlertDialog.Builder(getActivity())
                                .setTitle(getString(resTitleId))
                                .setMessage(getString(resMessId))
                                .setNegativeButton(android.R.string.ok, null)
                                .create()
                                .show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define content alert in fragment
     * @param title
     * @param mess
     * @param listener
     */
    public void showAlert(final String title, final String mess, final BaseActivity.AlertListener listener){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (getActivity() != null){
                        new AlertDialog.Builder(getActivity())
                                .setTitle(title)
                                .setMessage(mess)
                                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (listener != null){
                                            listener.yesOnClick();
                                        }
                                    }
                                })
                                .create()
                                .show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define content alert in fragment
     * @param title
     * @param mess
     * @param yesString
     * @param noString
     * @param listener
     */
    public void showAlert(final String title, final String mess, final String yesString, final String noString, final BaseActivity.AlertListener listener){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (getActivity() != null){
                        new AlertDialog.Builder(getActivity())
                                .setTitle(title)
                                .setMessage(mess)
                                .setPositiveButton(yesString, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (listener != null){
                                            listener.yesOnClick();
                                        }
                                    }
                                })
                                .setNegativeButton(noString, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    protected boolean isCurrentFragmentVisible(Fragment fragment){
        if (fragment == null){
            return false;
        }
        if (fragment.isAdded() || fragment.isVisible() || fragment.isResumed()){
            return true;
        }
        return false;
    }
}
