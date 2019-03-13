package com.calCounterapplicaton.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MealItemDetailsActivity extends AppCompatActivity {
    private TextView mealName, calories, dateTaken;
    private Button shareButton;
    private int mealId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_item_details);
    }
}
