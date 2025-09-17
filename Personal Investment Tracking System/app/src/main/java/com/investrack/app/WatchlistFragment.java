package com.investrack.app;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.db.DatabaseHelper;
import com.investrack.app.db.Scheme;
import com.investrack.app.db.Watchlist;
import com.investrack.app.db.WatchlistData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WatchlistFragment extends Fragment {

    private AppCompatAutoCompleteTextView autoCompleteTextView;
    private SearchAdapter searchAdapter;
    private ArrayList<Scheme> searchableSchemes = new ArrayList<>();

    private RecyclerView watchListView;
    private WatchlistAdapter watchListAdapter;
    private List<WatchlistData> watchList = new ArrayList<>();

    public WatchlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autoCompleteTextView = view.findViewById(R.id.auto_text_view);

        searchAdapter = new SearchAdapter(getContext(), R.layout.search_row, searchableSchemes);
        autoCompleteTextView.setAdapter(searchAdapter);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    autoCompleteTextView.showDropDown();
                }
            }
        });
        // handle click event and set desc on textview
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Scheme scheme = (Scheme) adapterView.getItemAtPosition(i);

                addToWatchlist(scheme);

                autoCompleteTextView.setText("");
            }
        });
        populateSearchData();

        LinearLayout titleRow = view.findViewById(R.id.title_row);
        titleRow.setClickable(false);
        titleRow.setFocusable(false);

        TextView schemeName = titleRow.findViewById(R.id.title);
        schemeName.setText("Scheme");
        schemeName.setTypeface(null, Typeface.BOLD);
        schemeName.setTextColor(getResources().getColor(R.color.purple_500));

        TextView subtitle = titleRow.findViewById(R.id.subtitle);
        subtitle.setVisibility(View.GONE);

        TextView currentRate = titleRow.findViewById(R.id.current_rate);
        currentRate.setText("Rate");
        currentRate.setTypeface(null, Typeface.BOLD);
        currentRate.setTextColor(getResources().getColor(R.color.purple_500));

        TextView date = titleRow.findViewById(R.id.date);
        date.setText("Date");
        date.setTypeface(null, Typeface.BOLD);
        date.setTextColor(getResources().getColor(R.color.purple_500));


        watchListView = view.findViewById(R.id.recycler_view);

        watchListAdapter = new WatchlistAdapter(watchList);
        watchListAdapter.setOnItemClickListener(new WatchlistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WatchlistData watchlistData) {
                showClickPopup(watchlistData);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        watchListView.setLayoutManager(layoutManager);
        watchListView.setItemAnimator(new DefaultItemAnimator());
        watchListView.setAdapter(watchListAdapter);
        populateWatchlistData();
    }

    private void showClickPopup(final WatchlistData watchlistData) {
        String[] options = {"Add new Investment", "Remove from Watchlist"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select an option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), NewInvestmentActivity.class);
                    intent.putExtra("scheme", watchlistData.getScheme());
                    startActivity(intent);
                } else {
                    removeFromWatchlist(watchlistData);
                }
            }
        });
        builder.show();
    }

    private void removeFromWatchlist(final WatchlistData watchlistData) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Watchlist watchlist = new Watchlist(watchlistData.getScheme().getId(), new Date());
                DatabaseHelper.getDatabase(getContext()).watchlistDao().delete(watchlist);
                return null;
            }

            @Override
            protected void onPostExecute(Void ignore) {
                populateWatchlistData();
            }
        }.execute();
    }

    private void addToWatchlist(final Scheme scheme) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Watchlist watchlist = new Watchlist(scheme.getId(), new Date());
                DatabaseHelper.getDatabase(getContext()).watchlistDao().add(watchlist);
                return null;
            }

            @Override
            protected void onPostExecute(Void ignore) {
                populateWatchlistData();
            }
        }.execute();
    }

    private void populateSearchData() {
        new AsyncTask<Void, Void, List<Scheme>>() {

            @Override
            protected List<Scheme> doInBackground(Void... voids) {
                return DatabaseHelper.getDatabase(getActivity()).schemeDao().getAllSchemes();
            }

            @Override
            protected void onPostExecute(List<Scheme> schemes) {
                searchableSchemes.clear();
                searchableSchemes.addAll(schemes);
                searchAdapter.addAll(schemes);
                searchAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void populateWatchlistData() {
        new AsyncTask<Void, Void, List<WatchlistData>>() {

            @Override
            protected List<WatchlistData> doInBackground(Void... voids) {
                return DatabaseHelper.getDatabase(getActivity()).watchlistDao().getWatchlist();
            }

            @Override
            protected void onPostExecute(List<WatchlistData> schemes) {
                watchList.clear();
                watchList.addAll(schemes);
                watchListAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
