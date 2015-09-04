package it.spinningtop.app;

import android.app.Application;

import it.spinningtop.lib.SharedObject;
import it.spinningtop.lib.SpinningTop;

/**
 * Created by Paolo Brandi on 24/02/14.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpinningTop.Builder builder = new SpinningTop.Builder(this);
        builder.setSharedObject(new SharedObject());


    }
}
