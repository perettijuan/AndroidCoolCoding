package com.jpp.androidchallenge.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.background.AddTaskExecutor;
import com.jpp.androidchallenge.background.IBackgroundExecutionListener;
import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.model.TaskColor;
import com.jpp.androidchallenge.ui.adapter.ColorSelectionSpinnerAdapter;

/**
 * DialogFragment used to show the add task UI to the user.
 */
public class AddTaskDialogFragment extends DialogFragment implements View.OnClickListener, IBackgroundExecutionListener {


    public static final String TAG = AddTaskDialogFragment.class.getName();

    private ColorSelectionSpinnerAdapter mSpinnerAdapter;
    private EditText etNewTask;
    private TextView txtSubmitnewTask;
    private Spinner spColorSelection;
    private View pgLoading;

    private String mTaskText;
    private int mTaskColorIdentifier;

    /**
     * Class constructor
     */
    public AddTaskDialogFragment() {

    }


    /**
     * Factory method for fragment creation.
     *
     * @return - the newly created instance.
     */
    public static AddTaskDialogFragment newInstance() {
        AddTaskDialogFragment newInstance = new AddTaskDialogFragment();
        return newInstance;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTopDown;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
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
            TaskColor color = (TaskColor) spColorSelection.getSelectedItem();
            if (color != null) {
                mTaskColorIdentifier = color.getIdentifier();
                mTaskText = etNewTask.getText().toString();
                pgLoading.setVisibility(View.VISIBLE);
                Task task = Task.newInstance(mTaskText, mTaskColorIdentifier);
                AddTaskExecutor executor = new AddTaskExecutor(getActivity(), this);
                executor.execute(task);
            }
        }
    }

    @Override
    public void onSuccess() {
        dismiss();
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), R.string.error_inserting_task, Toast.LENGTH_LONG).show();
        dismiss();
    }


}

