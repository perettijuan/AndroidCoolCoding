package com.jpp.foods.ui;

import com.jpp.foods.R;
import com.jpp.foods.ui.adapter.FoodDetailsAdapter;
import com.jpp.foods.ui.extras.DepthPageTransformer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Activity that shows the details of the Foods in a horizontal scroll fashion.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodDetailsScreen extends FragmentActivity {


    public static final String ITEMS_COUNT_PARAM = "items_count_param";
    public static final String ITEM_POSITION_PARAM = "items_position_param";    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details_screen);
        getActionBar().setTitle(R.string.app_name);
        int count = getIntent().getExtras().getInt(ITEMS_COUNT_PARAM);
        int position = getIntent().getExtras().getInt(ITEM_POSITION_PARAM);        
        ViewPager vpFoodDetails = (ViewPager) findViewById(R.id.vp_food_details);
        FoodDetailsAdapter adapter = new FoodDetailsAdapter(getSupportFragmentManager(), count);
        vpFoodDetails.setAdapter(adapter);
        vpFoodDetails.setCurrentItem(position);
        vpFoodDetails.setPageTransformer(true, new DepthPageTransformer());
    }
}
