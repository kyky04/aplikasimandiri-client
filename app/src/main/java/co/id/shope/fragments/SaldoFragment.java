package co.id.shope.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.data.Session;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Notif;
import co.id.shope.utils.Toko;
import okhttp3.Response;

import static co.id.shope.data.Contans.TOPUP;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaldoFragment extends DialogFragment {
    public static final String ARG_DATA = "DATA";
    private static final String TAG = "KonfirmasiFragment";


    Unbinder unbinder;
    public CallbackResult callbackResult;
    @BindView(R.id.et_nama_user)
    EditText etNamaUser;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_bank_tujuan)
    EditText etBankTujuan;
    @BindView(R.id.et_rek_tujuan)
    EditText etRekTujuan;
    @BindView(R.id.et_bank_pengirim)
    EditText etBankPengirim;
    @BindView(R.id.et_rek_pengirim)
    EditText etRekPengirim;
    @BindView(R.id.et_nama_pengirim)
    EditText etNamaPengirim;
    @BindView(R.id.lampiran)
    ImageView lampiran;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.tv_konfirmasi)
    TextView tvKonfirmasi;
    @BindView(R.id.et_jumlah_bayar)
    EditText etJumlahBayar;
    @BindView(R.id.et_lampiran)
    EditText etLampiran;
    @BindView(R.id.et_tanggal)
    EditText etTanggal;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;


    private ProgressDialog progressDialog;
    private String path;
    File file;
    DataItemOrder item;

    Session session;

    List<String> stringArrayList = new ArrayList<>();
    private int pos, id_kategori = 0;
    ;

    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public SaldoFragment() {
        // Required empty public constructor
    }

    public static SaldoFragment newInstance(DataItemOrder item) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, item);
        SaldoFragment fragment = new SaldoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saldo, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());

        etNamaUser.setText(session.getUser().getData().getName());
        etEmail.setText(session.getUser().getData().getEmail());
        tvToolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
//        etBankTujuan.setText("BCA");
//        etRekTujuan.setText("12324234");
//        etBankPengirim.setText("MANDIRI");
//        etRekPengirim.setText("12321312");
//        etNamaPengirim.setText("Rizki Fauzi");
//        etJumlahBayar.setText("900000");

        return view;
    }

    private void addImage() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.// image selection title
                .single()// Toolbar 'up' arrow color
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            file = new File(image.getPath());
            path = image.getPath();
            Glide.with(getActivity()).load(file).into(lampiran);
            lampiran.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.et_lampiran, R.id.tv_konfirmasi, R.id.et_tanggal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_lampiran:
                addImage();
                break;
            case R.id.tv_konfirmasi:
                konfirmasi();
                break;
            case R.id.et_tanggal:
                Notif.dialogTanggalFormat(getActivity(), etTanggal, "yyyy-MM-dd");
                break;
        }
    }

    public void konfirmasi() {
        DialogUtil.openDialog(getActivity());

        ANRequest.MultiPartBuilder postRequestBuilder = new ANRequest.MultiPartBuilder<>(TOPUP);
        postRequestBuilder.addMultipartParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()));
        postRequestBuilder.addMultipartParameter("id_user", String.valueOf(session.getUser().getData().getId()));
        postRequestBuilder.addMultipartParameter("jumlah", etJumlahBayar.getText().toString());
        postRequestBuilder.addMultipartParameter("tanggal", etTanggal.getText().toString());
        postRequestBuilder.addMultipartParameter("bank_tujuan", etBankTujuan.getText().toString());
        postRequestBuilder.addMultipartParameter("rekening_tujuan", etRekTujuan.getText().toString());
        postRequestBuilder.addMultipartParameter("bank_pengirim", etBankPengirim.getText().toString());
        postRequestBuilder.addMultipartParameter("rekening_pengirim", etRekPengirim.getText().toString());
        postRequestBuilder.addMultipartParameter("nama_pengirim", etNamaPengirim.getText().toString());
        postRequestBuilder.addMultipartParameter("created_by", String.valueOf(session.getUser().getData().getId()));
        postRequestBuilder.addMultipartFile("gambar", file);

        postRequestBuilder.build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        DialogUtil.closeDialog();
                        if (response.isSuccessful()) {
                            CommonUtil.showToast(getActivity(), "Topup Succes");
                            callbackResult.sendResult();
                            dismiss();
                        } else {
                            CommonUtil.showToast(getActivity(), "Topup Gagal, Coba lagi");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        DialogUtil.closeDialog();
                        CommonUtil.showToast(getActivity(), "Error Koneksi");
                        dismiss();
                    }
                });
    }


    public interface CallbackResult {
        void sendResult();
    }


    void openDialog() {
        progressDialog = ProgressDialog.show(getActivity(), "Harap tunggu", "Prosess .  . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    void closeDialog() {
        progressDialog.dismiss();
    }

}
