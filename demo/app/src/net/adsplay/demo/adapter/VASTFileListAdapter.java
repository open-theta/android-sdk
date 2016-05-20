//
//  VASTFileListAdapter.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.demo.adapter;

import java.util.ArrayList;

import net.adsplay.demo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class VASTFileListAdapter extends ArrayAdapter<VASTListItem> {
 
        private final Context context;
        private final ArrayList<VASTListItem> itemsArrayList;
 
        public VASTFileListAdapter(Context context, ArrayList<VASTListItem> itemsArrayList) {
 
            super(context, R.layout.row, itemsArrayList);
 
            this.context = context;
            this.itemsArrayList = itemsArrayList;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.row, parent, false);
 
            // 3. Get the two text view from the rowView
            TextView labelView = (TextView) rowView.findViewById(R.id.label);
            TextView valueView = (TextView) rowView.findViewById(R.id.value);
 
            // 4. Set the text for textView 
            labelView.setText(itemsArrayList.get(position).getTitle());
            valueView.setText(itemsArrayList.get(position).getDescription());
 
            // 5. return rowView
            return rowView;
        }
}