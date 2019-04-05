package com.calCounterapplicaton.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import model.*;

import data.DatabaseHandler;


public class MainActivity extends AppCompatActivity {
    private EditText mealName, mealCals;
    private Button submitButton;
    private DatabaseHandler dba;
    private FirebaseAnalytics mFirebaseAnlytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnlytics = FirebaseAnalytics.getInstance(this);
        dba = new DatabaseHandler(MainActivity.this);

        mealName = (EditText) findViewById(R.id.foodEditText);
        mealCals = (EditText) findViewById(R.id.caloriesEditText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDataToDB();

                //firebase logevent

                Bundle bundle=new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"btn_click");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"Next Activity");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"Button");
                mFirebaseAnlytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);


            }
        });
    }



    private void saveDataToDB() {

        Meal meal = new Meal();
        //trimming to get rid of white spaces
        String name = mealName.getText().toString().trim();
        String calsString = mealCals.getText().toString().trim();

        int cals = Integer.parseInt(calsString);

        if (name.equals("") || calsString.equals("")) {

            Toast.makeText(getApplicationContext(), "No empty fields allowed", Toast.LENGTH_LONG).show();

        }else {

            meal.setMealName(name);
            meal.setCalories(cals);

            dba.addMeal(meal);
            dba.close();


            //clear the form
            mealName.setText("");
            mealCals.setText("");

            //take users to next screen (display all entered items)
            startActivity(new Intent(MainActivity.this, DisplayMealsActivity.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

