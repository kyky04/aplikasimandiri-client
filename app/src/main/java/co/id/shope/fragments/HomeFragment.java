package co.id.shope.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.WebViewActivity;
import co.id.shope.adapters.KategoriHomeAdapter;
import co.id.shope.adapters.KategoriVerticalAdapter;
import co.id.shope.adapters.ProductAdapterV;
import co.id.shope.adapters.SliderAdapter;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.DataItemProduk;
import co.id.shope.models.DataItemSlider;
import co.id.shope.models.KategoriResponse;
import co.id.shope.models.KategoriVertical;
import co.id.shope.models.ProdukResponse;
import co.id.shope.models.SliderResponse;
import co.id.shope.models.toko.DataItemToko;
import co.id.shope.models.toko.ShowToko;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Tools;
import co.id.shope.views.SliderIndicator;
import co.id.shope.views.SpacingItemDecoration;

import static co.id.shope.data.Contans.TOKO;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {


    SliderAdapter mPagerAdapter;
    ProductAdapterV adapter;
    KategoriVerticalAdapter kategoriVerticalAdapter;
    Unbinder unbinder;
    @BindView(R.id.slider)
    ViewPager slider;
    @BindView(R.id.pagesContainer)
    LinearLayout pagesContainer;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScroll;

    ProgressDialog progressDialog;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.recycler_kategori)
    RecyclerView recyclerKategori;


    private String mimeType;
    private String encoding;
    private String html;

    ProductAdapterV ProductAdapterV;
    KategoriHomeAdapter kategoriHomeAdapter;

    Session session;


    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        loadSlider();


        return view;
    }

    private void loadPesanPembuka(String pesan) {
        mimeType = "text/html";
        encoding = "UTF-8";
        html = pesan;
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

    @OnClick(R.id.et_search)
    public void onViewClicked() {
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    private void setupSlider(List<DataItemSlider> sliderURL) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < sliderURL.size(); i++) {
            fragments.add(SliderFragment.newInstance(sliderURL.get(i).getGambar(), sliderURL.get(i).getKeterangan()));
        }

        mPagerAdapter = new SliderAdapter(getFragmentManager(), fragments);
        slider.setAdapter(mPagerAdapter);
        mIndicator = new SliderIndicator(getActivity(), pagesContainer, slider, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void setupProudct(List<DataItemProduk> list) {

        List<KategoriVertical> listKategori = new ArrayList<>();
        listKategori.add(new KategoriVertical("Produk Terbaru", list));
        ProductAdapterV = new ProductAdapterV(getActivity());
        ProductAdapterV.addAll(list);

//        kategoriVerticalAdapter = new KategoriVerticalAdapter(getActivity(), listKategori);
//        kategoriVerticalAdapter.setDetail(new KategoriVerticalAdapter.OnDetail() {
//            @Override
//            public void onDetailClick(DataItemProduk produk) {
//
//            }
//        });

        recycler.setAdapter(ProductAdapterV);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        ProductAdapterV.setOnItemClickListener(new ProductAdapterV.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DetailProdukFragment detailProdukFragment = DetailProdukFragment.newInstance(ProductAdapterV.getItem(position));
                openFragment(detailProdukFragment);
            }

            @Override
            public void onFavClick(int position) {

            }
        });

        ViewCompat.setNestedScrollingEnabled(recycler, false);

    }

    void loadData() {
        AndroidNetworking.get(Contans.PRODUK)
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .addQueryParameter("includes", "gambar,kategori")
                .build()
                .getAsObject(ProdukResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof ProdukResponse) {
                            setupProudct(((ProdukResponse) response).getData());
                        }
                        getToko();
                    }

                    @Override
                    public void onError(ANError anError) {
                        getToko();
                    }
                });
    }

    void loadSlider() {
        openDialog();
        AndroidNetworking.get(Contans.SLIDER)
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .build()
                .getAsObject(SliderResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof SliderResponse) {
                            if (((SliderResponse) response).getData().size() > 0) {
                                setupSlider(((SliderResponse) response).getData());
                            }
                        }
                        loadKategori();
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadKategori();
                    }
                });
    }

    void loadKategori() {
        AndroidNetworking.get(Contans.KATEGORI)
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .build()
                .getAsObject(KategoriResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof KategoriResponse) {
                            if (((KategoriResponse) response).isStatus() && ((KategoriResponse) response).getData() != null) {
                                kategoriHomeAdapter = new KategoriHomeAdapter(getActivity());
                                kategoriHomeAdapter.setOnItemClickListener(new KategoriHomeAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        openFragment(FilterKategoriFragment.newInstance((int) kategoriHomeAdapter.getItem(position).getId()));
                                    }
                                });

                                recyclerKategori.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                                recyclerKategori.setAdapter(kategoriHomeAdapter);

                                kategoriHomeAdapter.swap(((KategoriResponse) response).getData());
                            } else {
                                CommonUtil.showToast(getActivity(), "Kategori tidak di temukan");
                            }
                        }
                        loadData();
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadData();
                    }
                });
    }

    private void getToko() {

        AndroidNetworking.get(TOKO + "/{id_toko}")
                .addPathParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .build()
                .getAsObject(ShowToko.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();

                        if (response instanceof ShowToko) {
                            if (((ShowToko) response).isStatus()) {
                                DataItemToko toko = ((ShowToko) response).getData();
                                try {
                                    loadPesanPembuka(toko.getPesanPembuka());
                                } catch (NullPointerException e) {
                                    webView.setVisibility(View.GONE);
                                }
                            } else {
                                CommonUtil.showToast(getActivity(), "Terjadi kesalahan");
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        closerDialog();
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());

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
}
