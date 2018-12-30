package co.id.shope.services;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationExtenderBareBonesExample extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        JSONObject data = receivedResult.payload.additionalData;
        String type = "";

        Log.d("DEBUG", "onNotificationProcessing: "+data.toString());

        try {
            type = data.getString("tipe_notif");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Read properties from result.
        if (type.equals("chat")) {
            try {
                broadcastIntent(data.getString("pesan"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // Return true to stop the notification from displaying.
        return false;
    }

    public void broadcastIntent(String pesan) {
        Intent intent = new Intent();
        intent.setAction("co.id.shope.CUSTOM_EVENT");
        intent.putExtra("pesan", pesan);
        // We should use LocalBroadcastManager when we want INTRA app
        // communication
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
