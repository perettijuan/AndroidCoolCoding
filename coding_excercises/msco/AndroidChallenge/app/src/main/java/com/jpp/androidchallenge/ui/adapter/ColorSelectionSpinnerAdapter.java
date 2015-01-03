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
 * Created by jperett on 02/01/2015.
 */
public class ColorSelectionSpinnerAdapter extends BaseAdapter {

    private final LayoutInflater INFLATER;

    public ColorSelectionSpinnerAdapter(Context context) {
        INFLATER = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        // plus 1 to show the none selection
        return TaskColor.values().length + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return null;
        } else {
            return TaskColor.values()[position - 1];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = INFLATER.inflate(R.layout.spinner_selection_item, parent, false);
            holder = new ViewHolder();
            holder.txtColorSelection = (TextView) convertView.findViewById(R.id.txt_color_selection);
            holder.viewColorSelectionCircle = convertView.findViewById(R.id.view_color_selection_circle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position == 0) {
            holder.txtColorSelection.setText(R.string.color_none);
            holder.viewColorSelectionCircle.setVisibility(View.INVISIBLE);
        } else {
            TaskColor color = (TaskColor) getItem(position);
            holder.txtColorSelection.setText(color.getTextIdentifier());
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
