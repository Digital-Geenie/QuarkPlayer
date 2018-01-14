package org.anirudh.redquark.quarkplayer.application;

import android.app.Application;

import org.anirudh.redquark.quarkplayer.receiver.ConnectivityReceiver;

public class QuarkPlayerApplication extends Application {
    private static QuarkPlayerApplication application;

    public static QuarkPlayerApplication getApplication() {
        return application;
    }

    public static void setApplication(QuarkPlayerApplication application) {
        QuarkPlayerApplication.application = application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static synchronized QuarkPlayerApplication getInstance() {
        return application;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
