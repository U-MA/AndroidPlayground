package com.example.ideanote.androidtestingfun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class NextActivity extends AppCompatActivity {

    public final static String EXTRAS_PAYLOAD_KEY = "extras_payload_key";

    public static Intent createIntent(Context context, String extra) {
        return new Intent(context, NextActivity.class).putExtra(EXTRAS_PAYLOAD_KEY, extra);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
    }
}
