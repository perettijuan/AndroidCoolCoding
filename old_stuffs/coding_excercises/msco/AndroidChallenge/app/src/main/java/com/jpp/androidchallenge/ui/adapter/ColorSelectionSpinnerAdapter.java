package com.jpp.androidchallenge.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.model.TaskColor;

/**
 * Custom adapter used to show the items for selecting tasks colors.
 */
public class ColorSelectionSpinnerAdapter extends BaseAdapter {

    private final LayoutInflater INFLATER;

    public ColorSelectionSpinnerAdapter(Context context) {
        INFLATER = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TaskColor.values().length;
    }

    @Override
    public Object getItem(int position) {
        return TaskColor.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = INFLATER.inflate(R.layout.spinner_selection_item, parent, false);
            holder = new ViewHolder();
            holder.txtColorSelection = (TextView) convertView.findViewById(R.id.txt_color_selection);
            holder.viewColorSelectionCircle = convertView.findViewById(R.id.view_color_selection_circle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TaskColor color = (TaskColor) getItem(position);
        holder.txtColorSelection.setText(color.getTextIdentifier());

        if (color.getIdentifier() == TaskColor.NONE.getIdentifier()) {
            holder.viewColorSelectionCircle.setVisibility(View.INVISIBLE);
        } else {
            holder.viewColorSelectionCircle.setBackgroundResource(color.getSample());
            holder.viewColorSelectionCircle.setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    private class ViewHolder {
        View viewColorSelectionCircle;
        TextView txtColorSelection;
    }
}
