package com.c2p.donaryKbd;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    public final static int KBD_NUMERIC = 20001;
    public final static int KBD_ENGLISH = 20002;
    public final static int KBD_HEBREW = 20003;

    public final static int KBD_NUMBER = 10001;
    public final static int KBD_QUERTY = 10002;
    public final static int KBD_ABC = 10003;
    public final static int KBD_HEBREW_ABC = 10004;
    public final static int KBD_HEBREW_QUERTY = 10005;

    private int current_kbd = KBD_NUMBER;
    private int next_kbd = KBD_ABC;

    //public final static int CodeLeft     = 55002;
    public final static int CodeSearch = 15001;
    public final static int CodeMenu = 15002;
    public final static int CodeDeleteRight = 15003;
    public final static int CodeNextAbc = 15004;

    public final static int CodeNextNumeric = 16001;
    public final static int CodeNextHebrew = 16002;

    public final static int HCodeNextNumeric = 17001;
    public final static int HCodeNextAbc = 17002;

    public Integer KBDHorizontalArray[]={ KBD_ABC, KBD_NUMBER, KBD_QUERTY, KBD_HEBREW_ABC, KBD_HEBREW_QUERTY};
    public Integer KBDHorizontalLayoutArray[]={R.xml.abckbd, R.xml.numkbd,  R.xml.qwerty,  R.xml.hebrew_abc, R.xml.hebrew_qwerty};
    public Integer current_hz_kbd_position=0;


    public Integer KBDVerticalArray[]={ KBD_ABC, KBD_NUMBER, KBD_QUERTY, KBD_HEBREW_ABC, KBD_HEBREW_QUERTY};
    public Integer KBDVerticalLayoutArray[]={R.xml.abckbd, R.xml.numkbd,  R.xml.qwerty,  R.xml.hebrew_abc, R.xml.hebrew_qwerty};
    public Integer current_vtc_kbd_position=0;


    public Integer currentKBDTypeArr[]={ KBD_ENGLISH, KBD_NUMERIC, KBD_HEBREW};
    public Integer currentKBDType=0;



    @Override
    public View onCreateInputView() {

        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);

        keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        InputConnection ic = getCurrentInputConnection();
        //int start = ic.getSelectionStart();
        playClick(primaryCode);
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;

            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;

            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;

            case CodeDeleteRight:
                ic.deleteSurroundingText(0, 1);
                break;

            case CodeNextAbc:
                //Toast.makeText(getApplicationContext(),"Next",Toast.LENGTH_SHORT).show();
                current_hz_kbd_position=0;
                keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

                /*keyboard = new Keyboard(this, R.xml.abckbd);
                current_kbd = KBD_ABC;
                next_kbd = KBD_QUERTY;*/
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case CodeMenu:
                //Toast.makeText(getApplicationContext(), "Menu Pressed", Toast.LENGTH_SHORT).show();
                break;

            case CodeSearch:
                //Toast.makeText(getApplicationContext(), "Search Pressed", Toast.LENGTH_SHORT).show();
                break;

            case CodeNextNumeric:
                current_hz_kbd_position=1;
                keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

                /*keyboard = new Keyboard(this, R.xml.numkbd);
                current_kbd = KBD_NUMBER;
                next_kbd = KBD_ABC;*/
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case CodeNextHebrew:
                //Toast.makeText(getApplicationContext(), "Under development", Toast.LENGTH_SHORT).show();
                current_hz_kbd_position=3;
                keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case HCodeNextNumeric:
                current_hz_kbd_position=1;
                keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

                /*keyboard = new Keyboard(this, R.xml.numkbd);
                current_kbd = KBD_NUMBER;
                next_kbd = KBD_ABC;*/
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            case HCodeNextAbc:
                //Toast.makeText(getApplicationContext(), "Under development", Toast.LENGTH_SHORT).show();
                current_hz_kbd_position=0;
                keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

                /*keyboard = new Keyboard(this, R.xml.abckbd);
                current_kbd = KBD_ABC;
                next_kbd = KBD_QUERTY;*/
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                break;

            default:
                char code = (char) primaryCode;
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code), 1);
        }

    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeUp() {
        //Toast.makeText(getApplicationContext(),"Up",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void swipeDown() {
        //Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void swipeLeft() {

        if(current_hz_kbd_position < KBDHorizontalLayoutArray.length -1 ) {
            current_hz_kbd_position = current_hz_kbd_position + 1;
        }else{
            current_hz_kbd_position=0;
        }
        keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
    }

    @Override
    public void swipeRight() {

        if(current_hz_kbd_position > 0 ) {
            current_hz_kbd_position = current_hz_kbd_position - 1;
        }else{
            current_hz_kbd_position=KBDHorizontalLayoutArray.length - 1;
        }
        keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);

        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
    }



    private void playClick(int keyCode) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }


    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {

        switch (attribute.inputType & EditorInfo.TYPE_MASK_CLASS) {

            case EditorInfo.TYPE_CLASS_NUMBER:
                    current_hz_kbd_position=1;
                    keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    break;

            case EditorInfo.TYPE_CLASS_PHONE:
                    current_hz_kbd_position=1;
                    keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);
                    next_kbd = KBD_ABC;
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    break;

            case EditorInfo.TYPE_CLASS_TEXT:
                    current_hz_kbd_position=0;
                    keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    break;

            default:
                    current_hz_kbd_position=0;
                    keyboard = new Keyboard(this, KBDHorizontalLayoutArray[current_hz_kbd_position]);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
        }
    }



}