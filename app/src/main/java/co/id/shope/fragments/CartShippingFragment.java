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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.CheckOutActivity;
import co.id.shope.adapters.AlamatAdapter;
import co.id.shope.adapters.ShippingAdapter;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.AlamatResponse;
import co.id.shope.models.CostsItem;
import co.id.shope.models.DataItemAlamat;
import co.id.shope.models.ShippingResponse;
import co.id.shope.utils.CommonUtil;
import co.id.shope.views.SliderIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CartShippingFragment extends Fragment {
    private static final String TAG = "CartShippingFragment";


    Unbinder unbinder;
    ProgressDialog progressDialog;
    ShippingAdapter shippingAdapter;
    AlamatAdapter alamatAdapter;

    int id_adress = 0;

    double tempShip = 0;
    boolean temp = false;

    String jenis, kurir, ongkir;
    @BindView(R.id.add_alamat)
    ImageView addAlamat;
    @BindView(R.id.recycler_alamat)
    RecyclerView recyclerAlamat;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.radio_puas1)
    RadioButton radioPuas1;
    @BindView(R.id.radio_puas_2)
    RadioButton radioPuas2;
    @BindView(R.id.radio_puas_3)
    RadioButton radioPuas3;
    @BindView(R.id.radio_puas_4)
    RadioButton radioPuas4;
    @BindView(R.id.myRadioGroup)
    RadioGroup myRadioGroup;
    @BindView(R.id.recycler_shipping)
    RecyclerView recyclerShipping;
    @BindView(R.id.card_ship)
    CardView cardShip;

    String alamat_review, alamat_detail, no_hp, shipping = "";

    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public CartShippingFragment() {
        // Required empty public constructor
    }

    int total = 0;
    boolean shippingShow = false;

    Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());

        total = (int) ((CheckOutActivity) getActivity()).getTotal();
        recyclerShipping.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerShipping.setNestedScrollingEnabled(false);

        recyclerAlamat.setLayoutManager(new LinearLayoutManager(getActivity()));

        shippingAdapter = new ShippingAdapter(getActivity());
        alamatAdapter = new AlamatAdapter(getActivity());
        recyclerAlamat.setAdapter(alamatAdapter);
        recyclerShipping.setAdapter(shippingAdapter);

        loadAlamat();

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (temp) {
                    tempShip = 0;
                    ((CheckOutActivity) getActivity()).setTotal(total);
                    temp = false;
                }

                switch (checkedId) {
                    case R.id.radio_puas1:
                        loadShip("jne");
                        kurir = "jne";
                        recyclerShipping.setVisibility(View.VISIBLE);
                        shippingShow = false;
                        break;
                    case R.id.radio_puas_2:
                        loadShip("tiki");
                        kurir = "tiki";
                        recyclerShipping.setVisibility(View.VISIBLE);
                        shippingShow = false;
                        break;
                    case R.id.radio_puas_3:
                        loadShip("pos");
                        kurir = "pos";
                        recyclerShipping.setVisibility(View.VISIBLE);
                        shippingShow = false;
                        break;
                    case R.id.radio_puas_4:
                        recyclerShipping.setVisibility(View.GONE);
                        kurir = "cod";
                        shippingShow = true;
                        break;
                }

            }
        });


        alamatAdapter.setOnItemClickListener(new AlamatAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataItemAlamat alamat) {
                id_adress = alamat.getId();
                tempShip = 0;
                ((CheckOutActivity) getActivity()).setTotal(total);

                cardShip.setVisibility(View.VISIBLE);
                myRadioGroup.clearCheck();

                recyclerShipping.setVisibility(View.GONE);
//                if (shippingShow || temp) {
//                } else {
//                    recyclerShipping.setVisibility(View.VISIBLE);
//                }
                alamat_review = alamat.getNamaProvinsi() + " - " + alamat.getNamaKota() + " - " + alamat.getKodePos();
                alamat_detail = alamat.getAlamat();
                no_hp = alamat.getNomorTelepon();

            }
        });

        shippingAdapter.setOnItemClickListener(new ShippingAdapter.OnItemClickListener() {
            @Override
            public void onClick(CostsItem costsItem) {
                double plus = ((CheckOutActivity) getActivity()).getTotal() + costsItem.getCost().get(0).getValue() - tempShip;
                ((CheckOutActivity) getActivity()).setTotal(plus);

                tempShip = costsItem.getCost().get(0).getValue();
                temp = true;

                jenis = costsItem.getService();
                ongkir = String.valueOf(costsItem.getCost().get(0).getValue());
                shipping = jenis + " - " + ongkir;

                Log.d(TAG, "onClick: "+shipping);
                shippingShow = true;

            }
        });
        return view;
    }

    void showShipping() {
        if (temp) {
            recyclerShipping.setVisibility(View.GONE);
        } else {
            recyclerShipping.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }


    void loadAlamat() {
        openDialog();
        AndroidNetworking.get(Contans.ALAMAT)
                .build()
                .getAsObject(AlamatResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof AlamatResponse) {
                            alamatAdapter.swap(((AlamatResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closerDialog();
                    }
                });
    }

    void loadShip(String ship) {
        openDialog();
        AndroidNetworking.post(Contans.SHIP)
                .addBodyParameter("asal", String.valueOf(session.getUser().getData().getToko().getIdKota()))
                .addBodyParameter("tujuan", String.valueOf(id_adress))
                .addBodyParameter("berat", String.valueOf(1))
                .addBodyParameter("kurir", ship)
                .build()
                .getAsObject(ShippingResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof ShippingResponse) {
                            shippingAdapter.swap(((ShippingResponse) response).getData().get(0).getCosts());
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


    public boolean getKurir() {
        if (shippingShow) {
            ((CheckOutActivity) getActivity()).setKurir(id_adress, jenis, kurir, ongkir);
            ((CheckOutActivity) getActivity()).getReview().setAlamat(alamat_review);
            ((CheckOutActivity) getActivity()).getReview().setAlamat_detail(alamat_detail);
            ((CheckOutActivity) getActivity()).getReview().setNo_hp(no_hp);
            ((CheckOutActivity) getActivity()).getReview().setShipping(shipping);
            return true;
        } else {
            CommonUtil.showToast(getActivity(), "Anda Belum memilih pengiriman  !");
            return false;
        }
    }

}
