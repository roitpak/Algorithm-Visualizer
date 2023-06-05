package com.example.rohit.algorithmvisualiser;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rohit on 4/3/2017.
 */

public class WordAdapter extends ArrayAdapter<Algorithms> {


    public WordAdapter(Activity context, ArrayList<Algorithms> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Algorithms currentAndroidFlavor = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.stextview);

        nameTextView.setText(currentAndroidFlavor.returnName());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.dtextview);

        numberTextView.setText(currentAndroidFlavor.returnDetail());


        return listItemView;
    }
}