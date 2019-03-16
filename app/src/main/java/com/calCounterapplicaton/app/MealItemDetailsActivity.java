package com.calCounterapplicaton.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


import model.Meal;

public class MealItemDetailsActivity extends AppCompatActivity {
    private TextView mealName, calories, dateTaken;
    private Button shareButton;
    private int mealId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_item_details);

        mealName= (TextView) findViewById(R.id.detailsMeal);
        calories= (TextView) findViewById(R.id.detailsCaloriesValue);
        dateTaken= (TextView) findViewById(R.id.detailsDateText);
        shareButton= (Button) findViewById(R.id.detailsShareButton);

        Meal meal = (Meal) getIntent().getSerializableExtra("userObj");

        mealName.setText(meal.getMealName());
        calories.setText(String.valueOf(meal.getCalories()));
        dateTaken.setText(meal.getRecordDate());

        mealId= meal.getMealId();

        calories.setTextSize(34.9f);
        calories.setTextColor(Color.RED);

    }
}
