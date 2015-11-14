package com.example.ideanote.androidtestingfun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickFunActivity extends AppCompatActivity {

    Button clickMeButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_fun);

        clickMeButton = (Button) findViewById(R.id.launch_next_activity_button);
        textView = (TextView) findViewById(R.id.info_text_view);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getVisibility() == View.GONE) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(R.string.button_clicked);
                }
            }
        });


    }

}
