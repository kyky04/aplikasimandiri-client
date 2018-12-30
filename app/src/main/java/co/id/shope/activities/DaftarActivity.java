package co.id.shope.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.models.DataToko;
import co.id.shope.models.LoginResponse;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.Toko;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static co.id.shope.data.Contans.REGISTER_MEMBER;


public class DaftarActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    Button btnSignin;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_nama)
    EditText etNama;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.tv_nama_toko)
    TextView tvNamaToko;
    @BindView(R.id.layout)
    RelativeLayout layout;
    private ProgressDialog progress;
    private ArrayList<View> arrayListView = new ArrayList<>();
    DataToko dataUser;

//    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);


        tvNamaToko.setText(Toko.get(this).getNama_toko());
        layout.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public Boolean checkAccount() {
        boolean moveActivity = false;
        arrayListView.add(etNama);
        arrayListView.add(etPassword);
        arrayListView.add(etPhone);
        arrayListView.add(etPassword);

        if (CommonUtil.validateEmptyEntries(arrayListView)) {
            moveActivity = true;
            register();
        } else {
            CommonUtil.showToast(this, "Please fill in all fields");
        }
        return moveActivity;
    }

    private void register() {
        openDialog();
        ANRequest.PostRequestBuilder post = new ANRequest.PostRequestBuilder(REGISTER_MEMBER);
        post.addBodyParameter("email", etEmail.getText().toString());
        post.addBodyParameter("password", etPassword.getText().toString());
        post.addBodyParameter("name", etNama.getText().toString());
        post.addBodyParameter("nomor_telepon", etPhone.getText().toString());
        post.addBodyParameter("id_toko", String.valueOf(Toko.get(this).getId_toko()));
        post.build().getAsObject(LoginResponse.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                closeDialog();
                if (response instanceof LoginResponse) {
                    if (((LoginResponse) response).isStatus()) {
                        CommonUtil.showToast(DaftarActivity.this, "Pendaftaran Berhasil");
                        finish();
                    } else {
                        CommonUtil.showToast(DaftarActivity.this, ((LoginResponse) response).getMessage());
                    }
                }
            }

            @Override
            public void onError(ANError anError) {
                closeDialog();
                CommonUtil.showToast(DaftarActivity.this, "Terjadi masalah ketika berkomunikasi dengan server");
            }
        });
    }

    private void openDialog() {
        progress = new ProgressDialog(this);
        progress.setMessage("Loading . . . ");
        progress.setCancelable(false);
        progress.show();
    }

    private void closeDialog() {
        progress.dismiss();
    }


    @OnClick({R.id.btn_login, R.id.btn_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                supportFinishAfterTransition();
                break;
            case R.id.btn_signup:
                checkAccount();
                break;
        }
    }
}
