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
import android.widget.TableRow;
import android.widget.TextView;

import com.jpp.foods.Constants;
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
    private int mCount;
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
    public static FoodDetailsFragment newInstance(int foodId, int count) {
        FoodDetailsFragment fr = new FoodDetailsFragment();
        fr.mFoodId = foodId;
        fr.mCount = count;
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
        if (data.moveToFirst()) {
            String title = data.getString(data.getColumnIndex(FoodsAtLifesumContract.Foods.TITLE));
            txtFoodTitle.setText(title);
            String category = data.getString(data.getColumnIndex(FoodsAtLifesumContract.Foods.CATEGORY));
            txtFoodCategory.setText(category);
            double carbohydrates = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.CARBOHYDRATES));
            populatePropertiesTable(R.string.carbohydrates, carbohydrates);
            double calories = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.CALORIES));
            populatePropertiesTable(R.string.calories, calories);
            double cholesterol = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.CHOLESTEROL));
            populatePropertiesTable(R.string.cholesterol, cholesterol);
            double potassium = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.POTASSIUM));
            populatePropertiesTable(R.string.potassium, potassium);
            double sodium = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.SODIUM));
            populatePropertiesTable(R.string.sodium, sodium);
            double sugar = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.SUGAR));
            populatePropertiesTable(R.string.sugar, sugar);
            double fiber = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.FIBER));
            populatePropertiesTable(R.string.fiber, fiber);
            double fat = data.getDouble(data.getColumnIndex(FoodsAtLifesumContract.Foods.FAT));
            populatePropertiesTable(R.string.fat, fat);
            updateHorizontalIndicators();
        }
    }

    /**
     * Populates the properties table with two columns: one per each category
     */
    private void populatePropertiesTable(int categoryResId, double categoryValue) {
        TableRow tblRow = new TableRow(getActivity().getApplicationContext());
        LayoutInflater inflater = getLayoutInflater(null);
        View propertyItem = inflater.inflate(R.layout.food_property_item, null, false);
        TextView txtPropertyName = (TextView) propertyItem.findViewById(R.id.txt_property_name);
        txtPropertyName.setText(getString(categoryResId,
                (String.valueOf(categoryValue).equals(Constants.EMPTY_STRING) ? Constants.SLASH : String.valueOf(categoryValue))));
        tblRow.addView(propertyItem);
        tblFoodProperties.addView(tblRow);
    }

    private void updateHorizontalIndicators() {
        if (mFoodId == 0) {
            hideViews(ivPrevious);
        } else if (mFoodId == mCount) {
            hideViews(ivNext);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Do nothing in purpose.
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
