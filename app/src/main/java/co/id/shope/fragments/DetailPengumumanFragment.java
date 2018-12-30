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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.WebViewActivity;
import co.id.shope.adapters.SliderAdapter;
import co.id.shope.models.DataItemPengumuman;
import co.id.shope.utils.Toko;
import co.id.shope.views.SliderIndicator;
import io.objectbox.Box;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DetailPengumumanFragment extends Fragment {
    private static final String TAG = "DetailProdukFragment";

    Unbinder unbinder;
    ProgressDialog progressDialog;

    Box productBox;
    Box cartBox;
    boolean whislist = false;
    boolean cart = false;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.parent_view)
    CoordinatorLayout parentView;


    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;
    private SliderAdapter mPagerAdapter;
    private String mimeType;
    private String encoding;
    private String html;

    DataItemPengumuman pengumuman;

    public DetailPengumumanFragment() {
        // Required empty public constructor
    }

    public static DetailPengumumanFragment newInstance(DataItemPengumuman itemProduk) {

        Bundle args = new Bundle();
        args.putSerializable("data", itemProduk);
        DetailPengumumanFragment fragment = new DetailPengumumanFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengumuman_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        pengumuman = (DataItemPengumuman) getArguments().getSerializable("data");

        tvJudul.setText(pengumuman.getJudul());
//        tvKategori.setText(pengumuman.getKategori().getNama());

        mimeType = "text/html";
        encoding = "UTF-8";
        html = pengumuman.getIsi();
        webView.loadDataWithBaseURL("", html, mimeType, encoding, "");

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Bundle data= new Bundle();
//                    data.putString("url", String.valueOf(request.getUrl()));
//                    intent.putExtras(data);
//                    Log.d(TAG, "shouldOverrideUrlLoading: " + request.getUrl());
//                }
//                startActivity(intent);
//                return true;
//            }
//        });

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
//            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
//                BarangActivity.this.layoutLoading.setVisibility(0);
//            }
//
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                Toast.makeText(BarangActivity.this.getApplicationContext(), "Tidak dapat terhubung ke server. Coba lagi nanti.", 1).show();
//                BarangActivity.this.layoutLoading.setVisibility(8);
//                BarangActivity.webView.setVisibility(8);
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                BarangActivity.this.layoutLoading.setVisibility(8);
//            }

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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }


}
