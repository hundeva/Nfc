package com.hdeva.nfc.ui.adapter.holder;

import android.nfc.NdefMessage;
import android.view.View;
import android.widget.TextView;

import com.hdeva.nfc.R;
import com.hdeva.nfc.service.MessageExtractor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NdefMessageViewHolder extends RecyclerView.ViewHolder {

    private MessageExtractor extractor = new MessageExtractor();

    @BindView(R.id.holder_message_index)
    TextView messageIndex;
    @BindView(R.id.holder_message_records)
    TextView records;

    public NdefMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(NdefMessage message, int position) {
        messageIndex.setText(itemView.getContext().getString(R.string.nfc_message_numbered_text, position));
        records.setText(extractor.extractMessageFromNdefMessage(itemView.getContext(), message));
    }
}
