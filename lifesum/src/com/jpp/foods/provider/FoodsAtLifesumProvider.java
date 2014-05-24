package com.jpp.foods.provider;

import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.jpp.foods.Constants;
import com.jpp.foods.Logger;
import com.jpp.foods.model.Food;
import com.jpp.foods.services.HttpManager;
import com.jpp.foods.services.HttpManager.RequestTypes;
import com.jpp.foods.services.ServiceException;
import com.jpp.foods.services.response.FoodsResponse;

/**
 * Content provider used to access the data model in the application. <br>
 * Note that this provider executes access to the server API.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodsAtLifesumProvider extends ContentProvider {


    private static final String TAG = FoodsAtLifesumProvider.class.getName();

    // URI ID to get all foods
    private static final int FOODS = 100;

    // URI ID to get one particular food identified by id
    private static final int FOODS_ID = 101;

    // URI ID to get all remote foods
    private static final int REMOTE_FOODS = 200;

    // URI ID to get one particular remote food identified by id
    private static final int REMOTE_FOODS_ID = 201;

    /*
     * {@link UriMatcher} to determine what is requested to this {@link
     * ContentProvider}.
     */
    private static final UriMatcher mUriMatcher = buildUriMatcher();

    // a builder to build the HttpManager instances.
    private HttpManager.Builder mHttpManagerBuilder;

    /**
     * Builds the {@link UriMatcher} matching the tables defined in the contract
     * with an integer that helps to the identification of the proper tables.
     * 
     * @return - the builded {@link UriMatcher}
     */
    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FoodsAtLifesumContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, FoodsAtLifesumContract.Tables.FOODS, FOODS);
        matcher.addURI(authority, FoodsAtLifesumContract.Tables.FOODS + "/*", FOODS_ID);

        matcher.addURI(authority, FoodsAtLifesumContract.Remotes.FOODS, REMOTE_FOODS);
        matcher.addURI(authority, FoodsAtLifesumContract.Remotes.FOODS + "/*", REMOTE_FOODS_ID);

        return matcher;
    }

    /** {@inheritDoc} */
    @Override
    public boolean onCreate() {
        if (mHttpManagerBuilder == null) {
            mHttpManagerBuilder = new HttpManager.Builder();
        }
        return ((mHttpManagerBuilder == null) ? false : true);
    }

    /** {@inheritDoc} */
    @Override
    public String getType(Uri uri) {
        final int match = mUriMatcher.match(uri);
        switch (match) {
        case FOODS:
            return FoodsAtLifesumContract.Foods.CONTENT_TYPE;
        case FOODS_ID:
            return FoodsAtLifesumContract.Foods.CONTENT_ITEM_TYPE;
        case REMOTE_FOODS:
            return FoodsAtLifesumContract.Foods.CONTENT_TYPE;
        case REMOTE_FOODS_ID:
            return FoodsAtLifesumContract.Foods.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = mUriMatcher.match(uri);
        Cursor cursor = null;
        switch (match) {
        case REMOTE_FOODS:
            try {
                cursor = retrieveFoodsFromSeverApi();
            } catch (ServiceException e) {
                Logger.error(e.getMessage(), TAG);
            }
            break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }

    /**
     * Executes the access to the server API in order to retrieve all the Foods
     * in the server.
     * 
     * @return - a {@link Cursor} that contains the results.
     * @throws ServiceException
     *             - if something goes wrong when accessing the server
     */
    private Cursor retrieveFoodsFromSeverApi() throws ServiceException {
        HttpManager manager = mHttpManagerBuilder.build(RequestTypes.GET, Constants.API_URL, Constants.ACCESS_TOKEN);
        FoodsResponse response = manager.execute(FoodsResponse.class);
        FoodsCursor cursor = new FoodsCursor();
        List<Food> foods = response.getResponseAsList();
        for (Food current : foods) {
            cursor.newRow(current.getTitle(), current.getServerId(), current.getCategory(), current.getCategoryId(), current.getCarbohydrates(),
                    current.getCalories(), current.getCholesterol(), current.getPotasium(), current.getSodium(), current.getSugar());
        }
        return cursor;
    }

}
