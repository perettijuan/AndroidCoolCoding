package com.jpp.foods.ui.adapter;

import com.jpp.foods.R;
import com.jpp.foods.provider.FoodsAtLifesumContract;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * CursorAdapter used to show the available Foods as a list.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class RemoteFoodsAdapter extends CursorAdapter {


    public RemoteFoodsAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View newView = inflater.inflate(R.layout.foods_item, parent, false);
        return newView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtFoodTitle = (TextView) view.findViewById(R.id.txt_food_title);
        TextView txtFoodCategory = (TextView) view.findViewById(R.id.txt_food_category);
        String title = cursor.getString(cursor.getColumnIndex(FoodsAtLifesumContract.Foods.TITLE));
        String category = cursor.getString(cursor.getColumnIndex(FoodsAtLifesumContract.Foods.CATEGORY));        
        txtFoodTitle.setText(title);
        txtFoodCategory.setText(category);
    }

}
