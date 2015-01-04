package com.jpp.androidchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.background.AddTaskExecutor;
import com.jpp.androidchallenge.background.IBackgroundExecutionListener;
import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.model.TaskColor;
import com.jpp.androidchallenge.provider.StorageManager;
import com.jpp.androidchallenge.ui.adapter.ColorSelectionSpinnerAdapter;

/**
 * Fragment used to show the option of adding a new task to the user.
 */
public class AddTaskFragment extends Fragment implements View.OnClickListener {


    public static final String TAG = AddTaskFragment.class.getName();

    private static final String LAST_COLOR_KEY = "last_color_key";

    private ColorSelectionSpinnerAdapter mSpinnerAdapter;
    private EditText etNewTask;
    private TextView txtSubmitnewTask;
    private Spinner spColorSelection;
    private View pgLoading;

    private String mTaskText;
    private int mTaskColorIdentifier;
    private IBackgroundExecutionListener mBackgroundExecutionListener;

    public AddTaskFragment() {

    }

    public static AddTaskFragment newInstance(IBackgroundExecutionListener listener) {
        AddTaskFragment newInstance = new AddTaskFragment();
        newInstance.mBackgroundExecutionListener = listener;
        return newInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.add_task_dialog_fragment, container, false);
        spColorSelection = (Spinner) fView.findViewById(R.id.sp_color_selection);
        mSpinnerAdapter = new ColorSelectionSpinnerAdapter(getActivity());
        spColorSelection.setAdapter(mSpinnerAdapter);
        etNewTask = (EditText) fView.findViewById(R.id.et_new_task);
        txtSubmitnewTask = (TextView) fView.findViewById(R.id.txt_submit_new_task);
        txtSubmitnewTask.setOnClickListener(this);
        pgLoading = fView.findViewById(R.id.pg_loading);

        setNewTaskWatcher();
        setLastColorSelected();

        return fView;
    }


    /**
     * Sets a new TextWatcher to the EditText used to enter the text
     * of the new task to manage the UI.
     */
    private void setNewTaskWatcher() {
        etNewTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countChar = etNewTask.getText().length();
                if (countChar > 0) {
                    txtSubmitnewTask.setEnabled(true);
                } else {
                    txtSubmitnewTask.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == txtSubmitnewTask.getId()) {
            saveTask();
        }
    }

    private void saveTask() {
        TaskColor color = (TaskColor) spColorSelection.getSelectedItem();
        if (color != null) {
            mTaskColorIdentifier = color.getIdentifier();
            mTaskText = etNewTask.getText().toString();
            pgLoading.setVisibility(View.VISIBLE);

            saveLastColorSelected(color);

            Task task = Task.newInstance(mTaskText, mTaskColorIdentifier);
            AddTaskExecutor executor = new AddTaskExecutor(getActivity(), mBackgroundExecutionListener);
            executor.execute(task);
        }
    }


    private void saveLastColorSelected(TaskColor color) {
        int colorId = color.getIdentifier();
        StorageManager.putInt(getActivity(), LAST_COLOR_KEY, colorId);
    }


    private void setLastColorSelected() {
        int lastColor = StorageManager.getInt(getActivity(), LAST_COLOR_KEY);
        if (lastColor == -1) {
            lastColor = TaskColor.NONE.getIdentifier();
        }
        spColorSelection.setSelection(lastColor);
    }

}
