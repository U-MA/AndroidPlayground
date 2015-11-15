package com.example.ideanote.espressobasicsample;

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

public class ShowTextActivity extends AppCompatActivity {

    private final static String EXTRA_MESSAGE_TEXT = "extra_message_text";

    public static Intent createIntent(Context context, String text) {
        Intent intent = new Intent(context, ShowTextActivity.class);
        intent.putExtra(EXTRA_MESSAGE_TEXT, text);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String text = intent.getStringExtra(EXTRA_MESSAGE_TEXT);

        TextView textView = (TextView) findViewById(R.id.show_text_view);
        textView.setText(text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
