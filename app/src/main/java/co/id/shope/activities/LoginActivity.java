package co.id.shope.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.LoginResponse;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.Toko;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static co.id.shope.data.Contans.LOGIN;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.tv_nama_toko)
    TextView tvNamaToko;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.tv_reset_pass)
    TextView tvResetPass;

    private ProgressDialog progress;
    private ArrayList<View> arrayListView = new ArrayList<>();

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (!CommonUtil.verifyPermission(this, WRITE_EXTERNAL_STORAGE) || !CommonUtil.verifyPermission(this, CAMERA)) {
            requestPermission();
        }
        session = new Session(this);

        tvNamaToko.setText(Toko.get(this).getNama_toko());

        layout.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));

        if (session.isLoggedIn()) {
//            openAdminActivity();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public Boolean checkAccount() {
        boolean moveActivity = false;
        arrayListView.add(etEmail);
        arrayListView.add(etPassword);

        if (CommonUtil.validateEmptyEntries(arrayListView)) {
            login();
        } else {
            CommonUtil.showToast(this, "Please fill in all fields");
        }
        return moveActivity;
    }

    private void login() {
        openDialog();
        ANRequest.PostRequestBuilder post = new ANRequest.PostRequestBuilder(LOGIN);
        post.addBodyParameter("email", etEmail.getText().toString());
        post.addBodyParameter("password", etPassword.getText().toString());
        post.build().getAsObject(LoginResponse.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                closeDialog();
                if (response instanceof LoginResponse) {
                    if (((LoginResponse) response).isStatus()) {
                        if (((LoginResponse) response).getData().getIdToko() == Toko.get(LoginActivity.this).getId_toko()) {
                            session.createUser(((LoginResponse) response));
                            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        } else {
                            CommonUtil.showToast(LoginActivity.this, "Maaf anda belum terdaftar");
                        }
//                        CommonUtil.showToast(LoginActivity.this,((LoginResponse) response).getData().getName());
                    } else {
                        CommonUtil.showToast(LoginActivity.this, ((LoginResponse) response).getMessage());
                    }
                }
            }

            @Override
            public void onError(ANError anError) {
                closeDialog();
                CommonUtil.showToast(LoginActivity.this, "Terjadi masalah ketika berkomunikasi dengan server");
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

    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }


    @OnClick({R.id.btn_login, R.id.btn_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                checkAccount();
                break;
            case R.id.btn_signup:
                startActivity(new Intent(this, DaftarActivity.class));
                break;
        }
    }

    @OnClick(R.id.tv_reset_pass)
    public void onViewClicked() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url",Contans.RESET_PASS);
        startActivity(intent);
    }
}
