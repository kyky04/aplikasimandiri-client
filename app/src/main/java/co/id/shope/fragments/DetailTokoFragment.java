package co.id.shope.fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.adapters.DetailOrderAdapter;
import co.id.shope.data.Session;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.models.toko.DataItemToko;
import co.id.shope.models.toko.ShowToko;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Toko;
import de.hdodenhof.circleimageview.CircleImageView;

import static co.id.shope.data.Contans.TOKO;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTokoFragment extends DialogFragment {
    public static final String ARG_DATA = "DATA";
    private static final String TAG = "DetailTokoFragment";


    Unbinder unbinder;
    public CallbackResult callbackResult;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_nama_toko)
    TextView tvNamaToko;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_deskripsi)
    EditText etDeskripsi;
    @BindView(R.id.et_prov)
    EditText etProv;
    @BindView(R.id.et_kota)
    EditText etKota;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.et_wa)
    EditText etWa;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.et_nama_user)
    EditText etNamaUser;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.et_no_telp)
    EditText etNoTelp;
    @BindView(R.id.lay)
    LinearLayout lay;


    private ProgressDialog progressDialog;
    private String path;
    File file;
    DataItemOrder item;

    Session session;
    DetailOrderAdapter produkAdapter;


    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public DetailTokoFragment() {
        // Required empty public constructor
    }

    public static DetailTokoFragment newInstance(DataItemOrder item) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, item);
        DetailTokoFragment fragment = new DetailTokoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toko, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        lay.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));

        getData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface CallbackResult {
        void sendResult();
    }

    private void getData() {
        DialogUtil.openDialog(getActivity());
        AndroidNetworking.get(TOKO + "/{id_toko}")
                .addPathParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .build()
                .getAsObject(ShowToko.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        DialogUtil.closeDialog();
                        if (response instanceof ShowToko) {
                            if (((ShowToko) response).isStatus()) {
                                DataItemToko toko = ((ShowToko) response).getData();
                                etName.setText(toko.getNama());
                                etAlamat.setText(toko.getAlamat());
                                etDeskripsi.setText(toko.getDeskripsi());
                                etKota.setText(toko.getNamaKota());
                                etProv.setText(toko.getNamaProvinsi());
                                etWa.setText(toko.getWhatsapp());
                                etNoTelp.setText(toko.getNomorTelepon());
                            } else {
                                CommonUtil.showToast(getActivity(), "Terjadi kesalahan");
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());
                        DialogUtil.closeDialog();

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

}
