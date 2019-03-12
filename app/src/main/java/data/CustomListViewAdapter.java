package data;
import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    @Override
    public int getCount()
    {
        return mealList.size();
    }

    @Override
    public Meal getItem(int position)
    {
        return mealList.get(position);
    }

    @Override
    public int getPosition(Meal item)
    {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView ,ViewGroup parent)
    {
        return super.getView(position,convertView,parent);
    }

    public class ViewHolder {
        Meal meal;
        TextView mealName;
        TextView mealCalories;
        TextView mealDate;
    }

}
