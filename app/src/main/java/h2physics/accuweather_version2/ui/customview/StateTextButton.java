package h2physics.accuweather_version2.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by YukiNoHara on 8/29/2017.
 */

public class StateTextButton extends Button{
    public StateTextButton(Context context) {
        super(context);
    }

    public StateTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
