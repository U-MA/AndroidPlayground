package com.example.ideanote.androidtestingfun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {

    public final static String STRING_PAYLOAD = "string_payload";

    private Button launchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        launchButton = (Button) findViewById(R.id.button);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NextActivity.createIntent(LaunchActivity.this, STRING_PAYLOAD));
                finish();
            }
        });
    }

}
