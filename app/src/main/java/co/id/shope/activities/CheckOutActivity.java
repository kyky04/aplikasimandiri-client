package co.id.shope.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.adapters.ViewPagerAdapter;
import co.id.shope.data.Session;
import co.id.shope.fragments.CartPembayaranFragment;
import co.id.shope.fragments.CartProdukFragment;
import co.id.shope.fragments.CartReviewFragment;
import co.id.shope.fragments.CartShippingFragment;
import co.id.shope.models.ProdukOrder;
import co.id.shope.models.Review;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Toko;
import co.id.shope.views.NoSwipeableViewPager;
import okhttp3.Response;

import static co.id.shope.data.Contans.ORDER;

public class CheckOutActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActionBar actionBar;
    Fragment fragment;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    NoSwipeableViewPager viewPager;
    @BindView(R.id.tv_total_produk)
    TextView tvTotalProduk;
    @BindView(R.id.btn_back)
    TextView btnBack;
    @BindView(R.id.btn_selanjutnya)
    Button btnSelanjutnya;
    @BindView(R.id.nested)
    LinearLayout nested;

    Session session;


    private ViewPagerAdapter mPagerAdapter;

    private int pos;

    public double total = 0;

    String jenis, kurir, ongkir;
    int id_address = 0;
    int id_bank = 0;
    private List<ProdukOrder> listProduk;

    Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        session = new Session(this);
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
        review = new Review();

        Bundle i = getIntent().getExtras();
        listProduk = (List<ProdukOrder>) i.getSerializable("list-produk");
        total = i.getInt("total");
        tvTotalProduk.setText(CommonUtil.getFormattedPriceIndonesia(total));


        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new CartShippingFragment());
        mPagerAdapter.addFragment(new CartPembayaranFragment());
        mPagerAdapter.addFragment(new CartReviewFragment());

        viewPager.setAdapter(mPagerAdapter);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, fragment).addToBackStack(null).commit();
    }

    @OnClick({R.id.btn_back, R.id.btn_selanjutnya})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                back(viewPager.getCurrentItem());
                break;
            case R.id.btn_selanjutnya:
                next(viewPager.getCurrentItem());
                break;
        }
    }

    public void setTotal(double hasil) {
        total = hasil;
        tvTotalProduk.setText(CommonUtil.getFormattedPriceIndonesia(total));
    }

    public double getTotal() {
        return total;
    }

    private CartProdukFragment cartFragment() {
        CartProdukFragment fragment = (CartProdukFragment) mPagerAdapter.instantiateItem(viewPager, 0);
        return fragment;
    }

    private CartShippingFragment kirimFragment() {
        CartShippingFragment fragment = (CartShippingFragment) mPagerAdapter.instantiateItem(viewPager, 0);
        return fragment;
    }

    private CartPembayaranFragment pembayaranFragment() {
        CartPembayaranFragment fragment = (CartPembayaranFragment) mPagerAdapter.instantiateItem(viewPager, 1);
        return fragment;
    }

    private CartReviewFragment reviewFragment() {
        CartReviewFragment fragment = (CartReviewFragment) mPagerAdapter.instantiateItem(viewPager, 2);
        return fragment;
    }


    public void setKurir(int id_address, String jenis, String kurir, String ongkir) {
        this.id_address = id_address;
        this.jenis = jenis;
        this.kurir = kurir;
        this.ongkir = ongkir;
    }

    public void setBank(int id_bank) {
        this.id_bank = id_bank;

    }

    void next(int pos) {
        if (pos == 0) {
            if (kirimFragment().getKurir()) {
                viewPager.setCurrentItem(pos + 1);
            }
        } else if (pos == 1) {
            if (pembayaranFragment().getIdbank()) {
                viewPager.setCurrentItem(pos + 1);
                reviewFragment().setReview();
            }
        } else {
            showChekOut();
        }
    }

    void back(int pos) {
        if (pos != 0) {
            viewPager.setCurrentItem(pos - 1);
        } else if (pos == 0) {
            batal();
        }
    }


    public void order() {
        DialogUtil.openDialog(this);
        Log.d(TAG, "Order: " + kurir + " " + jenis + " " + ongkir);
        Log.d(TAG, "Order: Address " + id_address);
        Log.d(TAG, "Order: User " + String.valueOf(session.getUser().getData().getId()));
        Log.d(TAG, "Order: id_toko " + Toko.get(this).getId_toko());
        Log.d(TAG, "Order: id_bank" + id_bank);
        ANRequest.MultiPartBuilder postRequestBuilder = new ANRequest.MultiPartBuilder<>(ORDER);
        postRequestBuilder.addMultipartParameter("id_address", String.valueOf(id_address));
        postRequestBuilder.addMultipartParameter("id_user", String.valueOf(session.getUser().getData().getId()));
        postRequestBuilder.addMultipartParameter("id_toko", String.valueOf(Toko.get(this).getId_toko()));
        postRequestBuilder.addMultipartParameter("ongkos_kirim", ongkir);
        postRequestBuilder.addMultipartParameter("keterangan", "Yayayaya");
        postRequestBuilder.addMultipartParameter("created_by", String.valueOf(session.getUser().getData().getId()));
        if(kurir.equals("cod")){
            postRequestBuilder.addMultipartParameter("cod", String.valueOf(true));
        }else {
            postRequestBuilder.addMultipartParameter("cod", String.valueOf(false));
            postRequestBuilder.addMultipartParameter("id_bank", String.valueOf(id_bank));
            postRequestBuilder.addMultipartParameter("kurir", kurir);
            postRequestBuilder.addMultipartParameter("jenis", jenis);

        }

        for (int i = 0; i < listProduk.size(); i++) {
            Log.d(TAG, "Order: Produk " + listProduk.get(i).getId_produk() +" Harga " + listProduk.get(i).getHarga()+" qty " + listProduk.get(i).getQuantity());
            postRequestBuilder.addMultipartParameter("produk[" + i + "]", String.valueOf(listProduk.get(i).getId_produk()));
            postRequestBuilder.addMultipartParameter("harga[" + i + "]", String.valueOf(listProduk.get(i).getHarga()));
            postRequestBuilder.addMultipartParameter("qty[" + i + "]", String.valueOf(listProduk.get(i).getQuantity()));

        }

        postRequestBuilder.build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        DialogUtil.closeDialog();
                        if (response.isSuccessful()) {
                            CommonUtil.showToast(CheckOutActivity.this, "Order Succes");
                            finish();
                        } else {
                            CommonUtil.showToast(CheckOutActivity.this, "Order Gagal, Coba lagi");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        DialogUtil.closeDialog();
                        CommonUtil.showToast(CheckOutActivity.this, "Error Koneksi");
                        finish();
                    }
                });
    }

    public void showChekOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Anda yakin melakukan transaksi ini ?")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        order();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    public void batal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Anda yakin membatalkan transaksi ini ?")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        supportFinishAfterTransition();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    public List<ProdukOrder> getListProduk() {
        return listProduk;
    }

    public Review getReview() {
        return review;
    }

    @Override
    public void onBackPressed() {
        batal();
    }
}
