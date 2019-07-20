package com.richie.halotes.feature.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.richie.halotes.R;
import com.richie.halotes.model.HighlightResult;
import com.richie.halotes.model.Hit;
import com.richie.halotes.model.Title;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterSearch extends RecyclerView.Adapter {

    private final int VIEW_ITEM = 1;

    // Add data set you need
    private List<Hit> eventData;

    private Context context;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(String title, String url);
    }


    // Constructor to provide required data and objects
    public AdapterSearch(Context context, List<Hit> eventData) {
        this.eventData       = eventData;
        this.context         = context;
    }

    @Override
    public int getItemViewType(int position) {
        //return eventData.get(position) != null ? VIEW_ITEM : VIEW_EMPTY;
        return VIEW_ITEM;

    }

    // Provide reference to the views for each data item
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info);
        }
    }

    // Create new view for recycler view item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        // Create new view from xml layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_news, parent, false);
        viewHolder = new ItemViewHolder(view);


        return viewHolder;
    }

    // Replace the content of view item
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final ItemViewHolder itemViewHolder         = (ItemViewHolder) holder;
         Hit event                             = (Hit) eventData.get(position);
         String title                           = event.getTitle();
         String url                            = event.getUrl();
         int point                        = event.getPoints();
         String author                          = event.getAuthor();
        int comment                        = event.getNumComments();

        if(title == null || title.length() == 0){
            title = event.getStoryTitle();
            if(title == null || title.length() == 0){
                title = "Tidak ada judul";
            }
        }

        if(url == null || url.length() == 0){
            url = event.getStoryUrl();
            if(url == null || url.length() == 0){
                url = "";
            }
        }

        final String urlValue = url;
        final String titleValue = title;
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    if(urlValue.length() > 0){
                        onItemClickListener.onItemClick(titleValue,urlValue);
                    }

                }
            }
        });

        String info = "";
        if(point > 0){
            info = convertRupiahFormat(point)+" Poin | "+author;
        }
        else {
            info= author;
        }

        if(comment > 0){
            info = info+" | "+convertRupiahFormat(comment)+" Komentar";
        }

        itemViewHolder.tvTitle.setText(title);
        itemViewHolder.tvInfo.setText(info);

    }

    @Override
    public int getItemCount() {
        return eventData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addList(List<Hit> list){
        if(getItemCount() > 0){
            for(int i=0;i<list.size();i++){
                eventData.add(list.get(i));
                notifyItemInserted(eventData.size()-1);
            }
        }
        else {
            eventData = list;
            notifyDataSetChanged();
        }

    }

    public String convertRupiahFormat(int nominal) {
        String rupiah;

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(otherSymbols);
        rupiah = decimalFormat.format(nominal);
        return rupiah;
    }


    public void clearList(){
        eventData.clear();
        notifyDataSetChanged();
    }

}
