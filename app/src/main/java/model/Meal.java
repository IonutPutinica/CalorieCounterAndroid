package model;

import java.io.Serializable;

public class Meal implements Serializable {
    private static final long serialVersionUID = 10L;
    private String mealName;
    private int calories;
    private int mealId;
    private String recordDate;

    public Meal(String meal, int cals, int id, String date)

    {
        mealName=meal;
        calories=cals;
        mealId=id;
        recordDate=date;
    }

    public Meal() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }


}
