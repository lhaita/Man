package liuhao.bawei.com.man.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import liuhao.bawei.com.man.R;

public class WebMainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_main);

        webView = (WebView) findViewById(R.id.web);
        webView.loadUrl("http://baidu.com");


    }
}
