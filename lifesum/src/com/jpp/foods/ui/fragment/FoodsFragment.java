package com.jpp.foods.ui.fragment;

import com.jpp.foods.R;
import com.jpp.foods.provider.FoodsAtLifesumContract;
import com.jpp.foods.ui.FoodDetailsScreen;
import com.jpp.foods.ui.adapter.RemoteFoodsAdapter;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Fragment used to present the Foods contained in the server to the user.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodsFragment extends Fragment implements LoaderCallbacks<Cursor>, OnItemClickListener {


    // tag used to identify the fragment
    public static final String TAG = FoodsFragment.class.getName();

    private static final int LOAD_REMOTE_FOODS = 1;

    private ProgressBar pgLoadingFoods;
    private ListView lvRemoteFoods;
    private TextView txtNoMatch;
    private RemoteFoodsAdapter mAdapter;

    private String mQuery;

    /**
     * Default class constructor
     */
    public FoodsFragment() {

    }

    /**
     * Factory method.
     * 
     * @return - a new instance of a {@link FoodsFragment}
     */
    public static FoodsFragment newInstance() {
        FoodsFragment instance = new FoodsFragment();
        return instance;
    }

    public void setFoodToSearch(String foodTitle) {
        mQuery = foodTitle;
        if (isVisible()) {
            getLoaderManager().restartLoader(LOAD_REMOTE_FOODS, null, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.foods_fragment, container, false);
        pgLoadingFoods = (ProgressBar) fView.findViewById(R.id.pg_loading_foods);
        lvRemoteFoods = (ListView) fView.findViewById(R.id.lv_remote_foods);
        txtNoMatch = (TextView) fView.findViewById(R.id.txt_no_match);
        lvRemoteFoods.setOnItemClickListener(this);
        return fView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(LOAD_REMOTE_FOODS, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        pgLoadingFoods.setVisibility(View.VISIBLE);
        lvRemoteFoods.setVisibility(View.GONE);
        txtNoMatch.setVisibility(View.GONE);
        return new CursorLoader(getActivity(), FoodsAtLifesumContract.Foods.REMOTE_CONTENT_URI, null, FoodsAtLifesumContract.Foods.QUERY_SEARCH,
                new String[] { mQuery },
                FoodsAtLifesumContract.Foods.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mAdapter == null) {
            mAdapter = new RemoteFoodsAdapter(getActivity().getApplicationContext(), data, 0);
            lvRemoteFoods.setAdapter(mAdapter);
        } else {
            mAdapter.changeCursor(data);
        }
        pgLoadingFoods.setVisibility(View.GONE);
        if (mAdapter.getCount() > 0) {
            lvRemoteFoods.setVisibility(View.VISIBLE);
            txtNoMatch.setVisibility(View.GONE);
        } else {
            lvRemoteFoods.setVisibility(View.GONE);
            txtNoMatch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mAdapter != null) {
            mAdapter.changeCursor(null);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int count = mAdapter.getCount();
        Intent intent = new Intent(getActivity(), FoodDetailsScreen.class);
        intent.putExtra(FoodDetailsScreen.ITEMS_COUNT_PARAM, count);
        intent.putExtra(FoodDetailsScreen.ITEM_POSITION_PARAM, position);
        startActivity(intent);
    }

}
