package com.karmic.simplekeyboard;

import android.content.ContentResolver;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        //imeManager.showInputMethodPicker();

        //ContentResolver contentResolver = getContentResolver();
        //changekeyboard("keyboard",contentResolver);
    }


    protected static void changekeyboard(String keyboardID, ContentResolver contentResolver) {
        String oldDefaultKeyboard = Settings.Secure.getString(contentResolver, "default_input_method");
        Settings.Secure.putString(contentResolver, "enabled_input_methods", keyboardID);
        Settings.Secure.putString(contentResolver, "default_input_method", keyboardID);

    }
}
