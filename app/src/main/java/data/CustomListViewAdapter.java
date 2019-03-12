package data;
import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import model.Meal;

public class CustomListViewAdapter extends ArrayAdapter<Meal> {
    private int layoutResource;
    private Activity activity;
    private ArrayList<Meal> mealList=new ArrayList<>();
    public CustomListViewAdapter(Activity act, int resource, ArrayList<Meal> data) {
        super(act, resource, data);
        layoutResource = resource;
        activity=act;
        mealList=data;
        notifyDataSetChanged();
    }

    
}
