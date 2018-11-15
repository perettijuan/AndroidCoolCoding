package com.jpp.androidchallenge.ui.adapter;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.background.DeleteTaskExecutor;
import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.model.TaskColor;
import com.jpp.androidchallenge.util.Utils;

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
        // add elements to handle view swipe.
        if(Utils.isRunningOnIceCreamSandwitch()) {
            handleSwiping(itemView, holder);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TasksViewHolder viewHolder, Cursor cursor) {
        Task itemTask = Task.fromCursor(cursor);
        TaskColor color = itemTask.getTaskColor();
        if (color != TaskColor.NONE) {
            viewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(color.getBackgroundColor()));
            viewHolder.txtTask.setTextColor(mContext.getResources().getColor(color.getTextColor()));
        }
        viewHolder.txtTask.setText(itemTask.getTask());
        viewHolder.ivRemove.setOnClickListener(new DeleteIconListener(mContext, itemTask));
        viewHolder.mTask = itemTask;
    }


    /**
     * ViewHolder class required by the RecyclerView framework.
     */
    public static class TasksViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTask;
        private final CardView cardView;
        private final View ivRemove;
        private Task mTask;

        public TasksViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtTask = (TextView) itemLayoutView.findViewById(R.id.txt_task);
            cardView = (CardView) itemLayoutView.findViewById(R.id.card_view);
            ivRemove = itemLayoutView.findViewById(R.id.iv_remove);
        }


        public Task getItemTask() {
            return mTask;
        }
    }


    /**
     * OnClickListener used to detect clicks on the delete option.
     */
    private static class DeleteIconListener implements View.OnClickListener {

        private final Task mTask;
        private final Context mContext;

        DeleteIconListener(Context context, Task task) {
            mTask = task;
            mContext = context;
        }

        @Override
        public void onClick(View v) {
            deleteTask(mContext, mTask);
        }
    }


    /**
     * Method that executes the task deletion on a background thread.
     */
    private static void deleteTask(Context context, Task task) {
        DeleteTaskExecutor executor = new DeleteTaskExecutor(context, null);
        executor.execute(task);
    }


    /**
     * This method is used to create the structure needed to handle the swipes. This is an adaptation
     * from a swipe method used in a ListView. Note that probably is not performing as well as espected.
     * Some fine tunning is needed since it is the first time I ever used it.
     *
     * @param rootView
     * @param holder
     */
    private void handleSwiping(View rootView, RecyclerView.ViewHolder holder) {
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(rootView.getContext(), new FlingDetector(rootView, holder));
        rootView.setOnTouchListener(new SwipeTouchListener(rootView, holder, gestureDetector));
    }


    /**
     * Starts an animation and the delete process when performing a swipe.
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void onSwipe(final View rootView, final int position, final RecyclerView.ViewHolder holder) {
        ViewPropertyAnimator animator;
        animator = rootView.animate().translationX(-rootView.getWidth());
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Task task = ((TasksAdapter.TasksViewHolder) holder).getItemTask();
                deleteTask(rootView.getContext(), task);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }


    /**
     * A GestureDetector that listen for gestures over a View and detect if the gesture is a swipe.
     */
    private final class FlingDetector implements GestureDetector.OnGestureListener {
        final View rootView;
        final RecyclerView.ViewHolder holder;

        FlingDetector(View rootView, RecyclerView.ViewHolder holder) {
            this.rootView = rootView;
            this.holder = holder;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            {
                final int viewSwipeThreshold = rootView.getWidth() / 4;
                if (velocityX > viewSwipeThreshold) {
                    onSwipe(rootView, holder.getPosition(), holder);
                    return true;
                }
                return false;
            }
        }
    }


    private final class SwipeTouchListener implements View.OnTouchListener {
        private final float originalX = 0;
        private final float originalY = 0;
        private float startMoveX = 0;
        private float startMoveY = 0;

        final View rootView;
        final RecyclerView.ViewHolder holder;
        final GestureDetectorCompat gestureDetector;

        SwipeTouchListener(View rootView, RecyclerView.ViewHolder holder, GestureDetectorCompat detector) {
            this.rootView = rootView;
            this.holder = holder;
            gestureDetector = detector;
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int viewSwipeHorizontalThreshold = rootView.getWidth() / 3;
            final int viewSwipeVerticalThreshold = view.getContext().getResources().getDimensionPixelSize(R.dimen.vertical_swipe_threshold);
            if (gestureDetector.onTouchEvent(event))
                return true;
            final float x = event.getRawX(), y = event.getRawY();
            final float deltaX = x - startMoveX, deltaY = y - startMoveY;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    startMoveX = x;
                    startMoveY = y;
                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(deltaX) < viewSwipeHorizontalThreshold) {
                        rootView.animate().translationX(originalX).translationY(originalY).alpha(1).start();
                        if (Math.abs(deltaY) < viewSwipeHorizontalThreshold) {
                            rootView.performClick();
                        }
                    } else {
                        onSwipe(rootView, holder.getPosition(), holder);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    if (Math.abs(deltaX) < viewSwipeHorizontalThreshold
                            || Math.abs(deltaY) < viewSwipeVerticalThreshold)
                        rootView.animate().translationX(originalX).translationY(originalY).alpha(1).start();
                    else {
                        onSwipe(rootView, holder.getPosition(), holder);
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    rootView.setAlpha(Math.max(Math.min((255 - Math.abs(deltaX)) / 255f, 1.0f), 0.1f));
                    rootView.setTranslationX(deltaX);
                    break;
            }
            return true;
        }
    }
}


