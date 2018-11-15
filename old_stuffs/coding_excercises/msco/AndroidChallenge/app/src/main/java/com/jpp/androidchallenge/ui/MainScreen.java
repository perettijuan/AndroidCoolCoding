package com.jpp.androidchallenge.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.background.IBackgroundExecutionListener;
import com.jpp.androidchallenge.ui.extention.FloatingActionButton;
import com.jpp.androidchallenge.ui.fragment.AddTaskFragment;
import com.jpp.androidchallenge.ui.fragment.TasksFragment;


/**
 * Main application's screen. This is the entry point of the application that shows the list of tasks.
 */
public class MainScreen extends ActionBarActivity implements IBackgroundExecutionListener {


    private enum Section {
        TASKS, NEW_TASK,;
    }


    private FloatingActionButton btnAddTask;
    private Toolbar mainToolbar;
    private Section mCurrentSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        addBtnAddTask();
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainToolbar.setTitle(R.string.app_name);
        addTasksFragment();
    }


    /**
     * Adds a new FloatingActionButton to the bottom right of the screen.
     */
    private void addBtnAddTask() {
        btnAddTask = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_edit_fab))
                .withButtonColor(getResources().getColor(R.color.add_task_background))
                .withGravity(Gravity.BOTTOM | Gravity.END)
                .withMargins(0, 0, 15, 15)
                .withButtonSize(70)
                .create();
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTaskFragment();
            }
        });
    }

    private void addTasksFragment() {
        Fragment fr = getSupportFragmentManager().findFragmentByTag(TasksFragment.TAG);
        if (fr == null) {
            fr = TasksFragment.newInstance();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fr, TasksFragment.TAG);
        transaction.commit();
        mCurrentSection = Section.TASKS;
    }


    private void addNewTaskFragment() {
        AddTaskFragment fr = AddTaskFragment.newInstance(this);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.add(R.id.main_fragment_container, fr, AddTaskFragment.TAG);
        tr.setCustomAnimations(R.anim.down_top, 0, 0, R.anim.top_down);
        tr.show(fr);
        tr.addToBackStack(null);
        tr.commit();

        animateFloatingActionButtonOut();

        mCurrentSection = Section.NEW_TASK;
    }


    @Override
    public void onBackPressed() {
        if (Section.NEW_TASK.equals(mCurrentSection)) {
            animateFloatingActionButtonIn();
            mCurrentSection = Section.TASKS;
        }
        super.onBackPressed();
    }


    private void animateFloatingActionButtonOut() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.top_down);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainToolbar.setTitle(R.string.new_task_title);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btnAddTask.startAnimation(anim);
    }


    private void animateFloatingActionButtonIn() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.down_top);
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainToolbar.setTitle(R.string.app_name);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btnAddTask.startAnimation(anim);
    }


    private void removeAddNewTaskFragment() {
        hideKeyboard();
        getSupportFragmentManager().popBackStack();
        animateFloatingActionButtonIn();
    }

    @Override
    public void onSuccess() {
        removeAddNewTaskFragment();
    }

    @Override
    public void onError() {
        Toast.makeText(this, R.string.error_inserting_task, Toast.LENGTH_LONG).show();
        removeAddNewTaskFragment();
    }


    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
