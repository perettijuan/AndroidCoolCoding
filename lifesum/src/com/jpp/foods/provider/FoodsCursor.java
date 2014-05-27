package com.jpp.foods.provider;

import com.jpp.foods.model.Food;

import android.database.MatrixCursor;

/**
 * A {@link MatrixCursor} used to load the foods and present it to the UI.<br>
 * Note that this cursor uses an Id that can be used to identify each instance
 * of Foods. Since this information is backed by a List in the
 * {@link FoodsAtLifesumProvider}, and for the way in which this cursor is
 * populated, there is a one to one match between this Id and the index on the
 * backed list.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
class FoodsCursor extends MatrixCursor {


    private static final String[] COLUMNS = { FoodsAtLifesumContract.Foods.TITLE, FoodsAtLifesumContract.Foods.SERVER_ID,
            FoodsAtLifesumContract.Foods.CATEGORY, FoodsAtLifesumContract.Foods.CATEGORY_ID, FoodsAtLifesumContract.Foods.CARBOHYDRATES,
            FoodsAtLifesumContract.Foods.CALORIES, FoodsAtLifesumContract.Foods.CHOLESTEROL, FoodsAtLifesumContract.Foods.POTASSIUM,
            FoodsAtLifesumContract.Foods.SODIUM, FoodsAtLifesumContract.Foods.SUGAR, FoodsAtLifesumContract.Foods.FIBER,
            FoodsAtLifesumContract.Foods.FAT, FoodsAtLifesumContract.Foods.PROTEIN, FoodsAtLifesumContract.Foods._ID };

    private int mId;

    public FoodsCursor() {
        super(COLUMNS);
        mId = 0;
    }

    public void newRow(Food food) {
        mId++;
        addRow(new Object[] { food.getTitle(), food.getServerId(), food.getCategory(), food.getCategoryId(), food.getCarbohydrates(),
                food.getCalories(), food.getCholesterol(), food.getPotasium(), food.getSodium(), food.getSugar(), food.getFiber(), food.getFat(),
                food.getProtein(), mId });
    }

}
