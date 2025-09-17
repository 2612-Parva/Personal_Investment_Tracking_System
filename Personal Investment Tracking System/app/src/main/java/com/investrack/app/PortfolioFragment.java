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
import com.investrack.app.db.InvestmentData;
import com.investrack.app.db.PortfolioDao;
import com.investrack.app.db.Scheme;

import java.util.ArrayList;
import java.util.List;


public class PortfolioFragment extends Fragment {
    private AppCompatAutoCompleteTextView autoCompleteTextView;
    private SearchAdapter searchAdapter;
    private ArrayList<Scheme> searchableSchemes = new ArrayList<>();

    private RecyclerView portfolioView;
    private PortfolioAdapter portfolioAdapter;
    private List<InvestmentData> investmentList = new ArrayList<>();

    private TextView investedValue;
    private TextView currentValue;
    private TextView gainsValue;

    public PortfolioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
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

                Intent intent = new Intent(getActivity(), NewInvestmentActivity.class);
                intent.putExtra("scheme", scheme);
                startActivity(intent);
                autoCompleteTextView.setText("");
            }
        });
        populateSearchData();

        investedValue = view.findViewById(R.id.invested_value);
        currentValue = view.findViewById(R.id.current_value);
        gainsValue = view.findViewById(R.id.gains_value);

        LinearLayout titleRow = view.findViewById(R.id.title_row);
        titleRow.setClickable(false);
        titleRow.setFocusable(false);

        TextView schemeName = titleRow.findViewById(R.id.scheme_name);
        schemeName.setText("Scheme");
        schemeName.setTypeface(null, Typeface.BOLD);
        schemeName.setTextColor(getResources().getColor(R.color.purple_500));

        TextView schemeInvestedValue = titleRow.findViewById(R.id.invested_value);
        schemeInvestedValue.setText("Invested");
        schemeInvestedValue.setTypeface(null, Typeface.BOLD);
        schemeInvestedValue.setTextColor(getResources().getColor(R.color.purple_500));

        TextView schemeCurrentValue = titleRow.findViewById(R.id.current_value);
        schemeCurrentValue.setText("Current");
        schemeCurrentValue.setTypeface(null, Typeface.BOLD);
        schemeCurrentValue.setTextColor(getResources().getColor(R.color.purple_500));

        TextView schemeGains = titleRow.findViewById(R.id.gains);
        schemeGains.setText("Gains");
        schemeGains.setTypeface(null, Typeface.BOLD);
        schemeGains.setTextColor(getResources().getColor(R.color.purple_500));

        portfolioView = view.findViewById(R.id.recycler_view);

        portfolioAdapter = new PortfolioAdapter(investmentList);
        portfolioAdapter.setOnItemClickListener(new PortfolioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(InvestmentData investment) {
                showClickPopup(investment);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        portfolioView.setLayoutManager(layoutManager);
        portfolioView.setItemAnimator(new DefaultItemAnimator());
        portfolioView.setAdapter(portfolioAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        populateInvestmentData();
    }

    private void showClickPopup(final InvestmentData investment) {
        String[] options = {"View all Transactions", "Add another Investment", "Remove Investment"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select an option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), TransactionsActivity.class);
                    intent.putExtra("scheme", investment.getScheme());
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(), NewInvestmentActivity.class);
                    intent.putExtra("scheme", investment.getScheme());
                    startActivity(intent);
                } else {
                    removeFromInvestment(investment);
                }
            }
        });
        builder.show();
    }

    private void removeFromInvestment(final InvestmentData investment) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseHelper.getDatabase(getContext()).portfolioDao().delete(investment.getInvestment());
                DatabaseHelper.getDatabase(getContext()).portfolioDao().deleteAllTransactions(investment.getInvestment().getSchemeId());
                return null;
            }

            @Override
            protected void onPostExecute(Void ignore) {
                populateInvestmentData();
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

    private void populateInvestmentData() {
        new AsyncTask<Void, Void, List<InvestmentData>>() {

            private float totalCurrentValue;
            private float totalInvestedValue;

            @Override
            protected List<InvestmentData> doInBackground(Void... voids) {
                PortfolioDao db = DatabaseHelper.getDatabase(getActivity()).portfolioDao();
                totalInvestedValue = db.getTotalInvestedAmount();
                totalCurrentValue = db.getTotalCurrentAmount();
                return db.getAllInvestments();
            }

            @Override
            protected void onPostExecute(List<InvestmentData> investments) {
                investmentList.clear();
                investmentList.addAll(investments);
                portfolioAdapter.notifyDataSetChanged();

                investedValue.setText(String.format("%.2f", totalInvestedValue));
                currentValue.setText(String.format("%.2f", totalCurrentValue));

                float gains = totalCurrentValue - totalInvestedValue;
                if (gains >= 0) {
                    gainsValue.setTextColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    gainsValue.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_light));
                }

                gainsValue.setText(String.format("%.2f", gains));
            }
        }.execute();
    }

}
