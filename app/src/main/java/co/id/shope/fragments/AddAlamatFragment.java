package co.id.shope.fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.alamat.DataKota;
import co.id.shope.models.alamat.DataProvinsi;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.Notif;
import co.id.shope.utils.Toko;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAlamatFragment extends DialogFragment {
    public static final String ARG_DATA = "DATA";
    private static final String TAG = "DialogProdukFragment";


    Unbinder unbinder;
    public CallbackResult callbackResult;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_provinsi)
    EditText etProvinsi;
    @BindView(R.id.et_kota)
    EditText etKota;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.et_kode_pos)
    EditText etKodePos;
    @BindView(R.id.et_no_telp)
    EditText etNoTelp;
    @BindView(R.id.btn_update)
    Button btnUpdate;


    private ProgressDialog progressDialog;
    int id_kota, id_provinsi = 0;

    Session session;

    List<String> districtList = new ArrayList<>();
    List<String> cityList = new ArrayList<>();
    List<Integer> listIdKota = new ArrayList<>();
    DataKota regency;


    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public interface CallbackResult {
        void sendResult();
    }

    public AddAlamatFragment() {
        // Required empty public constructor
    }

    public static AddAlamatFragment newInstance(DataItemOrder item) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, item);
        AddAlamatFragment fragment = new AddAlamatFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alamat, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void postAlamat() {
        openDialog();
        AndroidNetworking.post(Contans.ALAMAT)
                .addBodyParameter("id_user", String.valueOf(session.getUser().getData().getId()))
                .addBodyParameter("nama", etName.getText().toString())
                .addBodyParameter("id_provinsi", String.valueOf(id_provinsi))
                .addBodyParameter("id_kota", String.valueOf(id_kota))
                .addBodyParameter("alamat", etAlamat.getText().toString())
                .addBodyParameter("kode_pos", etKodePos.getText().toString())
                .addBodyParameter("nomor_telepon", etNoTelp.getText().toString())
                .addBodyParameter("utama", String.valueOf(false))
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        closeDialog();
                        if (response.isSuccessful()) {
                            CommonUtil.showToast(getActivity(), "Alamat berhasil ditambahkan");
                            callbackResult.sendResult();
                            dismiss();
                        }else {
                            CommonUtil.showToast(getActivity(), "Alamat Gagal ditambahkan");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeDialog();

                    }
                });

    }

    void openDialog() {
        progressDialog = ProgressDialog.show(getActivity(), "Harap tunggu", "Prosess .  . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    void closeDialog() {
        progressDialog.dismiss();
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    @OnClick({R.id.et_provinsi, R.id.et_kota, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_provinsi:
                dialogDistrict();
                break;
            case R.id.et_kota:
                dialogCity();
                break;
            case R.id.btn_update:
                postAlamat();
                break;
        }
    }

    public void dialogDistrict() {
        cityList.clear();
        DataProvinsi province = null;
        try {
            String myJson= inputStreamToString(getActivity().getAssets().open("provinsi.json"));
            province = new Gson().fromJson(myJson, DataProvinsi.class);
            for (int i = 0; i < province.getData().size(); i++) {
                districtList.add(province.getData().get(i).getProvince());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] items = new String[districtList.size()];
        items = districtList.toArray(items);

        final String[] finalItems = items;
        final DataProvinsi finalProvince = province;
        Notif.dialogArray(getActivity(), items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etProvinsi.setText(finalItems[which]);
                etProvinsi.setError(null);

                if (Integer.parseInt(finalProvince.getData().get(which).getProvinceId()) != id_provinsi){
                    etKota.setText("");
                }

                try {
                    String myJson = inputStreamToString(getActivity().getAssets().open("kota.json"));
                     regency = new Gson().fromJson(myJson, DataKota.class);
                    for (int i = 0; i < regency.getData().size(); i++) {
                        if(regency.getData().get(i).getProvinceId().equals(finalProvince.getData().get(which).getProvinceId())){
                            cityList.add(regency.getData().get(i).getCityName());
                            listIdKota.add(Integer.valueOf(regency.getData().get(i).getCityId()));
                            id_provinsi = Integer.parseInt(regency.getData().get(i).getProvinceId());
                            Log.d(TAG, "onClick: "+id_provinsi);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void dialogCity() {

        String[] items = new String[cityList.size()];
        items = cityList.toArray(items);
        final String[] finalItems = items;

        Notif.dialogArray(getActivity(), items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etKota.setText(finalItems[which]);
                etKota.setError(null);
                id_kota = listIdKota.get(which);
                Log.d(TAG, "onClick: "+id_kota);

            }
        });
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
}
