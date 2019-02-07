package com.hdeva.nfc.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.hdeva.nfc.service.MessageExtractor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReaderActivity extends AppCompatActivity {

    private MessageExtractor extractor = new MessageExtractor();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            for (String key : intent.getExtras().keySet()) {
                Log.i(":::", "onCreate: " + key + "= " + intent.getExtras().get(key));
            }
        } else {
            Log.i(":::", "onCreate: " + "extras are null");
        }

        Uri uri = intent.getData();
        if (uri != null) {
            Log.i(":::", "onCreate: " + uri.toString());
        } else {
            Log.i(":::", "onCreate: no uri");
        }
    }
}
