package com.hdeva.nfc.ui.adapter;


import android.nfc.NdefMessage;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hdeva.nfc.R;
import com.hdeva.nfc.ui.adapter.holder.NdefMessageViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NdefMessagesAdapter extends RecyclerView.Adapter<NdefMessageViewHolder> {

    private NdefMessage[] messages;

    public NdefMessagesAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public NdefMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NdefMessageViewHolder(inflater.inflate(R.layout.holder_ndef_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NdefMessageViewHolder holder, int position) {
        holder.bind(messages[position], position);
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.length;
    }

    @Override
    public long getItemId(int position) {
        return messages[position].toString().hashCode();
    }

    public void setMessages(NdefMessage[] messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
}
