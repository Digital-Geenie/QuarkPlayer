package org.anirudh.redquark.quarkplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class QuarkPlayerService extends Service {

    private static final String TAG = QuarkPlayerService.class.getSimpleName();
    private static final int DURATION = 335;

    // Binders given to the clients
    private final IBinder pBinder = new PlayerBinder();
    private PlayerWorker pWorker;

    public QuarkPlayerService() {
    }



    @Override
    public IBinder onBind(Intent intent) throws UnsupportedOperationException {
        return pBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (pWorker != null) {
            pWorker.interrupt();
        }
        return super.onUnbind(intent);
    }

    public void play() {
        if (pWorker == null) {
            pWorker = new PlayerWorker();
            pWorker.start();
        } else {
            pWorker.doResume();
        }
    }

    public boolean isPlaying() {
        return pWorker != null && pWorker.isPlaying();
    }

    public void pause() {
        if (pWorker != null) {
            pWorker.doPause();
        }
    }

    public int getPosition() {
        if (pWorker != null) {
            return pWorker.getPosition();
        }
        return 0;
    }

    public int getDuration() {
        return DURATION;
    }


    private static class PlayerWorker extends Thread {

        boolean paused = false;
        int position = 0;

        @Override
        public void run() {
            try {
                while (position < DURATION) {
                    sleep(1000);
                    if (!paused) {
                        position++;
                    }
                }
            } catch (InterruptedException e) {
                Log.d(TAG, "Player unbounded");
            }
        }

        void doResume() {
            paused = false;
        }

        void doPause() {
            paused = true;
        }

        boolean isPlaying() {
            return !paused;
        }

        int getPosition() {
            return position;
        }
    }


    /**
     * Class used for the client Binder.
     */
    public class PlayerBinder extends Binder {

        public QuarkPlayerService getService() {
            // Return this instance of PlayerService so clients can call public methods
            return QuarkPlayerService.this;
        }
    }
}
