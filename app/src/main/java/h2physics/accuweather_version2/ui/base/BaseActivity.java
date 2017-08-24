package h2physics.accuweather_version2.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import h2physics.accuweather_version2.R;

/**
 * Created by YukiNoHara on 8/24/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String LOG_TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    abstract public int getContentLayout();

    abstract public void initView();

    abstract public void initData();

    abstract public void initToolbar();

    public void showKeyboard(View view){
        if (view == null){
            return;
        }
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null){
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showLoading(final String loadingMessage){
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final View v = findViewById(R.id.loading_view);

                    if (v == null){
                        Log.d(LOG_TAG, "Loading view is null");
                        return;
                    }

                    TextView mess = (TextView) findViewById(R.id.loading_title);
                    if (TextUtils.isEmpty(loadingMessage)){
                        mess.setVisibility(View.GONE);
                    } else {
                        mess.setText(loadingMessage);
                        mess.setVisibility(View.VISIBLE);
                    }
                    if (!isFinishing()){
                        try{
                            v.setVisibility(View.VISIBLE);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void showLoading(){
        showLoading(null);
    }

    public void showLoading(@StringRes int resId){
        showLoading(getString(resId));
    }

    public void hideLoading(){
        final View v = findViewById(R.id.loading_view);
        if (v == null){
            return;
        }
        if (!isFinishing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    v.setVisibility(View.GONE);
                }
            });
        }
    }

    public void startActivityWithAnimation(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void finishActivityWithAnimation(){
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void showAlert(final String title, final String mess){
        if (!isFinishing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        new AlertDialog.Builder(BaseActivity.this)
                                .setTitle(title)
                                .setMessage(mess)
                                .setNegativeButton(android.R.string.ok, null)
                                .create()
                                .show();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showAlert(@StringRes final int resTitleId, @StringRes final int resMessId){
        if (!isFinishing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        new AlertDialog.Builder(BaseActivity.this)
                                .setTitle(getString(resTitleId))
                                .setMessage(getString(resMessId))
                                .setNegativeButton(android.R.string.ok, null)
                                .create()
                                .show();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showAlert(final String title, final String mess, final AlertListener listener){
        if (!isFinishing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        new AlertDialog.Builder(BaseActivity.this)
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
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showAlert(final String title, final String mess, final String yesString, final String noString, final AlertListener listener){
        if (!isFinishing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        new AlertDialog.Builder(BaseActivity.this)
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
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public interface AlertListener{
        public void yesOnClick();
    }

    public void postDelayed(Runnable runnable, long delay){
        new Handler().postDelayed(runnable, delay);
    }
}
