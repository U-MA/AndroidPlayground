package com.example.ideanote.androidtestingfun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReceiverActivity extends AppCompatActivity {

    private final static String EXTRA_MESSAGE = "extra_message";

    public static Intent createIntent(Context context, String message) {
        return new Intent(context, ReceiverActivity.class).putExtra(EXTRA_MESSAGE, message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.received_message_text_view);
        textView.setText(message);
    }

}
