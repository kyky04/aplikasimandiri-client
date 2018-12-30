package co.id.shope.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;
import co.id.shope.utils.Toko;

public class WebViewActivity extends Activity {
    private static final String TAG = "WebViewActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView webView;

    String url;
    private String mimeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        toolbar.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));
        toolbar.setTitle(Toko.get(this).getNama_toko());

        mimeType = "text/html";
        String encoding = "UTF-8";
        url = getIntent().getStringExtra("url");
        Log.d(TAG, "onCreate: "+url);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(String.valueOf(request.getUrl()));
                }
                return true;
            }
        });
    }
}
