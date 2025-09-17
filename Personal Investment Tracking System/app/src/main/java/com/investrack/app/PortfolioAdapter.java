package com.investrack.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.investrack.app.db.InvestmentData;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.MyViewHolder> {
    private List<InvestmentData> investmentList;
    private OnItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView schemeName, investedValue, currentValue, gains;
        public View rootView;

        public MyViewHolder(View view) {
            super(view);
            schemeName = view.findViewById(R.id.scheme_name);
            investedValue = view.findViewById(R.id.invested_value);
            currentValue = view.findViewById(R.id.current_value);
            gains = view.findViewById(R.id.gains);
            rootView = view;
        }
    }

    public PortfolioAdapter(List<InvestmentData> investmentList) {
        this.investmentList = investmentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investment_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final InvestmentData investment = investmentList.get(position);
        holder.schemeName.setText(String.format("%s (%s)",
                investment.getScheme().getName(), investment.getScheme().getType().toUpperCase()));

        holder.investedValue.setText(String.format("%.2f", investment.getInvestment().getOrigValue()));
        holder.currentValue.setText(String.format("%.2f", investment.getInvestment().getCurrValue()));

        float gainsValue = investment.getInvestment().getCurrValue() - investment.getInvestment().getOrigValue();
        if (gainsValue >= 0) {
            holder.gains.setTextColor(holder.gains.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.gains.setTextColor(holder.gains.getContext().getResources().getColor(android.R.color.holo_red_light));
        }

        holder.gains.setText(String.format("%.2f", gainsValue));

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(investment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return investmentList.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(InvestmentData investmentData);
    }

}


