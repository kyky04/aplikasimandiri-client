package co.id.shope.application;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.onesignal.OneSignal;

import java.util.concurrent.TimeUnit;

import co.id.shope.models.MyObjectBox;
import co.id.shope.utils.CommonUtil;
import io.objectbox.BoxStore;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApp extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
//        MultiDex.install(this);
        CommonUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/montserat.ttf");
//
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                . writeTimeout(120, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.enableLogging();

        boxStore = MyObjectBox.builder().androidContext(MyApp.this).build();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
