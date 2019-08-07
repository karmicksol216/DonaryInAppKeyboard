package com.karmic.customviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.karmic.simplekeyboard.R;

import java.util.List;

public class CustomKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    Context context;
    //private Activity mHostActivity;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //mHostActivity= (Activity) context;
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


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*View focusCurrent = context.getWindow().getCurrentFocus();
        if( focusCurrent==null || focusCurrent.getClass()!= EditText.class ) return;
        EditText edittext = (EditText) focusCurrent;
        Editable editable = edittext.getText();
        int start = edittext.getSelectionStart();*/

        List<Key> keys = getKeyboard().getKeys();

        for (Key key : keys) {

            Log.e("KEY", "Drawing key with code " + key.codes[0]);

            if (key.codes[0] == 0) {
                //-- for space around numeric keyboard, padding
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.circle_black_key);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

            } else if (key.codes[0] == 16001 || key.codes[0] == 16002 || key.codes[0] == 16003  || key.codes[0] == 32 ) {
                //-- for back arrow in numeric keyboard
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_silver_border);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);

            }else {

                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.circle_cyan36);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
            }

            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);

            //if(key.codes[0]>=97 && key.codes[0]<=122)

            if((key.codes[0]>=97 && key.codes[0]<=122) || key.codes[0] == 64 || key.codes[0] == 63)
            {
                paint.setTextSize(42);// querty
            }
            else if((key.codes[0]>=48 && key.codes[0]<=57) || key.codes[0] == 15004 )
            {
                paint.setTextSize(62);//numeric
            }
            else if((key.codes[0]>=65 && key.codes[0]<=90) || key.codes[0] == 32 )
            {
                paint.setTextSize(62);//ABC
            }
            else
            {
                paint.setTextSize(62);
            }

            paint.setColor(Color.YELLOW);

            if (key.label != null) {
                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                        key.y + (key.height / 2)+20, paint);
            } else {

                int padding=0;

                //--for ABC
                if (key.codes[0] == 16001 || key.codes[0] == 16002 || key.codes[0] == 16003  ) {
                    padding=10;
                }

                //--for Numeric
                if (key.codes[0] == 15001 || key.codes[0] == 15002 || key.codes[0] == 15003 || key.codes[0] == 36|| key.codes[0] == -5 ) {
                    padding=30;
                }

                key.icon.setBounds(key.x+padding, key.y+padding, key.x + key.width-padding, key.y + key.height-padding);
                key.icon.draw(canvas);
            }
        }
    }
}
