package com.jpp.androidchallenge.ui.extention;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;


public class FlipDownItemAnimator extends PendingItemAnimator {

    public FlipDownItemAnimator() {
        setAddDuration(1000);
        setRemoveDuration(500);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return true;
    }

    @Override
    protected boolean prepHolderForAnimateRemove(RecyclerView.ViewHolder holder) {
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateRemoveImpl(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationY(90)
                .translationX(-(holder.itemView.getMeasuredWidth() / 4))
                .scaleX(0.5F)
                .scaleY(0.5F)
                .setInterpolator(new AccelerateInterpolator());
    }

    @Override
    protected void onRemoveCanceled(RecyclerView.ViewHolder holder) {
        ViewCompat.setRotationY(holder.itemView, 0);
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setScaleX(holder.itemView, 1);
        ViewCompat.setScaleY(holder.itemView, 1);
    }

    @Override
    protected boolean prepHolderForAnimateAdd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -(holder.itemView.getMeasuredWidth() / 2));
        ViewCompat.setRotationY(holder.itemView, -90);
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateAddImpl(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationY(0)
                .translationX(0)
                .setInterpolator(new BounceInterpolator());
    }

    @Override
    protected void onAddCanceled(RecyclerView.ViewHolder holder) {
        ViewCompat.setRotationY(holder.itemView, 0);
        ViewCompat.setTranslationX(holder.itemView, 0);
    }
}