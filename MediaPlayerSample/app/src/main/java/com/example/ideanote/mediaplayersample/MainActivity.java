package com.example.ideanote.mediaplayersample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isStop; // STOPボタンを押した直後かどうか

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isStop = false;

        initMediaPlayer();

        TextView stateView = (TextView) findViewById(R.id.state_view);
        stateView.setText("Loading...");

        Button playAndPauseButton = (Button) findViewById(R.id.play_and_pause_button);
        playAndPauseButton.setEnabled(false);
        playAndPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isStop) {
                        isStop = false;
                        TextView textView = (TextView) findViewById(R.id.state_view);
                        textView.setText("Playing");
                        mediaPlayer.start();
                    } else if (!mediaPlayer.isPlaying()) {
                        TextView textView = (TextView) findViewById(R.id.state_view);
                        textView.setText("Playing");
                        mediaPlayer.start();
                    } else {
                        TextView textView = (TextView) findViewById(R.id.state_view);
                        textView.setText("Pause");
                        mediaPlayer.pause();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setEnabled(false);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.state_view);
                textView.setText("Stop");
                try {
                    mediaPlayer.pause();
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    isStop = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();

        Uri uri = Uri.parse("http://techbooster.org/wp-content/uploads/2015/10/techboosterfm_vol_02.mp3");

        try {
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    TextView textView = (TextView) findViewById(R.id.state_view);
                    textView.setText("Ready");
                    Button playAndPauseButton = (Button) findViewById(R.id.play_and_pause_button);
                    if (!playAndPauseButton.isEnabled()) {
                        playAndPauseButton.setEnabled(true);
                    }
                    Button stopButton = (Button) findViewById(R.id.stop_button);
                    if (!stopButton.isEnabled()) {
                        stopButton.setEnabled(true);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
    }
}
