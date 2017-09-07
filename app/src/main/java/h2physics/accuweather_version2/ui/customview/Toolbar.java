package h2physics.accuweather_version2.ui.customview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import h2physics.accuweather_version2.R;

/**
 * Created by YukiNoHara on 8/28/2017.
 */

public class Toolbar extends RelativeLayout{
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.btn_left)
    ImageView mBtnLeft;

    @BindView(R.id.ll_right_container)
    LinearLayout mRightContainer;

    @BindView(R.id.contentView)
    View mContentView;

    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.toolbar_view, this, true);
        ButterKnife.bind(this);
        reset();
    }

    public Toolbar reset(){
        show();
        mRightContainer.removeAllViews();
        mBtnLeft.setVisibility(INVISIBLE);
        setTitle("");
        return this;
    }

    public Toolbar show(){
        setVisibility(VISIBLE);
        return this;
    }

    public Toolbar hide(){
        setVisibility(INVISIBLE);
        return this;
    }

    public Toolbar setTitle(String title){
        mTvTitle.setText(title);
        return this;
    }

    public Toolbar showLeftBackButton(OnClickListener onClickListener){
        mBtnLeft.setImageResource(R.drawable.ic_back);
        mBtnLeft.setVisibility(VISIBLE);
        mBtnLeft.setOnClickListener(onClickListener);
        return this;
    }

    public Toolbar hideLeftBackButton(){
        mBtnLeft.setVisibility(INVISIBLE);
        return this;
    }

    public Toolbar showLeftMenuButton(OnClickListener onClickListener){
        mBtnLeft.setImageResource(R.drawable.ic_drawer_menu);
        mBtnLeft.setVisibility(VISIBLE);
        mBtnLeft.setOnClickListener(onClickListener);
        mTvTitle.setTextColor(getResources().getColor(R.color.colorWhite));
        return this;
    }

    public Toolbar addRightButton(String text, OnClickListener onClickListener){
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.toolbar_text_button, mRightContainer, false);
        textView.setText(text);
        textView.setOnClickListener(onClickListener);
        textView.setAllCaps(false);
        mRightContainer.addView(textView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public Toolbar addRightButton(@DrawableRes int resId, OnClickListener onClickListener){
        ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.toolbar_image_button, mRightContainer, false);
        imageView.setImageResource(resId);
        imageView.setOnClickListener(onClickListener);
        mRightContainer.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public Toolbar removeAllRightButton(){
        show();
        mRightContainer.removeAllViews();
        return this;
    }
}
