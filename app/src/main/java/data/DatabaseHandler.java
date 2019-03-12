package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.util.Log;
import java.text.DateFormat;
import java.util.Date;

import java.util.ArrayList;

import data.Constants;
import model.*;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final ArrayList<Meal> mealList= new ArrayList<>();


   public DatabaseHandler(Context context)
   {
       super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //creating the table
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.MEAL_NAME +
                " TEXT, " + Constants.MEAL_CALORIES_NAME + " INT, " + Constants.DATE_NAME + " LONG);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

    //create a new  table in case there is a new one
        onCreate(db);
    }
    //get total number of itemes that the user has saved within the application
    public int getTotalItems()
    {
    int totalItems=0;
    String query= "SELECT * FROM "+ Constants.TABLE_NAME;
    SQLiteDatabase dba= this.getReadableDatabase();
    Cursor cursor= dba.rawQuery(query,null);
    totalItems=cursor.getCount();
    cursor.close();


    return totalItems;
    }

    //return the total calorie intake for the day
    public int totalCalories()
    {
        int cals=0;
        SQLiteDatabase dba= this.getReadableDatabase();
        String query= "SELECT SUM( " + Constants.MEAL_CALORIES_NAME+ " )" + "FROM " + Constants.TABLE_NAME;
        Cursor cursor= dba.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            cals=cursor.getInt(0);
        }
        cursor.close();
        dba.close();
        return cals;
    }

    //delete a meal from the list
    //the id parsing is used to find the item
    public void deleteMeal(int id)
    {
        SQLiteDatabase dba= this.getWritableDatabase();
        //the table is passed in
        dba.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

        dba.close();
    }

    public void addMeal(Meal meal)
    {
        SQLiteDatabase dba = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.MEAL_NAME, meal.getMealName());
        values.put(Constants.MEAL_CALORIES_NAME, meal.getCalories());
        //asks the android device for the time
        values.put(Constants.DATE_NAME, System.currentTimeMillis());

        dba.insert(Constants.TABLE_NAME, null, values);

        //used to create a verbose log
        Log.v("Added Meal item", "Confirmation");

        dba.close();
    }

    //get all meals from the database
    
}
