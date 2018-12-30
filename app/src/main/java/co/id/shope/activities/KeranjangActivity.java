package co.id.shope.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.adapters.CartAdapter;
import co.id.shope.application.MyApp;
import co.id.shope.models.DataItemCart;
import co.id.shope.models.ProdukOrder;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.Toko;
import co.id.shope.views.PragmaRecyclerView;
import co.id.shope.views.SliderIndicator;
import io.objectbox.Box;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class KeranjangActivity extends AppCompatActivity {
    private static final String TAG = "CartProdukFragment";

    private ActionBar actionBar;
    @BindView(R.id.recyclerView)
    PragmaRecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.price_total)
    TextView priceTotal;
    @BindView(R.id.bt_add_to_cart)
    AppCompatButton btAddToCart;
    @BindView(R.id.btn_back)
    TextView btnBack;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    CartAdapter adapter;
    Box catBox;

    int total = 0;
    List<ProdukOrder> produkOrderList = new ArrayList<>();

    public KeranjangActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);


        initView();
        initToolbar();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(Toko.get(this).getNama_toko());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));
    }


    private void initView() {
        recycler.vertical();
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        priceTotal.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(0)));

        catBox = ((MyApp) getApplication()).getBoxStore().boxFor(DataItemCart.class);

        adapter = new CartAdapter(this, new CartAdapter.OnItemClickListener() {
            @Override
            public void onAdd(int position) {
                Log.d(TAG, "onAdd: " + position);
                priceTotal.setText(CommonUtil.getFormattedPriceIndonesia(adapter.getTotal()));
            }

            @Override
            public void onMinus(int position) {
                priceTotal.setText(CommonUtil.getFormattedPriceIndonesia(adapter.getTotal()));
            }

            @Override
            public void onDelete(int pos) {
                catBox.remove(adapter.getItem(pos
                ));
                adapter.remove(adapter.getItem(pos));
                priceTotal.setText(CommonUtil.getFormattedPriceIndonesia(adapter.getTotal()));
                if (adapter.getItemCount() == 0) {
                    recycler.showEmpty();
                }
            }
        });
        adapter.addAll(catBox.getAll());
        if (!CommonUtil.isEmpty(adapter.getListItem())) {
            recycler.setAdapter(adapter);
        } else {
            recycler.showEmpty();
        }

    }


    @OnClick({R.id.btn_back, R.id.bt_add_to_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                supportFinishAfterTransition();
                break;
            case R.id.bt_add_to_cart:
                goToChart();
                break;
        }
    }

    private void goToChart() {
        total = (int) adapter.getTotal();
        if (total > 0) {
            for (int i = 0; i < adapter.getItemCount(); i++) {
                DataItemCart cart = adapter.getItem(i);
                produkOrderList.add(new ProdukOrder((int) cart.getId(), Double.valueOf(cart.getHarga()), cart.getAmount(),cart.getNama(),cart.getGambarUtama()));
            }
            Bundle bundle = new Bundle();
            bundle.putInt("total", total);
            bundle.putSerializable("list-produk", (Serializable) produkOrderList);
            Intent intent = new Intent(this, CheckOutActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            CommonUtil.showSnack(this, "Anda belum menambahkan barang !");
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }


    void openDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    void closerDialog() {
        progressDialog.dismiss();
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    @Override
    public void onResume() {
        super.onResume();
//        catBox = ((MyApp) getApplication()).getBoxStore().boxFor(DataItemCart.class);
//
////        adapter = new CartAdapter(getActivity());
//        adapter.swap(catBox.getAll());
//        recycler.setAdapter(adapter);
    }


}
