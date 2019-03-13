package com.calCounterapplicaton.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import data.CustomListViewAdapter;
import data.DatabaseHandler;
import model.Meal;

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
    }
}
