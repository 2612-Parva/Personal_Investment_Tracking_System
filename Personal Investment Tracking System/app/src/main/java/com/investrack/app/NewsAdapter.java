package com.investrack.app;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<News> newsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, time;
        public View rootView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            time = view.findViewById(R.id.time);
            rootView = view;
        }
    }


    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(Html.fromHtml(news.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        holder.time.setText(Utils.formatTime(news.getPublishedAt()));

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.launchURL(view.getContext(), news.getUrl());
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
