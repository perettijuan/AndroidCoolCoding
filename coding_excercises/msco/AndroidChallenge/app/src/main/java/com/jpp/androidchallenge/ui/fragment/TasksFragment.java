package com.jpp.androidchallenge.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpp.androidchallenge.R;
import com.jpp.androidchallenge.provider.AndroidChallengeContract;
import com.jpp.androidchallenge.ui.adapter.TasksAdapter;

/**
 * A Fragment used to show the list of tasks that the user has in the application.
 */
public class TasksFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = TasksFragment.class.getName();

    private static final int LOADER_ID = 1;

    private TasksAdapter mAdapter;
    private RecyclerView rvTasks;
    private View txtEmptyTasks;
    private View pgLoadingTasks;

    /**
     * Class Constructor
     */
    public TasksFragment() {

    }

    /**
     * Factory method for external class creation.
     */
    public static TasksFragment newInstance() {
        TasksFragment newInstance = new TasksFragment();
        return newInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.tasks_fragment, container, false);
        rvTasks = (RecyclerView) fView.findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTasks.setItemAnimator(new DefaultItemAnimator());
        pgLoadingTasks = fView.findViewById(R.id.pg_loading_tasks);
        txtEmptyTasks = fView.findViewById(R.id.txt_empty_tasks);
        return fView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (mAdapter == null || mAdapter.getItemCount() == 0) {
            showLoadingView();
        }
        return new CursorLoader(getActivity(), AndroidChallengeContract.Tasks.CONTENT_URI, null, null, null, AndroidChallengeContract.Tasks.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount() == 0) {
            showEmptyView();
        } else {
            if (mAdapter == null) {
                mAdapter = new TasksAdapter(getActivity(), data);
                rvTasks.setAdapter(mAdapter);
            } else {
                mAdapter.swapCursor(data);
            }
            showTasksView();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }


    private void showLoadingView() {
        pgLoadingTasks.setVisibility(View.VISIBLE);
        txtEmptyTasks.setVisibility(View.GONE);
        rvTasks.setVisibility(View.GONE);
    }


    private void showEmptyView() {
        pgLoadingTasks.setVisibility(View.GONE);
        txtEmptyTasks.setVisibility(View.VISIBLE);
        rvTasks.setVisibility(View.GONE);
    }

    private void showTasksView() {
        pgLoadingTasks.setVisibility(View.GONE);
        txtEmptyTasks.setVisibility(View.GONE);
        rvTasks.setVisibility(View.VISIBLE);
    }
}
