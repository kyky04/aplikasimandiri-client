package co.id.shope.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.WebViewActivity;
import co.id.shope.adapters.GambarAdapter;
import co.id.shope.adapters.SliderAdapter;
import co.id.shope.application.MyApp;
import co.id.shope.data.Contans;
import co.id.shope.models.DataItemCart;
import co.id.shope.models.DataItemCart_;
import co.id.shope.models.DataItemProduk;
import co.id.shope.models.DataItemProduk_;
import co.id.shope.models.GambarItem;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.Toko;
import co.id.shope.views.SliderIndicator;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DetailProdukFragment extends Fragment {
    private static final String TAG = "DetailProdukFragment";

    Unbinder unbinder;
    ProgressDialog progressDialog;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_kategori)
    TextView tvKategori;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.recycler_product)
    RecyclerView recyclerProduct;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.bt_add_to_wishlist)
    AppCompatButton btAddToWishlist;
    @BindView(R.id.bt_add_to_cart)
    AppCompatButton btAddToCart;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.parent_view)
    CoordinatorLayout parentView;

    Box productBox;
    Box cartBox;
    boolean whislist = false;
    boolean cart = false;


    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;
    private SliderAdapter mPagerAdapter;
    private String mimeType;
    private String encoding;
    private String html;

    GambarAdapter adapter;
    DataItemProduk produk;
    DataItemProduk itemProdukBox;
    DataItemCart itemCart;

    QueryBuilder<DataItemProduk> builder;
    QueryBuilder<DataItemCart> builderCart;

    public DetailProdukFragment() {
        // Required empty public constructor
    }

    public static DetailProdukFragment newInstance(DataItemProduk itemProduk) {

        Bundle args = new Bundle();
        args.putSerializable("data", itemProduk);
        DetailProdukFragment fragment = new DetailProdukFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_2, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());

        produk = (DataItemProduk) getArguments().getSerializable("data");
        productBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemProduk.class);
        cartBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemCart.class);

        refreshWishList();
        refreshCart();

        tvHarga.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(produk.getHarga())));
        tvNama.setText(produk.getNama());
        tvKategori.setText(produk.getKategori().getNama());

        mimeType = "text/html";
        encoding = "UTF-8";
        html = produk.getDeskripsi();
        webView.loadDataWithBaseURL("", html, mimeType, encoding, "");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext()).setMessage(message).setCancelable(false).setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                }).show();
                return true;
            }
        });
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        webView.setWebViewClient(new WebViewClient() {


            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return setAction(Uri.parse(url));
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return setAction(request.getUrl());
            }

            public boolean setAction(Uri uri) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                String url = uri.toString();
                if (url.endsWith("jpg") || url.endsWith("JPG") || url.endsWith("png") || url.endsWith("PNG") || url.endsWith("gif") || url.endsWith("GIF") || url.endsWith("zip") || url.endsWith("rar")) {
//                    BarangActivity.this.saveImage(url);
                    return true;
                } else if (url.startsWith("http://") || url.startsWith("https://")) {
                    intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                    return true;
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent("android.intent.action.VIEW");
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Glide.with(getActivity()).load(Contans.WEB_URL_STORAGE + produk.getGambarUtama()).into(image);

        recyclerProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new GambarAdapter(getActivity());
        recyclerProduct.setAdapter(adapter);
        adapter.add(new GambarItem(produk.getGambarUtama()));
        adapter.addAll(produk.getGambar());
        adapter.setOnItemClickListener(new GambarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Glide.with(getActivity()).load(Contans.WEB_URL_STORAGE + adapter.getItem(position).getGambar()).into(image);
            }

            @Override
            public void onFavClick(int position) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_add_to_wishlist, R.id.bt_add_to_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add_to_wishlist:
                addToWishlist();
                break;
            case R.id.bt_add_to_cart:
                addtoCart();
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    void addToWishlist() {
        if(!whislist){
            produk.setWishlist(true);
            produk.setCart(false);
            productBox.put(produk);
        }else {
            productBox.remove(produk);
        }
        refreshWishList();
    }

    void addtoCart() {
        if(!cart){
            DataItemCart cart = new DataItemCart();
            cart.setId(produk.getId());
            cart.setNama(produk.getNama());
            cart.setHarga(produk.getHarga());
            cart.setGambarUtama(produk.getGambarUtama());
            cart.setGambar(produk.getGambar());
            cart.setBerat(produk.getBerat());
            cart.setDeskripsi(produk.getDeskripsi());
            cart.setIdKategori(produk.getIdKategori());
            cart.setStok(produk.getStok());
            cart.setCart(true);
            cart.setAmount(0);

            cartBox.put(cart);
        }else {
            cartBox.remove(produk.getId());
        }
        refreshCart();


    }

    void refreshWishList() {
        builder = productBox.query();
        itemProdukBox = builder.equal(DataItemProduk_.id, produk.getId()).equal(DataItemProduk_.wishlist,true).build().findFirst();

        if (itemProdukBox != null) {
            whislist = true;
            btAddToWishlist.setText("Remove From Wishlist");
        }else {
            whislist = false;
            btAddToWishlist.setText("Add To Wishlist");
        }
    }

    void refreshCart() {
        builderCart = cartBox.query();
        itemCart = builderCart.equal(DataItemCart_.id, produk.getId()).build().findFirst();

        if (itemCart != null) {
            cart = true;
            btAddToCart.setText("Remove From Cart");
        }else {
            cart = false;
            btAddToCart.setText("Add To Cart");
        }

    }
}
