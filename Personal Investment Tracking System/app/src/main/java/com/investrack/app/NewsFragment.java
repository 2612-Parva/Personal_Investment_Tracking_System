package com.investrack.app;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.api.ApiHelper;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {


    private List<News> newsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        newsAdapter = new NewsAdapter(newsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsAdapter);

        populateNewsData();
    }

    private void populateNewsData() {
        new AsyncTask<Void, Void, List<News>>() {

            @Override
            protected List<News> doInBackground(Void... voids) {
                return ApiHelper.getInstance(getActivity()).getLatestNews();
            }

            @Override
            protected void onPostExecute(List<News> news) {
                newsList.clear();
                newsList.addAll(news);
                newsAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}

