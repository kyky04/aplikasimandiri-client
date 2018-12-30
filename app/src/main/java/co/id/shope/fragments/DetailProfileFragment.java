package co.id.shope.fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.adapters.AlamatAdapter;
import co.id.shope.adapters.AlamatProfilAdapter;
import co.id.shope.adapters.DetailOrderAdapter;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.AlamatResponse;
import co.id.shope.models.member.ShowMember;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Toko;
import de.hdodenhof.circleimageview.CircleImageView;

import static co.id.shope.data.Contans.MEMBER;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailProfileFragment extends DialogFragment {
    public static final String ARG_DATA = "DATA";
    private static final String TAG = "DialogProdukFragment";


    Unbinder unbinder;
    public CallbackResult callbackResult;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_saldo)
    TextView tvSaldo;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_no_hp)
    EditText etNoHp;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.add_alamat)
    ImageView addAlamat;
    @BindView(R.id.recycler_alamat)
    RecyclerView recyclerAlamat;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.et_nama_user)
    EditText etNamaUser;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    AlamatProfilAdapter alamatAdapter;


    private ProgressDialog progressDialog;
    private String path;
    File file;
    DataItemOrder item;

    Session session;
    DetailOrderAdapter produkAdapter;


    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public DetailProfileFragment() {
        // Required empty public constructor
    }

    public static DetailProfileFragment newInstance(DataItemOrder item) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, item);
        DetailProfileFragment fragment = new DetailProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        lay.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));

        alamatAdapter = new AlamatProfilAdapter(getActivity());
        recyclerAlamat.setAdapter(alamatAdapter);
        recyclerAlamat.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_saldo, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_saldo:
                SaldoFragment saldoFragment = new SaldoFragment();
                openFragment(saldoFragment);
                saldoFragment.setOnCallbackResult(new SaldoFragment.CallbackResult() {
                    @Override
                    public void sendResult() {
                        getData();
                    }
                });
                break;
            case R.id.btn_update:
                break;
        }
    }

    @OnClick(R.id.add_alamat)
    public void onViewClicked() {
        AddAlamatFragment fragment = new AddAlamatFragment();
        openFragment(fragment);
        fragment.setOnCallbackResult(new AddAlamatFragment.CallbackResult() {
            @Override
            public void sendResult() {
                loadAlamat();
            }
        });

    }

    public interface CallbackResult {
        void sendResult();
    }

    private void getData() {
        DialogUtil.openDialog(getActivity());
        AndroidNetworking.get(MEMBER + "/{id_member}")
                .addPathParameter("id_member", String.valueOf(session.getUser().getData().getId()))
                .build()
                .getAsObject(ShowMember.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof ShowMember) {
                            if (((ShowMember) response).isStatus()) {
                                tvNama.setText(((ShowMember) response).getData().getName());
                                tvSaldo.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(((ShowMember) response).getData().getSaldo())));
                                etName.setText(((ShowMember) response).getData().getName());
                                etNoHp.setText(((ShowMember) response).getData().getNomorTelepon());
                                etEmail.setText(((ShowMember) response).getData().getEmail());
                            } else {
                                CommonUtil.showToast(getActivity(), "Terjadi kesalahan");
                            }
                        }

                        loadAlamat();

                    }

                    @Override
                    public void onError(ANError anError) {
                        loadAlamat();
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());

                    }
                });
    }

    void loadAlamat() {
        AndroidNetworking.get(Contans.ALAMAT)
                .addQueryParameter("id_user", String.valueOf(session.getUser().getData().getId()))
                .build()
                .getAsObject(AlamatResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        DialogUtil.closeDialog();
                        if (response instanceof AlamatResponse) {
                            alamatAdapter.swap(((AlamatResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
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
