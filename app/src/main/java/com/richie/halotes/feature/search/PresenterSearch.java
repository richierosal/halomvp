package com.richie.halotes.feature.search;

import android.content.Context;

import com.richie.halotes.model.Hit;
import com.richie.halotes.model.News;
import com.richie.halotes.rest.APIService;
import com.richie.halotes.rest.NetworkFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PresenterSearch implements ContractSearch.Presenter {
    private final String TAG = getClass().getSimpleName();
    private final Context context;
    private final ContractSearch.View view;
    private APIService api;

    private int pages = 1;
    private String query = "";

    public PresenterSearch(Context context) {
        this.context = context;
        this.view = (ContractSearch.View) context;
        api = NetworkFactory.getAPI(context);
    }

    public void init(){

        loadAPI("",1,false);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void loadAPI(String query, final int page, boolean isLoadMore) {
        if(!isLoadMore){
            view.showLoadingView();
        }
        this.query = query;
        Call<News> call = api.search(query,page);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response == null){
                    checkErrorView("Terjadi kesalahan");
                    return;
                }
                if(response.body() == null){
                    checkErrorView("Terjadi kesalahan");
                    return;
                }
                List<Hit> hits = response.body().getHits();
                if(hits == null || hits.size() == 0){
                    checkErrorView("Tidak ada data");
                    return;
                }
                pages++;
                view.showContentView(hits);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                checkErrorView(t.getMessage());
            }
        });
    }

    @Override
    public void checkErrorView(String error) {
        if(view.getListSize() == 0){
            view.showErrorView(error);
        }
    }

    @Override
    public void reload(String query, int page) {
        if(page == 1){
            view.clearList();
        }
        pages = page;
        loadAPI(query,page,false);
    }

    @Override
    public void checkSearch(String query) {
        if(query.length() == 0){
            reload("",1);
            return;
        }
        if(query.length() > 2){
            reload(query,1);
        }
    }

    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public String getLastQuery() {
        return query;
    }


}
