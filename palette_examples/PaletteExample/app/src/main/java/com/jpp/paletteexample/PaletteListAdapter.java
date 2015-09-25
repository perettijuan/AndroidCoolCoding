package com.jpp.paletteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Juan P. Peretti
 */
public class PaletteListAdapter extends BaseAdapter {

    private List<PaletteColor> mData;
    private LayoutInflater mInflater;

    public PaletteListAdapter(List<PaletteColor> data, Context context) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder();
            holder.txtColorName = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PaletteColor color = mData.get(position);
        holder.txtColorName.setText(color.name);
        holder.txtColorName.setBackgroundColor(color.color);

        return convertView;
    }


    private class ViewHolder {
        TextView txtColorName;
    }
}
