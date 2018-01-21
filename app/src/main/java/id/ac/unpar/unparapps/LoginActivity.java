package id.ac.unpar.unparapps;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class LoginActivity extends AppCompatActivity {
    WebView ssoWebView;
    @Override
    protected void onStop() {
        ActivityHelper.makeToast(getApplicationContext(),"Cache Cleared");
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ssoWebView = findViewById(R.id.webview_ssoLogin);
        final ProgressBar loading = findViewById(R.id.bar_ssoProcessLogin);
        WebSettings ssoSettings = ssoWebView.getSettings();
        ssoSettings.setJavaScriptEnabled(true);
        ssoWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loading.setVisibility(View.VISIBLE);
            }
        });
        ssoWebView.loadUrl("https://sso.unpar.ac.id/login?service=https%3A%2F%2Fstudentportal.unpar.ac.id%2Fhome%2Findex.login.submit.php");
    }
}
