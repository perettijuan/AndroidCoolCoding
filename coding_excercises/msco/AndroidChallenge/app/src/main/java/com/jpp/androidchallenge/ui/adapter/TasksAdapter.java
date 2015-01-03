package com.jpp.androidchallenge.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.model.TaskColor;

/**
 * An Adapter backed by a Cursor that contains and shows the information of the tasks in the local storage.
 */
public class TasksAdapter extends CursorRecyclerViewAdapter<TasksAdapter.TasksViewHolder> {



    public TasksAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        TasksAdapter.TasksViewHolder holder = new TasksViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(TasksViewHolder viewHolder, Cursor cursor) {
        Task itemTask = Task.fromCursor(cursor);
        TaskColor color = itemTask.getTaskColor();
        if(color != TaskColor.NONE) {
            viewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(color.getBackgroundColor()));
            viewHolder.txtTask.setTextColor(mContext.getResources().getColor(color.getTextColor()));
        }
        viewHolder.txtTask.setText(itemTask.getTask());
    }


    public static class TasksViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTask;
        private CardView cardView;

        public TasksViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtTask = (TextView) itemLayoutView.findViewById(R.id.txt_task);
            cardView = (CardView) itemLayoutView.findViewById(R.id.card_view);
        }
    }
}
