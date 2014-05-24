package com.jpp.foods.provider;

import android.database.MatrixCursor;

/**
 * A {@link MatrixCursor} used to load the foods and present it to the UI.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
class FoodsCursor extends MatrixCursor {


    private static final String[] COLUMNS = { FoodsAtLifesumContract.Foods.TITLE, FoodsAtLifesumContract.Foods.SERVER_ID,
            FoodsAtLifesumContract.Foods.CATEGORY, FoodsAtLifesumContract.Foods.CATEGORY_ID, FoodsAtLifesumContract.Foods.CARBOHYDRATES,
            FoodsAtLifesumContract.Foods.CALORIES, FoodsAtLifesumContract.Foods.CHOLESTEROL, FoodsAtLifesumContract.Foods.POTASSIUM,
            FoodsAtLifesumContract.Foods.SODIUM, FoodsAtLifesumContract.Foods.SUGAR, FoodsAtLifesumContract.Foods._ID };

    private int mId;

    public FoodsCursor() {
        super(COLUMNS);
        mId = 0;
    }

    public void newRow(String title, double serverId, String category, int categoryId, float carbohydrates, float calories, float cholesterol,
            float potassium, float sodium, float sugar) {
        mId++;
        addRow(new Object[] { title, serverId, category, categoryId, carbohydrates, calories, cholesterol, potassium, sodium, sugar, mId });
    }

}
