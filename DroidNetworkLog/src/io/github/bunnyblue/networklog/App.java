package io.github.bunnyblue.networklog;

import android.app.Application;

/**
 * Created by BunnyBlue on 6/27/15.
 */
public class App extends Application {
    static  App  instance=null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public  static  App getInstance(){

        return  instance;
    }
}
