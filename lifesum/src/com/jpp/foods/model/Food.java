package com.jpp.foods.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model class to represent Food
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class Food {


    @SerializedName("categoryid")
    private int mCategoryId;

    @SerializedName("id")
    private double mServerId;

    @SerializedName("category")
    private String mCategory;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("sodium")
    private float mSodium;

    @SerializedName("carbohydrates")
    private float mCarbohydrates;

    @SerializedName("calories")
    private float mCalories;

    @SerializedName("sugar")
    private float mSugar;

    @SerializedName("cholesterol")
    private float mCholesterol;

    @SerializedName("potassium")
    private float mPotasium;

    @SerializedName("fiber")
    private long mFiber;

    @SerializedName("fat")
    private long mFat;

    @SerializedName("protein")
    private float mProtein;

    public long getFat() {
        return mFat;
    }

    public void setFat(long mFat) {
        this.mFat = mFat;
    }

    public float getProtein() {
        return mProtein;
    }

    public void setProtein(float mProtein) {
        this.mProtein = mProtein;
    }

    public long getFiber() {
        return mFiber;
    }

    public void setFiber(long mFiber) {
        this.mFiber = mFiber;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public double getServerId() {
        return mServerId;
    }

    public void setServerId(double mServerId) {
        this.mServerId = mServerId;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public float getSodium() {
        return mSodium;
    }

    public void setSodium(float mSodium) {
        this.mSodium = mSodium;
    }

    public float getCarbohydrates() {
        return mCarbohydrates;
    }

    public void setCarbohydrates(float mCarbohydrates) {
        this.mCarbohydrates = mCarbohydrates;
    }

    public float getCalories() {
        return mCalories;
    }

    public void setCalories(float mCalories) {
        this.mCalories = mCalories;
    }

    public float getSugar() {
        return mSugar;
    }

    public void setSugar(float mSugar) {
        this.mSugar = mSugar;
    }

    public float getCholesterol() {
        return mCholesterol;
    }

    public void setCholesterol(float mCholesterol) {
        this.mCholesterol = mCholesterol;
    }

    public float getPotasium() {
        return mPotasium;
    }

    public void setPotasium(float mPotasium) {
        this.mPotasium = mPotasium;
    }

}
