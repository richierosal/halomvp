package com.richie.halotes.feature.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richie.halotes.R;
import com.richie.halotes.Utils.EndlessRecyclerViewScrollListener;
import com.richie.halotes.feature.search.AdapterSearch;
import com.richie.halotes.feature.search.ContractSearch;
import com.richie.halotes.feature.search.PresenterSearch;
import com.richie.halotes.model.Hit;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetail extends AppCompatActivity implements ContractDetail.View {

    private ContractDetail.Presenter presenter;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        renderView();

        presenter = new PresenterDetail(this);
        presenter.init(getIntent());
    }

    @Override
    public void renderView() {

        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    @Override
    public void loadUrl(String title, String url) {
        webView.loadUrl(url);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackpressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
