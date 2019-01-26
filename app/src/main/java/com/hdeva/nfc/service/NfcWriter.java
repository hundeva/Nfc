package com.hdeva.nfc.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import com.hdeva.nfc.R;
import com.hdeva.nfc.ui.MainActivity;

import java.io.IOException;

public class NfcWriter {

    private NdefFactory factory = new NdefFactory();

    public void enableWriteMode(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0);
        IntentFilter intentFilter = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        NfcAdapter.getDefaultAdapter(activity).enableForegroundDispatch(activity, pendingIntent, new IntentFilter[]{intentFilter}, null);
    }

    public void disableWriteMode(Activity activity) {
        NfcAdapter.getDefaultAdapter(activity).disableForegroundDispatch(activity);
    }

    public void writeMessageToTag(Context context, Tag tag, String message) {
        if (tag == null) {
            Toast.makeText(context, R.string.nfc_tag_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            NdefMessage ndefMessage = factory.createNdefMessageForMessages(message.split(";"));
            Ndef ndef = Ndef.get(tag);
            ndef.connect();
            ndef.writeNdefMessage(ndefMessage);
            ndef.close();
            Toast.makeText(context, R.string.ndef_write_complete, Toast.LENGTH_SHORT).show();
        } catch (FormatException e) {
            Toast.makeText(context, R.string.ndef_format_error, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, R.string.ndef_io_error, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
