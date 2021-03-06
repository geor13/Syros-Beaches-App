package com.example.beachapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BeachesAdapter extends ArrayAdapter<SingleBeach> {
    public BeachesAdapter(Context context, ArrayList<SingleBeach> beachlist){
        super(context,0,beachlist);
    }

    public View getView(int position, View convertView, final ViewGroup parent){
        final SingleBeach beach = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_list, parent, false);
        }

        ConstraintLayout theList = (ConstraintLayout)convertView.findViewById(R.id.one_list);

        theList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), BeachDetail.class); //SOS
                intent.putExtra("THE_BEACH", beach);
                parent.getContext().startActivity(intent);
            }
        });

        TextView movieTitle = (TextView)convertView.findViewById(R.id.beach_name);
        movieTitle.setText(beach.getName());

        ImageView imageBeach = (ImageView)convertView.findViewById(R.id.beach_image_list);
        Glide.with(parent.getContext()).load(beach.getView()).into(imageBeach);
        return convertView;
    }
}
