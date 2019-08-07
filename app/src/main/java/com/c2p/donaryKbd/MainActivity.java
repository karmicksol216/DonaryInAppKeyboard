package com.c2p.donaryKbd;

import android.content.ContentResolver;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        imeManager.showInputMethodPicker();

        //startActivity(new Intent("android.settings.INPUT_METHOD_SETTINGS"));


    }


    protected static void changekeyboard(String keyboardID, ContentResolver contentResolver) {
        String oldDefaultKeyboard = Settings.Secure.getString(contentResolver, "default_input_method");
        Settings.Secure.putString(contentResolver, "enabled_input_methods", keyboardID);
        Settings.Secure.putString(contentResolver, "default_input_method", keyboardID);

    }
}
