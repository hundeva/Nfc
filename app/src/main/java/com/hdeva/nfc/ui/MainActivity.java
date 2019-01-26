package com.hdeva.nfc.ui;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hdeva.nfc.R;
import com.hdeva.nfc.ui.adapter.NdefMessagesAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @BindView(R.id.main_text_input)
    TextInputLayout textInputLayout;
    @BindView(R.id.main_edit_text)
    TextInputEditText textInputEditText;
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_fab)
    FloatingActionButton floatingActionButton;

    private NdefMessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Nfc);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new NdefMessagesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        setupObservers();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        viewModel.processIntent(intent);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    private void setupObservers() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.messages.observe(this, this::observeMessages);
        viewModel.tag.observe(this, this::observeTag);
    }

    private void observeMessages(NdefMessage[] ndefMessages) {
        adapter.setMessages(ndefMessages);
    }

    private void observeTag(Tag tag) {
        floatingActionButton.setEnabled(tag != null);
    }
}
