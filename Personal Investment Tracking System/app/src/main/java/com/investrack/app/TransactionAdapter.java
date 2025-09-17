package com.investrack.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.db.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    private List<Transaction> transactionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView type, date, units, amount;
        public View rootView;

        public MyViewHolder(View view) {
            super(view);
            type = view.findViewById(R.id.type);
            date = view.findViewById(R.id.date);
            units = view.findViewById(R.id.units);
            amount = view.findViewById(R.id.amount);
            rootView = view;
        }
    }

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Transaction transaction = transactionList.get(position);

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        holder.date.setText(sdf.format(transaction.getDate()));
        holder.type.setText(transaction.getType());
        holder.units.setText(String.format("%.2f", transaction.getRate()));

        if (transaction.getType().equals("BUY")) {
            holder.amount.setText(String.format("%.2f", transaction.getAmount()));
        } else {
            holder.amount.setText(String.format("%.2f", (-transaction.getAmount())));
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}


