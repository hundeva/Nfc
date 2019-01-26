package com.hdeva.nfc.service;

import android.content.Context;
import android.nfc.NdefMessage;
import android.text.TextUtils;

import com.hdeva.nfc.R;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MessageExtractor {

    public String extractMessageFromNdefMessage(Context context, NdefMessage ndefMessage) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < ndefMessage.getRecords().length; i++) {
            byte[] payload = ndefMessage.getRecords()[i].getPayload();
            String encoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageLength = payload[0] & 0063;

            String message;
            try {
                message = new String(payload, languageLength + 1, payload.length - languageLength - 1, encoding);
            } catch (UnsupportedEncodingException e) {
                message = "ERROR decoding messsage";
            }
            messages.add(context.getString(R.string.nfc_numbered_record_text, i, message));
        }

        return TextUtils.join("\n", messages);
    }
}
