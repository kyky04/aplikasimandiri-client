package co.id.shope.fragments;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.adapters.DetailOrderAdapter;
import co.id.shope.data.Session;
import co.id.shope.models.DataItemKategori;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.utils.Toko;
import co.id.shope.views.EqualSpacingItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOrderFragment extends DialogFragment {
    public static final String ARG_DATA = "DATA";
    private static final String TAG = "DialogProdukFragment";


    Unbinder unbinder;
    public CallbackResult callbackResult;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.et_nama_user)
    EditText etNamaUser;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.et_prov)
    EditText etProv;
    @BindView(R.id.et_kota)
    EditText etKota;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.et_no_telp)
    EditText etNoTelp;
    @BindView(R.id.et_nama_bank)
    EditText etNamaBank;
    @BindView(R.id.et_no_rek)
    EditText etNoRek;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.et_no_akun)
    EditText etNoAkun;
    @BindView(R.id.et_kode_pesanan)
    EditText etKodePesanan;
    @BindView(R.id.et_tanggal)
    EditText etTanggal;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_proses)
    Button btnProses;
    @BindView(R.id.btn_batal)
    Button btnBatal;
    @BindView(R.id.lay_proses)
    LinearLayout layProses;
    @BindView(R.id.btn_kirim)
    Button btnKirim;
    @BindView(R.id.lay_kirim)
    LinearLayout layKirim;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_total)
    EditText etTotal;

    private ProgressDialog progressDialog;
    private String path;
    File file;
    DataItemOrder item;

    Session session;
    DetailOrderAdapter produkAdapter;

    List<DataItemKategori> itemKategoriList = new ArrayList<>();
    List<String> stringArrayList = new ArrayList<>();
    private int pos, id_kategori = 0;
    ;

    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public DetailOrderFragment() {
        // Required empty public constructor
    }

    public static DetailOrderFragment newInstance(DataItemOrder item) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, item);
        DetailOrderFragment fragment = new DetailOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));

        if (getArguments() != null) {
            item = (DataItemOrder) getArguments().getSerializable(ARG_DATA);
            etNamaUser.setText(item.getUser().getName());
            etEmail.setText(item.getUser().getEmail());
            etProv.setText(item.getAddress().getNamaProvinsi());
            etKota.setText(item.getAddress().getNamaKota());
            etAlamat.setText(item.getAddress().getAlamat());
            etNoTelp.setText(item.getAddress().getNomorTelepon());
            etBank.setText(item.getPayment().getAkun());
            etNoRek.setText(item.getPayment().getNomorAkun());
            etKodePesanan.setText(item.getKode());
            etTanggal.setText(item.getCreatedAt());
//            etTotal.setText(item.getPayment().getT);

            recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            recycler.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.HORIZONTAL));
            recycler.setHasFixedSize(true);
            recycler.setNestedScrollingEnabled(false);
            recycler.setHasFixedSize(true);

            int status_order = item.getStatus();
            if (status_order == 0) {
                tvStatus.setText("Menunggu Konfirmasi");
                tvStatus.setBackgroundResource(R.drawable.status_pending);
            } else if (status_order == 1) {
                tvStatus.setText("Pembayaran diterima");
                tvStatus.setBackgroundResource(R.drawable.status_approved);
            } else if (status_order == 2) {
                tvStatus.setText("On Prosess");
                tvStatus.setBackgroundResource(R.drawable.status_on_proses);
            } else if (status_order == 3) {
                tvStatus.setText("On Delivery");
                tvStatus.setBackgroundResource(R.drawable.status_on_delivery);
            } else if (status_order == 4) {
                tvStatus.setText("Selesai");
                tvStatus.setBackgroundResource(R.drawable.status_selesai);
            } else if (status_order == 5) {
                tvStatus.setText("Pembayaran ditolak");
                tvStatus.setBackgroundResource(R.drawable.status_rejected);
            }

            produkAdapter = new DetailOrderAdapter(getActivity());
            recycler.setAdapter(produkAdapter);
            if (item.getDetail().size() > 0) {
                for (int i = 0; i < item.getDetail().size(); i++) {
                    produkAdapter.add(item.getDetail().get(i).getProduk());

                }
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_proses, R.id.btn_batal, R.id.btn_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_proses:
                break;
            case R.id.btn_batal:
                break;
            case R.id.btn_kirim:
                break;
        }
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
