package com.example.booleanmodelsearchengine_irproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.booleanmodelsearchengine_irproject.R;
import com.example.booleanmodelsearchengine_irproject.model.News;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.booleanmodelsearchengine_irproject.ui.MainActivity.NEWS_EXTRA;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        News news = getIntent().getParcelableExtra(NEWS_EXTRA);
        webView.loadUrl(news.getLink());
    }
}
