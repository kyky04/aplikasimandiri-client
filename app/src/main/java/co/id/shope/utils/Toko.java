package co.id.shope.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import co.id.shope.models.DataToko;

public class Toko {
    static DataToko toko;

    public static DataToko get(Context context) {
        String myJson = null;
        try {
            myJson = CommonUtil.inputStreamToString(context.getAssets().open("data_login.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        toko = new Gson().fromJson(myJson, DataToko.class);
        return toko;
    }
}
