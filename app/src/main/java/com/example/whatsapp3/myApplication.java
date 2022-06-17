package com.example.whatsapp3;
import android.app.Application;
import android.content.Context;

public class myApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
