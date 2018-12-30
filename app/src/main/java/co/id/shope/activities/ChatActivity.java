package co.id.shope.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.adapters.ChatAdapter;
import co.id.shope.data.Session;
import co.id.shope.models.UploadChatResponse;
import co.id.shope.models.chat.ChatResponse;
import co.id.shope.models.chat.DataItemChat;
import co.id.shope.utils.Toko;

import static co.id.shope.data.Contans.CHAT_ROOM;
import static co.id.shope.data.Contans.CHAT_TOKO;


public class ChatActivity extends Activity {
    private static final String TAG = "ChatActivity";
    @BindView(R.id.btnSend)
    ImageButton btnSend;
    @BindView(R.id.recyclerChat)
    RecyclerView recyclerChat;
    @BindView(R.id.editWriteMessage)
    EditText editWriteMessage;

    ChatAdapter chatAdapter;

    int id_pengirim;

    Session session;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        IntentFilter intent = new IntentFilter("co.id.shope.CUSTOM_EVENT");
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, intent);

        session = new Session(this);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerChat.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(this);

        recyclerChat.setAdapter(chatAdapter);
        id_pengirim = session.getUser().getData().getId();

        toolbar.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));
        Log.d(TAG, "pengirim: " + id_pengirim + " penerima " + Toko.get(this).getId_toko());

        getData();


    }

    private void getData() {
        AndroidNetworking.post(CHAT_ROOM)
                .addBodyParameter("pengirim", String.valueOf(id_pengirim))
                .addBodyParameter("penerima", String.valueOf(Toko.get(this).getId_user()))
                .build()
                .getAsObject(ChatResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof ChatResponse) {
                            if (((ChatResponse) response).getData().size() > 0) {
                                chatAdapter.swap(((ChatResponse) response).getData());
                                linearLayoutManager.scrollToPosition(chatAdapter.getItemCount() - 1);
                            } else {

                            }

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());

                    }
                });
    }

    private void chatUser() {
        AndroidNetworking.post(CHAT_TOKO)
                .addBodyParameter("pengirim", String.valueOf(id_pengirim))
                .addBodyParameter("penerima", String.valueOf(Toko.get(this).getId_user()))
                .addBodyParameter("pesan", editWriteMessage.getText().toString())
                .addBodyParameter("created_by", String.valueOf(session.getUser().getData().getId()))
                .build()
                .getAsObject(UploadChatResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof UploadChatResponse) {
                            if (((UploadChatResponse) response).getData() != null) {
                                chatAdapter.add(((UploadChatResponse) response).getData());
                                chatAdapter.notifyDataSetChanged();
                                editWriteMessage.setText("");

                                linearLayoutManager.scrollToPosition(chatAdapter.getItemCount() - 1);
                            } else {

                            }

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());

                    }
                });
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
        chatUser();
    }

    private BroadcastReceiver onNotice = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {
            String pesan = intent.getStringExtra("pesan");
            Log.d(TAG, "onReceive: " + pesan);
            DataItemChat chat = new DataItemChat();
            chat.setPesan(pesan);
            chat.setIdUser(Toko.get(ChatActivity.this).getId_user());

            chatAdapter.add(chat);
            chatAdapter.notifyDataSetChanged();

            linearLayoutManager.scrollToPosition(chatAdapter.getItemCount() - 1);
        }
    };
}
