package com.hdeva.nfc.ui;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.Tag;

import com.hdeva.nfc.service.NfcReader;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public final MutableLiveData<NdefMessage[]> messages = new MutableLiveData<>();
    public final MutableLiveData<Tag> tag = new MutableLiveData<>();

    private NfcReader reader = new NfcReader();

    public void processIntent(Intent intent) {
        messages.postValue(reader.readNdefMessagesFromIntent(intent));
        tag.postValue(reader.readTagFromIntent(intent));
    }

}
