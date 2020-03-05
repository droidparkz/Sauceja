package in.droidparkz.sauceja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Paymentconfirm extends AppCompatActivity {

    WebView mWebView;
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentconfirm);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mWebView = (WebView) findViewById(R.id.splashgatewayconfirm);
        mWebView.loadUrl("file:///android_asset/gatewayconfirm.gif");
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        mWebView.setWebViewClient(new WebViewClient());

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Toast.makeText(Paymentconfirm.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Paymentconfirm.this,Homewindow.class);
                Paymentconfirm.this.startActivity(intent);
                Paymentconfirm.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}