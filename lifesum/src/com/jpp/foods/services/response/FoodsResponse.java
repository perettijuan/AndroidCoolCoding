package com.jpp.foods.services.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.jpp.foods.model.Food;

/**
 * Response class that represents the results of the Foods API.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodsResponse extends GenericResponse {


    /**
     * Inner class to represent the list of response.
     * 
     * @author Juan P. Peretti (peretti.juan@gmail.com)
     * 
     */
    public class FoodList {


        @SerializedName("list")
        private List<Food> mFoodList;

        public List<Food> getFoodList() {
            return mFoodList;
        }

        public void setFoodList(List<Food> mFoodList) {
            this.mFoodList = mFoodList;
        }

    }

    @SerializedName("response")
    private FoodList mResponse;

    public FoodList getResponse() {
        return mResponse;
    }

    public void setResponse(FoodList mResponse) {
        this.mResponse = mResponse;
    }

    public List<Food> getResponseAsList() {
        List<Food> responseAsList = null;
        if (mResponse != null) {
            responseAsList = mResponse.getFoodList();
        }
        return responseAsList;
    }

}
