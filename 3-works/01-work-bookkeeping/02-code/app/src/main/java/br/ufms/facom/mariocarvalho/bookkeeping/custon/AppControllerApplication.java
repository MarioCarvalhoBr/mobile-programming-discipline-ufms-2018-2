package br.ufms.facom.mariocarvalho.bookkeeping.custon;

import android.app.Application;
import android.content.res.Configuration;

public class AppControllerApplication extends Application {

    private static final String TAG = AppControllerApplication.class.getSimpleName();
    private static AppControllerApplication mSingletonApplication;

    public static synchronized AppControllerApplication getInstance() {
        return mSingletonApplication;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingletonApplication = this;
    }

}