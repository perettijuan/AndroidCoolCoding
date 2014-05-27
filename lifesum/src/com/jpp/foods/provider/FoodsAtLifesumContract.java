package com.jpp.foods.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * The contract between the foods at Lifesum application provider and
 * applications. Contains definitions for the supported URIs and columns.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public final class FoodsAtLifesumContract {


    /**
     * The authority for Social Protection contents.
     */
    public static final String CONTENT_AUTHORITY = "com.jpp.foods.provider";

    /**
     * Base URI to access provider's content.
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Interface that contains the names of all the tables that the content
     * provider can handle
     * 
     * @author Juan P. Peretti (peretti.juan@gmail.com)
     * 
     */
    interface Tables {


        static final String FOODS = "foods";
    }

    /**
     * Interface that contains the names of all the remote elements that the
     * provider can handle.
     * 
     * @author Juan P. Peretti (peretti.juan@gmail.com)
     * 
     */
    interface Remotes {


        static final String FOODS = "remote_foods";
    }

    /**
     * Interface that contains the definition of the columns that are part of
     * the Foods table.
     * 
     * @author Juan P. Peretti (peretti.juan@gmail.com)
     * 
     */
    interface FoodColumns {


        String CATEGORY_ID = "categoryid";
        String SERVER_ID = "server_id";
        String CATEGORY = "category";
        String TITLE = "title";
        String SODIUM = "sodium";
        String CARBOHYDRATES = "carbohydrates";
        String CALORIES = "calories";
        String SUGAR = "sugar";
        String CHOLESTEROL = "cholesterol";
        String POTASSIUM = "potassium";
        String FIBER = "fiber";
        String FAT = "fat";
        String PROTEIN = "protein";
    }

    /**
     * Class that defines the contract provided for the Foods Table.
     * 
     * @author Juan P. Peretti (peretti.juan@gmail.com)
     * 
     */
    public static class Foods implements FoodColumns, BaseColumns {


        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(Tables.FOODS).build();

        public static final Uri REMOTE_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(Remotes.FOODS).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.lifesumfoods.foods";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.lifesumfoods.foods";

        public static final String QUERY_SEARCH = "query";

        /** Default "ORDER BY" clause. */
        public static final String DEFAULT_SORT = BaseColumns._ID + " ASC";

        /** Build {@link Uri} for request all feed details. */
        public static Uri buildFeedDetailsUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static Uri buildFeedDetailsUri(String foodId) {
            return CONTENT_URI.buildUpon().appendPath(foodId).build();
        }

        public static String getFeedDetailId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildRemoteFeedDetailsUri(String foodId) {
            return REMOTE_CONTENT_URI.buildUpon().appendPath(foodId).build();
        }
    }
}
