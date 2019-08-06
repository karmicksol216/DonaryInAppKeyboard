package com.karmic.simplekeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

public class SimpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    public final static int KBD_NUMBER     = 10001;
    public final static int KBD_QUERTY     = 10002;

    private int current_kbd=KBD_NUMBER;

    public final static int CodeLeft     = 55002;
    public final static int CodeSearch     = 15001;
    public final static int CodeMenu     = 15002;


    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);

        if(current_kbd==KBD_NUMBER) {
            keyboard = new Keyboard(this, R.xml.numkbd);
            current_kbd=KBD_QUERTY;
        }
        else if(current_kbd==KBD_QUERTY) {
            keyboard = new Keyboard(this, R.xml.qwerty);
            current_kbd=KBD_NUMBER;
        }

        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        //int start = ic.getSelectionStart();
        playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
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
            case CodeLeft:
                //ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                ic.setSelection(1,0);
                break;

            case CodeMenu:
                Toast.makeText(getApplicationContext(),"Menu Pressed",Toast.LENGTH_SHORT).show();
                break;
            case CodeSearch:
                Toast.makeText(getApplicationContext(),"Search Pressed",Toast.LENGTH_SHORT).show();
                break;

            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
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
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {

        if(current_kbd==KBD_NUMBER) {
            keyboard = new Keyboard(this, R.xml.numkbd);
            current_kbd=KBD_QUERTY;
        }
        else if(current_kbd==KBD_QUERTY) {
            keyboard = new Keyboard(this, R.xml.qwerty);
            current_kbd=KBD_NUMBER;
        }

        //keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
    }

    @Override
    public void swipeRight() {

        if(current_kbd==KBD_NUMBER) {
            keyboard = new Keyboard(this, R.xml.numkbd);
            current_kbd=KBD_QUERTY;
        }
        else if(current_kbd==KBD_QUERTY) {
            keyboard = new Keyboard(this, R.xml.qwerty);
            current_kbd=KBD_NUMBER;
        }


        //keyboard = new Keyboard(this, R.xml.numkbd);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
    }

    @Override
    public void swipeUp() {
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
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
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }


}