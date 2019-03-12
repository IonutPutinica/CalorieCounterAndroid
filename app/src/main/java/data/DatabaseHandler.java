package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

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
}
