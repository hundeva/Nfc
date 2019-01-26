package com.hdeva.nfc.service;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.text.TextUtils;

public class NfcReader {

    public NdefMessage[] readNdefMessagesFromIntent(Intent intent) {
        NdefMessage[] ndefMessages = null;
        String action = intent.getAction();
        if (TextUtils.equals(action, NfcAdapter.ACTION_TAG_DISCOVERED)
                || TextUtils.equals(action, NfcAdapter.ACTION_TECH_DISCOVERED)
                || TextUtils.equals(action, NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            Parcelable[] parcelableMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (parcelableMessages != null) {
                ndefMessages = new NdefMessage[parcelableMessages.length];
                for (int i = 0; i < parcelableMessages.length; i++) {
                    ndefMessages[i] = (NdefMessage) parcelableMessages[i];
                }
            }
        }
        return ndefMessages;
    }

    public Tag readTagFromIntent(Intent intent) {
        Tag tag = null;
        if (TextUtils.equals(intent.getAction(), NfcAdapter.ACTION_TAG_DISCOVERED)) {
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
        return tag;
    }
}
