package co.id.shope;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.activities.LoginActivity;
import co.id.shope.activities.MenuActivity;
import co.id.shope.data.Session;
import co.id.shope.utils.Toko;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {
    private static final int TIME = 3000;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv_nama_toko)
    TextView tvNamaToko;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.tv_slogan)
    TextView tvSlogan;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        session = new Session(this);

        tvSlogan.setText(Toko.get(this).getMoto_toko());
        tvNamaToko.setText(Toko.get(this).getNama_toko());
        layout.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.isLoggedIn()) {
                    Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Staring Login Activity
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Staring Login Activity
                    startActivity(i);
                    finish();
                }
            }
        }, TIME);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
