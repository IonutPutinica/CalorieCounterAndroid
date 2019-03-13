package data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.calCounterapplicaton.app.R;

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

        View row = convertView;
        ViewHolder holder = null;

        if ( row == null || (row.getTag() == null)) {

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new ViewHolder();

            holder.mealName = (TextView) row.findViewById(R.id.name);
            holder.mealDate = (TextView) row.findViewById(R.id.dateText);
            holder.mealCalories = (TextView) row.findViewById(R.id.calories);

            row.setTag(holder);

        }else {

            holder = (ViewHolder) row.getTag();
        }


        holder.meal = getItem(position);

        holder.mealName.setText(holder.meal.getMealName());
        holder.mealDate.setText(holder.meal.getRecordDate());
        holder.mealCalories.setText(String.valueOf(holder.meal.getCalories()));

        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });






        return row;

    }

    public class ViewHolder {
        Meal meal;
        TextView mealName;
        TextView mealCalories;
        TextView mealDate;
    }

}
