package com.richie.halotes.feature.detail;

import android.content.Context;
import android.content.Intent;

import com.richie.halotes.feature.search.ContractSearch;
import com.richie.halotes.model.Hit;
import com.richie.halotes.model.News;
import com.richie.halotes.rest.APIService;
import com.richie.halotes.rest.NetworkFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PresenterDetail implements ContractDetail.Presenter {
    private final String TAG = getClass().getSimpleName();
    private final Context context;
    private final ContractDetail.View view;

    private int pages = 1;
    private String query = "";

    public PresenterDetail(Context context) {
        this.context = context;
        this.view = (ContractDetail.View) context;
    }


    @Override
    public void init(Intent intent) {
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        if(title == null ||title.length() == 0 || url == null || url.length() == 0){
            view.onBackpressed();
            return;
        }
        view.loadUrl(title,url);
    }

    @Override
    public void destroy() {

    }
}
