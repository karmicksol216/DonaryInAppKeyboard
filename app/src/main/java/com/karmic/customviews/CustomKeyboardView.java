package com.karmic.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;

import com.karmic.simplekeyboard.R;

import java.util.List;

public class CustomKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    Context context;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }


    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }
    /*void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final Keyboard keyboard = (Keyboard)getKeyboard();
        keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }*/

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //getKeyboard().s

        List<Key> keys = getKeyboard().getKeys();
        for (Key key : keys) {
            Log.e("KEY", "Drawing key with code " + key.codes[0]);
            if (key.codes[0] == 55002) {
                //-- for back arrow in numeric keyboard
                //Log.e("KEY", "Drawing key with code " + key.codes[0]);
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_silver_border);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

            } else {
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.circle_cyan36);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }

            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);

            //if(key.codes[0]>=97 && key.codes[0]<=122)

            if(key.codes[0]>=97 && key.codes[0]<=122)
                paint.setTextSize(42);
            else
                paint.setTextSize(62);

            paint.setColor(Color.YELLOW);

            if (key.label != null) {
                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                        key.y + (key.height / 2)+20, paint);
            } else {
                int padding=30;
                key.icon.setBounds(key.x+padding, key.y+padding, key.x + key.width-padding, key.y + key.height-padding);
                key.icon.draw(canvas);
            }
        }
    }
}
