package br.ifsul.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.ifsul.R;

public class GridAdapter extends BaseAdapter {
    private Context context;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // Return the total number of items in the grid
        return 9;
    }

    @Override
    public Object getItem(int position) {
        // Return the data for a specific position
        return null;
    }

    @Override
    public long getItemId(int position) {
        // Return the ID for a specific position
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the grid item layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_item, parent, false);

        // Customize the grid item view here (e.g., set text)
        TextView textView = view.findViewById(R.id.textViewItem);
        textView.setText("Item " + (position + 1));

        return view;
    }
}
