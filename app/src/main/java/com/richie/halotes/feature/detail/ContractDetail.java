package com.richie.halotes.feature.detail;

import android.content.Intent;

import com.richie.halotes.model.Hit;

import java.util.List;

public interface ContractDetail {
    interface View{
        void renderView();
        void loadUrl(String title, String url);
        void onBackpressed();
    }

    interface Presenter{
        void init(Intent intent);
        void destroy();
    }
}
