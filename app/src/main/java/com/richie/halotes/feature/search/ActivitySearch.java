package com.richie.halotes.feature.search;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richie.halotes.R;
import com.richie.halotes.Utils.EndlessRecyclerViewScrollListener;
import com.richie.halotes.feature.detail.ActivityDetail;
import com.richie.halotes.model.Hit;

import java.util.ArrayList;
import java.util.List;

public class ActivitySearch extends AppCompatActivity implements ContractSearch.View {

    private ContractSearch.Presenter presenter;

    private SwipeRefreshLayout swRefresh;
    private ProgressBar progressBar;
    private RecyclerView rvList;
    private RelativeLayout rlError;
    private TextView tvError;
    private Button btnRetry;
    private LinearLayoutManager layoutManager;
    private FragmentManager fragmentManager;
    private AdapterSearch adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderView();

        presenter = new PresenterSearch(this);
        presenter.init();
    }

    @Override
    public void renderView() {
        swRefresh = (SwipeRefreshLayout) findViewById(R.id.sw_refresh);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rlError = (RelativeLayout) findViewById(R.id.rl_error);
        tvError = (TextView) findViewById(R.id.tv_error);
        btnRetry = (Button) findViewById(R.id.btn_retry);

        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        fragmentManager = getSupportFragmentManager();

        swRefresh.setRefreshing(false);
        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadAPI("",1,false);
            }
        });


        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                swRefresh.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        adapter = new AdapterSearch(this,new ArrayList<Hit>());
        rvList.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterSearch.OnItemClickListener() {
            @Override
            public void onItemClick(String title, String url) {
                openDetailPage(title,url);
            }
        });

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadAPI(presenter.getLastQuery(),presenter.getPages(),true);
            }

            @Override
            public void onFirstItemVisible(boolean isFirstItem) {
                swRefresh.setEnabled(isFirstItem);
            }
        };
        rvList.addOnScrollListener(scrollListener);


    }

    @Override
    public void showLoadingView() {
        swRefresh.setRefreshing(false);
        swRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentView(List<Hit> data) {
        swRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
        rlError.setVisibility(View.GONE);
        adapter.addList(data);
    }

    @Override
    public void showErrorView(String error) {
        swRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        rvList.setVisibility(View.GONE);
        rlError.setVisibility(View.VISIBLE);
        tvError.setText(error);
    }

    @Override
    public int getListSize() {
        return adapter.getItemCount();
    }

    @Override
    public void clearList() {
        adapter.clearList();
    }

    @Override
    public void openDetailPage(String title, String url) {
        Intent intent = new Intent(this, ActivityDetail.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem search_item = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) search_item.getActionView();
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(getResources().getColor(R.color.primary));
        searchView.setFocusable(false);
        searchView.setQueryHint("Cari");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {

                //clear the previous data in search arraylist if exist

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                presenter.checkSearch(s);
                return false;
            }
        });


        return true;
    }
}
