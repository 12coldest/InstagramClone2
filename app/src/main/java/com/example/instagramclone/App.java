package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IH6QTs4n4HtYCtENEHQMPZlsVi6piM5aOoFlPrkC")
                // if defined
                .clientKey("FSZutYoM3TVZSWiqz2lB1ScWXf7SVaVeqsXR3FcR")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
