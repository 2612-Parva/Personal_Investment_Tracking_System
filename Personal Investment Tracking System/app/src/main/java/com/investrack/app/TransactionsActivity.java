package com.investrack.app;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.db.DatabaseHelper;
import com.investrack.app.db.Scheme;
import com.investrack.app.db.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private RecyclerView transactionView;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> transactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Scheme scheme = getIntent().getParcelableExtra("scheme");

        TextView schemeNameTV = findViewById(R.id.scheme_name);
        schemeNameTV.setText(scheme.getName());

        TextView schemeSubtitleTV = findViewById(R.id.scheme_subtitle);
        TextView schemeSubtitleValueTV = findViewById(R.id.scheme_subtitle_value);

        if (Scheme.SCHEME_FD.equals(scheme.getType())) {
            schemeSubtitleTV.setText("Bank : ");
            schemeSubtitleValueTV.setText(scheme.getBank());

        } else if (Scheme.SCHEME_MF.equals(scheme.getType())) {
            schemeSubtitleTV.setText("Fundhouse : ");
            schemeSubtitleValueTV.setText(scheme.getFundHouse());

        } else {
            schemeSubtitleTV.setText("Market : ");
            schemeSubtitleValueTV.setText(scheme.getMarket());
        }


        LinearLayout titleRow = findViewById(R.id.title_row);

        TextView type = titleRow.findViewById(R.id.type);
        type.setText("Type");
        type.setTypeface(null, Typeface.BOLD);
        type.setTextColor(getResources().getColor(R.color.purple_500));

        TextView date = titleRow.findViewById(R.id.date);
        date.setText("Date");
        date.setTypeface(null, Typeface.BOLD);
        date.setTextColor(getResources().getColor(R.color.purple_500));

        TextView units = titleRow.findViewById(R.id.units);
        if (Scheme.SCHEME_FD.equals(scheme.getType())) {
            units.setText("Rate (%)");
        } else {
            units.setText("Quantity");
        }
        units.setTypeface(null, Typeface.BOLD);
        units.setTextColor(getResources().getColor(R.color.purple_500));

        TextView amount = titleRow.findViewById(R.id.amount);
        amount.setText("Amount");
        amount.setTypeface(null, Typeface.BOLD);
        amount.setTextColor(getResources().getColor(R.color.purple_500));

        transactionView = findViewById(R.id.recycler_view);

        transactionAdapter = new TransactionAdapter(transactionList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionView.setLayoutManager(layoutManager);
        transactionView.setItemAnimator(new DefaultItemAnimator());
        transactionView.setAdapter(transactionAdapter);
        populateTransactionData(scheme.getId());
    }

    private void populateTransactionData(final int schemeId) {
        new AsyncTask<Void, Void, List<Transaction>>() {

            @Override
            protected List<Transaction> doInBackground(Void... voids) {
                return DatabaseHelper.getDatabase(TransactionsActivity.this).portfolioDao().getAllTransactions(schemeId);
            }

            @Override
            protected void onPostExecute(List<Transaction> investments) {
                transactionList.clear();
                transactionList.addAll(investments);
                transactionAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
