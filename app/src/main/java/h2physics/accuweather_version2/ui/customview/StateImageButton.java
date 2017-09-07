package h2physics.accuweather_version2.ui.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by YukiNoHara on 8/28/2017.
 */

public class StateImageButton extends ImageView{
    public StateImageButton(Context context) {
        super(context);
    }

    public StateImageButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateImageButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (isEnabled()){
            setState(pressed);
        }
        super.setPressed(pressed);
    }

    @Override
    public void setEnabled(boolean enabled) {
        setState(!enabled);
        super.setEnabled(enabled);
    }

    private void setState(boolean pressed){
        if (pressed){
            setAlpha(0.6f);
        } else {
            setAlpha(1.0f);
        }
    }
}
