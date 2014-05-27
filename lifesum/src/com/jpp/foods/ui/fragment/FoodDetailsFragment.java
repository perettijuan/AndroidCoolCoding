package com.jpp.foods.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.jpp.foods.R;
import com.jpp.foods.provider.FoodsAtLifesumContract;

/**
 * Fragment that shows the details of a selected Food.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodDetailsFragment extends Fragment implements LoaderCallbacks<Cursor> {


    public static final String TAG = FoodDetailsFragment.class.getName();

    private static final int LOADER_ID = 999;

    private int mFoodId;
    private View rlFoodDetailHeader;
    private TextView txtFoodTitle;
    private TextView txtFoodCategory;
    private TableLayout tblFoodProperties;
    private ProgressBar pgLoadingFoodDetails;
    private ImageView ivPrevious;
    private ImageView ivNext;

    /**
     * Factory method
     */
    public static FoodDetailsFragment newInstance(int foodId) {
        FoodDetailsFragment fr = new FoodDetailsFragment();
        fr.mFoodId = foodId;
        return fr;
    }

    /**
     * Empty class constructor.
     */
    public FoodDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.food_detail_fragment, container, false);
        rlFoodDetailHeader = fView.findViewById(R.id.rl_food_detail_header);
        txtFoodTitle = (TextView) fView.findViewById(R.id.txt_food_detail_title);
        txtFoodCategory = (TextView) fView.findViewById(R.id.txt_food_detail_category);
        tblFoodProperties = (TableLayout) fView.findViewById(R.id.tbl_food_properties);
        pgLoadingFoodDetails = (ProgressBar) fView.findViewById(R.id.pg_loading_food_details);
        ivPrevious = (ImageView) fView.findViewById(R.id.iv_previous);
        ivNext = (ImageView) fView.findViewById(R.id.iv_next);
        return fView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        hideViews(rlFoodDetailHeader, txtFoodCategory, tblFoodProperties);
        showViews(pgLoadingFoodDetails);
        return new CursorLoader(getActivity(), FoodsAtLifesumContract.Foods.buildRemoteFeedDetailsUri(String.valueOf(mFoodId)), null, null, null,
                FoodsAtLifesumContract.Foods.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        showViews(rlFoodDetailHeader, txtFoodCategory, tblFoodProperties);
        hideViews(pgLoadingFoodDetails);
        if(data.moveToFirst()) {
            String title = data.getString(data.getColumnIndex(FoodsAtLifesumContract.Foods.TITLE));
            txtFoodTitle.setText(title);
            String category = data.getString(data.getColumnIndex(FoodsAtLifesumContract.Foods.CATEGORY));
            txtFoodCategory.setText(category);
        }
        
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // TODO Auto-generated method stub

    }

    private void hideViews(View... v) {
        changeViewVisibility(View.GONE, v);
    }

    private void showViews(View... v) {
        changeViewVisibility(View.VISIBLE, v);
    }

    private void changeViewVisibility(int visivility, View... v) {
        for (View current : v) {
            current.setVisibility(visivility);
        }
    }

}
