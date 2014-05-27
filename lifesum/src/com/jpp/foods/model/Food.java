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
    private double mCategoryId;

    @SerializedName("id")
    private double mServerId;

    @SerializedName("category")
    private String mCategory;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("sodium")
    private double mSodium;

    @SerializedName("carbohydrates")
    private double mCarbohydrates;

    @SerializedName("calories")
    private double mCalories;

    @SerializedName("sugar")
    private double mSugar;

    @SerializedName("cholesterol")
    private double mCholesterol;

    @SerializedName("potassium")
    private double mPotasium;

    @SerializedName("fiber")
    private double mFiber;

    @SerializedName("fat")
    private double mFat;

    @SerializedName("protein")
    private double mProtein;
    
    
    public double getFat() {
        return mFat;
    }

    public void setFat(double mFat) {
        this.mFat = mFat;
    }

    public double getProtein() {
        return mProtein;
    }

    public void setProtein(double mProtein) {
        this.mProtein = mProtein;
    }

    public double getFiber() {
        return mFiber;
    }

    public void setFiber(long mFiber) {
        this.mFiber = mFiber;
    }

    public double getCategoryId() {
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

    public double getSodium() {
        return mSodium;
    }

    public void setSodium(double mSodium) {
        this.mSodium = mSodium;
    }

    public double getCarbohydrates() {
        return mCarbohydrates;
    }

    public void setCarbohydrates(double mCarbohydrates) {
        this.mCarbohydrates = mCarbohydrates;
    }

    public double getCalories() {
        return mCalories;
    }

    public void setCalories(double mCalories) {
        this.mCalories = mCalories;
    }

    public double getSugar() {
        return mSugar;
    }

    public void setSugar(double mSugar) {
        this.mSugar = mSugar;
    }

    public double getCholesterol() {
        return mCholesterol;
    }

    public void setCholesterol(double mCholesterol) {
        this.mCholesterol = mCholesterol;
    }

    public double getPotasium() {
        return mPotasium;
    }

    public void setPotasium(double mPotasium) {
        this.mPotasium = mPotasium;
    }

}
