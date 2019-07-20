package com.richie.halotes.feature.search;

import com.richie.halotes.model.Hit;

import java.util.List;

public interface ContractSearch {
    interface View{
        void renderView();
        void showLoadingView();
        void showContentView(List<Hit> data);
        void showErrorView(String error);
        int getListSize();
        void clearList();
        void openDetailPage(String title, String url);
    }

    interface Presenter{
        void init();
        void destroy();
        void loadAPI(String query, int page, boolean isLoadMore);
        void checkErrorView(String error);
        void reload(String query, int page);
        void checkSearch(String query);
        int getPages();
        String getLastQuery();
    }
}
