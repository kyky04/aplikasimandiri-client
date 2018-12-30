package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.CheckOutActivity;
import co.id.shope.adapters.BankAdapter;
import co.id.shope.adapters.ShippingAdapter;
import co.id.shope.data.Contans;
import co.id.shope.models.BankResponse;
import co.id.shope.models.DataItemBank;
import co.id.shope.utils.CommonUtil;
import co.id.shope.views.SliderIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CartPembayaranFragment extends Fragment {
    private static final String TAG = "CartShippingFragment";

    @BindView(R.id.recycler_pembayaran)
    RecyclerView recyclerPembayaran;
    @BindView(R.id.card)
    CardView card;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    ShippingAdapter shippingAdapter;
    BankAdapter adapter;

    int id_bank = 0;

    String nama_bank, norek,atas_nama ="";


    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public CartPembayaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembayaran, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerPembayaran.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BankAdapter(getActivity());
        recyclerPembayaran.setAdapter(adapter);


        loadAlamat();


        adapter.setOnItemClickListener(new BankAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataItemBank bank) {
                Log.d(TAG, "onClick: " + bank.getId());
                id_bank = bank.getId();
                atas_nama = bank.getAtasNama();
                norek = bank.getNoRekening();
                nama_bank = bank.getNamaBank();
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }


    void loadAlamat() {
        openDialog();
        AndroidNetworking.get(Contans.BANK)
                .build()
                .getAsObject(BankResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof BankResponse) {
                            adapter.swap(((BankResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closerDialog();
                    }
                });
    }


    void openDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    void closerDialog() {
        progressDialog.dismiss();
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    public boolean getIdbank() {
        if (id_bank != 0) {
            Log.d(TAG, "getIdbank: "+atas_nama);
            ((CheckOutActivity) getActivity()).setBank(id_bank);
            ((CheckOutActivity) getActivity()).getReview().setAtas_nama(atas_nama);
            ((CheckOutActivity) getActivity()).getReview().setRekening(nama_bank);
            ((CheckOutActivity) getActivity()).getReview().setNo_rek(norek);
            return true;
        } else {
            CommonUtil.showToast(getActivity(), "Anda Belum memilih bank !");
            return false;
        }
    }
}
