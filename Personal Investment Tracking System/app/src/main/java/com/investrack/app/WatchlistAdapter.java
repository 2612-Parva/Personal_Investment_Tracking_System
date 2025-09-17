package com.investrack.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.db.Scheme;
import com.investrack.app.db.WatchlistData;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.MyViewHolder> {
    private List<WatchlistData> watchList;
    private OnItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle, date, currentRate;
        public View rootView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
            date = view.findViewById(R.id.date);
            currentRate = view.findViewById(R.id.current_rate);
            rootView = view;
        }
    }

    public WatchlistAdapter(List<WatchlistData> watchList) {
        this.watchList = watchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final WatchlistData watchlistData = watchList.get(position);

        holder.title.setText(String.format("%s (%s)",
                watchlistData.getScheme().getName(), watchlistData.getScheme().getType().toUpperCase()));

        if (Scheme.SCHEME_FD.equals(watchlistData.getScheme().getType())) {
            holder.subtitle.setText(watchlistData.getScheme().getBank());
        } else if (Scheme.SCHEME_MF.equals(watchlistData.getScheme().getType())) {
            holder.subtitle.setText(watchlistData.getScheme().getFundHouse());
        } else {
            holder.subtitle.setText(watchlistData.getScheme().getMarket());
        }

        holder.currentRate.setText(String.format("%.2f", watchlistData.getScheme().getCurrentRate()));
        
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        holder.date.setText(sdf.format(watchlistData.getDate()));

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(watchlistData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return watchList.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(WatchlistData watchlistData);
    }
}


