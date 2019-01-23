package com.hdeva.nfc.service;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import com.hdeva.nfc.R;

public class NfcWriter {

    private NdefRecordFactory ndefRecordFactory = new NdefRecordFactory();
    private boolean writing;

    public void writeMessageToTag(Context context, Tag tag, String message) {
        if (tag == null) {
            Toast.makeText(context, R.string.nfc_tag_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(context);
        if (adapter == null) {
            Toast.makeText(context, R.string.nfc_adapter_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        enableWriteMode(adapter);
        Ndef ndef = Ndef.get(tag); // TODO
        disableWriteMode(adapter);
    }

    private void enableWriteMode(NfcAdapter adapter) {
        writing = true;
    }

    private void disableWriteMode(NfcAdapter adapter) {
        writing = false;
    }
}
