package com.calCounterapplicaton.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import data.CustomListViewAdapter;
import data.DatabaseHandler;
import android.view.Menu;
import android.view.MenuItem;
import util.Utils;
import model.*;
import java.lang.reflect.Array;
import android.util.log;

public class DisplayMealsActivity extends AppCompatActivity {

    private DatabaseHandler dba;
    private ArrayList<Meal> dbMeals = new ArrayList<>();
    private CustomListViewAdapter mealAdapter;
    private ListView listView;
    private Meal myMeal;
    private TextView totalCals, totalMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_meals);

        listView= (ListView) findViewById(R.id.list);
        totalCals= (TextView) findViewById(R.id.totalAmountTextView);
        totalMeals= (TextView) findViewById(R.id.totalItemsTextView);

        refreshData();
    }

    private void refreshData() {
        dbMeals.clear();
        dba= new DatabaseHandler(getApplicationContext());
        ArrayList<Meal> mealsFromDB = dba.getMeals();
        int calsValue = dba.totalCalories();
        int totalItems= dba.getTotalItems();

        String formattedValue= Utils.formatNumber(calsValue);
        String formattedItems = Utils.formatNumber(totalItems);

        totalCals.setText("Total Calories: "+ formattedValue);
        totalMeals.setText("Total Meals: " + formattedItems);

        for(int i=0;i<mealsFromDB.size();i++)
        {
            String name=mealsFromDB.get(i).getMealName();
            String dateText=mealsFromDB.get(i).getRecordDate();
            int cals= mealsFromDB.get(i).getCalories();
            int mealId=mealsFromDB.get(i).getMealId();

            myMeal=new Meal();
            myMeal.setMealName(name);
            myMeal.setRecordDate(dateText);
            myMeal.setCalories(cals);
            myMeal.setMealId(mealId);
            dbMeals.add(myMeal);

        }
        dba.close();
        //setting up the adapter
        mealAdapter=new CustomListViewAdapter(DisplayMealsActivity.this, R.layout.list_row, dbMeals);
        listView.setAdapter(mealAdapter);
        mealAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_foods, menu);
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
