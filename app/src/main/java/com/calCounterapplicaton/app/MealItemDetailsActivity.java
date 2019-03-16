package com.calCounterapplicaton.app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCals();
            }
        });

    }
    public void shareCals()
    {
        StringBuilder dataString = new StringBuilder();

        String name = mealName.getText().toString();
        String cals = calories.getText().toString();
        String date = dateTaken.getText().toString();

        dataString.append(" Meal: " + name + "\n");
        dataString.append(" Calories: " + cals + "\n");
        dataString.append(" Eaten on: " + date);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "My Caloric Intake");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {"recipient@example.com"});
        i.putExtra(Intent.EXTRA_TEXT, dataString.toString());

        try{

            startActivity(Intent.createChooser(i, "Send mail..."));

        }catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Please install email client before sending",
                    Toast.LENGTH_LONG).show();
        }
    }
}
