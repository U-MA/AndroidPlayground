package com.example.ideanote.mediaplayersample;

import android.os.Handler;
import android.os.Message;

/**
 * Rebuild.app for AndroidのTimerのコピー
 */
public class Timer extends Handler {
    private static final int DEFAULT_INTERVAL_MILLIS = 1000;

    private boolean isUpdate;
    private int intervalMillis = DEFAULT_INTERVAL_MILLIS;
    private Callback callback;
    private long startMillis;

    public Timer(Callback callback) {
        this.callback = callback;
    }

    public Timer(Callback callback, int intervalMillis) {
        this.callback = callback;
        this.intervalMillis = intervalMillis;
    }

    public void start() {
        startMillis = System.currentTimeMillis();
        isUpdate = true;
        handleMessage(new Message());
    }

    public void stop() {
        isUpdate = false;
    }

    @Override
    public void handleMessage(Message message) {
        this.removeMessages(0);
        if (isUpdate) {
            sendMessageDelayed(obtainMessage(0), intervalMillis);
            callback.tick(System.currentTimeMillis() - startMillis);
        }
    }

    public interface Callback {
        public void tick(long timeMillis);
    }
}
