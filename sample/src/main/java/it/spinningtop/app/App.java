package it.spinningtop.app;

import android.app.Application;

import it.spinningtop.lib.SpinningTop;


public class App extends Application {

    private SpinningTop spinningTop;

    @Override
    public void onCreate() {
        super.onCreate();

        SpinningTop.Builder builder = new SpinningTop.Builder(this);
        spinningTop = builder.build();

    }
}
