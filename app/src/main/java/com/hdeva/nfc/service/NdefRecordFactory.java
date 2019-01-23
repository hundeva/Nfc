package com.hdeva.nfc.service;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.text.TextUtils;

public class NdefRecordFactory {

    public NdefMessage createNdefMessageForMessages(String... messages) {
        if (messages == null) {
            return null;
        }

        NdefRecord[] records = new NdefRecord[messages.length];
        for (int i = 0; i < messages.length; i++) {
            records[i] = createNdefRecordForMessage(messages[i]);
        }
        return new NdefMessage(records);
    }

    public NdefRecord createNdefRecordForMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            return null;
        }

        String language = "en";

        byte[] languageBytes = language.getBytes();
        int languageLength = languageBytes.length;

        byte[] textBytes = message.getBytes();
        int textLength = textBytes.length;

        byte[] payload = new byte[1 + languageLength + textLength];
        payload[0] = (byte) languageLength;

        System.arraycopy(languageBytes, 0, payload, 1, languageLength);
        System.arraycopy(textBytes, 0, payload, 1 + languageLength, textLength);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload);
    }
}
