package com.example.beachapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BeachesAdapter extends ArrayAdapter<SingleBeach> {
    public BeachesAdapter(Context context, ArrayList<SingleBeach> beachlist){
        super(context,0,beachlist);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        SingleBeach beach = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_list, parent, false);
        }

        TextView movieTitle = (TextView)convertView.findViewById(R.id.beach_name);
        movieTitle.setText(beach.getName());
        return convertView;
    }
}
