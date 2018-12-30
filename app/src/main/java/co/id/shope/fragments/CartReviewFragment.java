package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.CheckOutActivity;
import co.id.shope.activities.KeranjangActivity;
import co.id.shope.adapters.BankAdapter;
import co.id.shope.adapters.CartReviewAdapter;
import co.id.shope.adapters.EmptyAdapter;
import co.id.shope.adapters.ShippingAdapter;
import co.id.shope.models.Review;
import co.id.shope.utils.CommonUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CartReviewFragment extends Fragment {
    private static final String TAG = "CartShippingFragment";


    Unbinder unbinder;
    ProgressDialog progressDialog;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.tv_alamat_detail)
    TextView tvAlamatDetail;
    @BindView(R.id.tv_no_hp)
    TextView tvNoHp;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.tv_shipping_method)
    TextView tvShippingMethod;
    @BindView(R.id.card_ship)
    CardView cardShip;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_norek)
    TextView tvNorek;
    @BindView(R.id.atas_nama)
    TextView atasNama;
    @BindView(R.id.nama_bank)
    TextView namaBank;
    @BindView(R.id.lyt_parent)
    LinearLayout lytParent;

    Review review;

    CartReviewAdapter reviewAdapter;


    public CartReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    public void setReview(){
        review = ((CheckOutActivity)getActivity()).getReview();
        Log.d(TAG, "getIdbank: "+review.getAlamat());
        tvAlamat.setText(review.getAlamat());
        tvAlamatDetail.setText(review.getAlamat_detail());
        tvNoHp.setText(review.getNo_hp());
        if(review.getShipping().equals("")){
            tvShippingMethod.setText("COD");
        }else {
            tvShippingMethod.setText(review.getShipping());
        }
        tvNorek.setText(review.getNo_rek());
        atasNama.setText(review.getAtas_nama());
        namaBank.setText(review.getRekening());

        reviewAdapter = new CartReviewAdapter(getActivity());
        if (!CommonUtil.isEmpty(((CheckOutActivity) getActivity()).getListProduk())) {
            reviewAdapter.swap(((CheckOutActivity) getActivity()).getListProduk());
            recyclerView.setAdapter(reviewAdapter);
        }else {
            recyclerView.setAdapter(new EmptyAdapter(getActivity()));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

}
