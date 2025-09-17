package com.investrack.app;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.investrack.app.db.DatabaseHelper;
import com.investrack.app.db.Investment;
import com.investrack.app.db.PortfolioDao;
import com.investrack.app.db.Scheme;
import com.investrack.app.db.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewInvestmentActivity extends AppCompatActivity {

    private Spinner typeDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_investment);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Scheme scheme = getIntent().getParcelableExtra("scheme");

        TextView schemeNameTV = findViewById(R.id.scheme_name);
        schemeNameTV.setText(scheme.getName());

        TextView schemeSubtitleTV = findViewById(R.id.scheme_subtitle);
        TextView schemeSubtitleValueTV = findViewById(R.id.scheme_subtitle_value);
        TextView rateTV = findViewById(R.id.rate);

        if (Scheme.SCHEME_FD.equals(scheme.getType())) {
            schemeSubtitleTV.setText("Bank : ");
            schemeSubtitleValueTV.setText(scheme.getBank());
            rateTV.setText("Rate of Interest (%) : ");
        } else if (Scheme.SCHEME_MF.equals(scheme.getType())) {
            schemeSubtitleTV.setText("Fundhouse : ");
            schemeSubtitleValueTV.setText(scheme.getFundHouse());
            rateTV.setText("NAV : ");
        } else {
            schemeSubtitleTV.setText("Market : ");
            schemeSubtitleValueTV.setText(scheme.getMarket());
            rateTV.setText("Share Price : ");
        }
        EditText rateET = findViewById(R.id.rate_value);
        rateET.setHint(String.valueOf(scheme.getCurrentRate()));

        populateDropdownData(scheme.getId());

        final Calendar cal = Calendar.getInstance();

        final EditText dateET = findViewById(R.id.date_value);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateET.setText(sdf.format(cal.getTime()));
            }

        };

        dateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewInvestmentActivity.this, dateSetListener, cal
                        .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText amountET = findViewById(R.id.amount_value);

        typeDropdown = findViewById(R.id.type_value);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String type = (String) typeDropdown.getSelectedItem();
                    float rate = Float.parseFloat(rateET.getText().toString());
                    float amount = Float.parseFloat(amountET.getText().toString());
                    Date date = cal.getTime();

                    Transaction trans = new Transaction(type, date, rate, amount, scheme.getId());
                    updateInvestmentData(trans, scheme);
                } catch (Exception e) {
                    Toast.makeText(NewInvestmentActivity.this,
                            "Please enter all inputs correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateInvestmentData(final Transaction transaction, final Scheme scheme) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseHelper db = DatabaseHelper.getDatabase(NewInvestmentActivity.this);

                db.portfolioDao().add(transaction);

                Investment currInv = db.portfolioDao().getInvestment(transaction.getSchemeId());
                List<Transaction> transactions = db.portfolioDao().getAllTransactions(scheme.getId());

                if (currInv == null) {
                    currInv = new Investment(transaction.getSchemeId(), 0, transaction.getAmount());
                    currInv.setCurrValue(getCurrentValue(currInv, transactions, scheme));
                    db.portfolioDao().add(currInv);
                } else {
                    if (transaction.getType().equals("BUY")) {
                        currInv.setOrigValue(currInv.getOrigValue() + transaction.getAmount());
                        currInv.setCurrValue(getCurrentValue(currInv, transactions, scheme));
                    } else {
                        currInv.setOrigValue(currInv.getOrigValue() - transaction.getAmount());
                        // TODO update current currentValue
                    }
                    db.portfolioDao().update(currInv);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void ignore) {
                finish();
            }
        }.execute();
    }

    private float getCurrentValue(Investment investment, List<Transaction> transactions, Scheme scheme) {
        if (scheme.getType().equals(Scheme.SCHEME_FD)) {
            float interest = 0;
            for (Transaction transaction : transactions) {
                long difference = Math.abs(new Date().getTime() - transaction.getDate().getTime());
                long days = difference / (24 * 60 * 60 * 1000);
                interest = interest + (investment.getOrigValue() * transaction.getRate() * days / 36500);
            }
            return investment.getOrigValue() + interest;
        } else {
            float totalQuantity = 0;
            for (Transaction transaction : transactions) {
                totalQuantity = totalQuantity + transaction.getAmount() / transaction.getRate();
            }
            return totalQuantity * scheme.getCurrentRate();
        }
    }

    private void populateDropdownData(final int schemeId) {
        new AsyncTask<Void, Void, Investment>() {

            @Override
            protected Investment doInBackground(Void... voids) {
                PortfolioDao db = DatabaseHelper.getDatabase(NewInvestmentActivity.this).portfolioDao();
                return db.getInvestment(schemeId);
            }

            @Override
            protected void onPostExecute(Investment investment) {
                String[] items;
                if (investment == null) {
                    items = new String[]{"BUY"};
                } else {
                    items = new String[]{"BUY", "SELL"};
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(NewInvestmentActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, items);
                typeDropdown.setAdapter(adapter);
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
