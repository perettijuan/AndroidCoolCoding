package com.jpp.androidchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.ui.extention.FloatingActionButton;


/**
 * Main application's screen. This is the entry point of the application that shows the list of tasks.
 */
public class MainScreen extends ActionBarActivity {

    private FloatingActionButton btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainToolbar.setTitle(R.string.app_name);

        addBtnAddTask();


    }


    /**
     * Adds a new FloatingActionButton to the bottom right of the screen.
     */
    private void addBtnAddTask() {
        btnAddTask = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_edit_fab))
                .withButtonColor(getResources().getColor(R.color.add_task_background))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 15, 15)
                .withButtonSize(70)
                .create();
    }


}
