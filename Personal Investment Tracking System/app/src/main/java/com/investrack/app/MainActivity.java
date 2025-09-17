package com.investrack.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationBarView;
import com.investrack.app.api.ApiHelper;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new WatchlistFragment();
    final Fragment fragment2 = new NewsFragment();
    final Fragment fragment3 = new PortfolioFragment();
    final Fragment fragment4 = new SettingsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView navigation = findViewById(R.id.navigationView);
        navigation.setOnItemSelectedListener(navigationItemSelectedListener);

        fm.beginTransaction().add(R.id.container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.container, fragment1, "1").commit();

        // fetch all data from api

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                ApiHelper.getInstance(MainActivity.this).getAllBanks();
                ApiHelper.getInstance(MainActivity.this).getAllMutualFunds();
                ApiHelper.getInstance(MainActivity.this).getAllStocks();
                return null;
            }
        }.execute();


    }

    private NavigationBarView.OnItemSelectedListener navigationItemSelectedListener
            = new NavigationBarView.OnItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.nav_items_watchlist) {
                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
            } else if (item.getItemId() == R.id.nav_items_news) {
                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
            } else {
                fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
            }
            return true;
        }
    };
}
