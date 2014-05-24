package com.jpp.foods.ui.fragment;

import com.jpp.foods.R;
import com.jpp.foods.provider.FoodsAtLifesumContract;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment used to present the Foods contained in the server to the user.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodsFragment extends Fragment implements LoaderCallbacks<Cursor> {


    // tag used to identify the fragment
    public static final String TAG = FoodsFragment.class.getName();

    private static final int LOAD_REMOTE_FOODS = 1;
    
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.foods_fragment, container, false);
        return fView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(LOAD_REMOTE_FOODS, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), FoodsAtLifesumContract.Foods.REMOTE_CONTENT_URI, null, null, null,
                FoodsAtLifesumContract.Foods.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
       System.out.println(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // TODO Auto-generated method stub

    }

}
