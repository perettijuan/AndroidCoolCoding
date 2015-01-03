package com.jpp.androidchallenge.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Spinner;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.ui.adapter.ColorSelectionSpinnerAdapter;

/**
 * DialogFragment used to show the add task UI to the user.
 */
public class AddTaskDialogFragment extends DialogFragment {


    public static final String TAG = AddTaskDialogFragment.class.getName();

    private ColorSelectionSpinnerAdapter mSpinnerAdapter;

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
        Spinner spColorSelection = (Spinner) fView.findViewById(R.id.sp_color_selection);
        mSpinnerAdapter = new ColorSelectionSpinnerAdapter(getActivity());
        spColorSelection.setAdapter(mSpinnerAdapter);
        return fView;
    }
}

