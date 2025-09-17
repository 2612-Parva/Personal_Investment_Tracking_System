package com.investrack.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.investrack.app.db.Scheme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SearchAdapter extends ArrayAdapter<Scheme> {


    private Context context;
    private int resourceId;
    private List<Scheme> items, tempItems, suggestions;

    public SearchAdapter(@NonNull Context context, int resourceId, ArrayList<Scheme> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            Scheme scheme = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.textView);
            name.setText(scheme.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public Scheme getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return schemeFilter;
    }

    @Override
    public void addAll(Collection<? extends Scheme> collection) {
        super.addAll(collection);
        items.clear();
        items.addAll(collection);
        tempItems.clear();
        tempItems.addAll(items);
        suggestions.clear();
    }

    private Filter schemeFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Scheme scheme = (Scheme) resultValue;
            return scheme.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Scheme scheme : tempItems) {
                    if (scheme.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(scheme);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Scheme> tempValues = (ArrayList<Scheme>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Scheme schemeObj : tempValues) {
                    add(schemeObj);
                    notifyDataSetChanged();
                }
            }
        }
    };
}

