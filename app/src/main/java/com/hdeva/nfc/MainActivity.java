package com.hdeva.nfc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Nfc);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
