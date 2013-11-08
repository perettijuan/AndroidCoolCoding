package com.jpp.animations.ui.adapter;

import java.util.HashMap;
import java.util.List;

import com.jpp.animations.AnimationGroups;
import com.jpp.animations.Animations;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class SliderMenuAdapter extends BaseExpandableListAdapter {


    private Context mContext;
    private List<AnimationGroups> mHeaderData;
    private HashMap<AnimationGroups, List<Animations>> mGroupData;

    public SliderMenuAdapter(Context context, List<AnimationGroups> headerData,
            HashMap<AnimationGroups, List<Animations>> groupdata) {
        mContext = context;
        mHeaderData = headerData;
        mGroupData = groupdata;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupData.get(mHeaderData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = ((Animations) getChild(groupPosition, childPosition)).getName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView title = (TextView) convertView.findViewById(android.R.id.text1);
        title.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupData.get(mHeaderData.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mHeaderData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = ((AnimationGroups) getGroup(groupPosition)).getName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView txtGroupTitle = (TextView) convertView.findViewById(android.R.id.text1);
        txtGroupTitle.setTypeface(null, Typeface.BOLD);
        txtGroupTitle.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {        
        return true;
    }

}
