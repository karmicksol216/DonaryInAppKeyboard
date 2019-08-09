package com.c2p.donaryKbd;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        imeManager.showInputMethodPicker();
        imeManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


    /*@Override
    public void onBackPressed() {
    // super.onBackPressed();
    // Not calling **super**, disables back button in current screen.
        Toast.makeText(getApplicationContext(), "Back has been disabled in this project.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       //Toast.makeText(getApplicationContext(), "press" + keyCode, Toast.LENGTH_LONG).show();
       if(keyCode==KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_SOFT_LEFT) {
           return false;
       }
        // Disable back button..............
        return true;
    }*/

}
