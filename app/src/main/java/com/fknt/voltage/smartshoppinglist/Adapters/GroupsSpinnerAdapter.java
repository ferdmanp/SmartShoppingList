package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;

/**
 * Created by SD on 14.02.2017.
 */

//TODO: Make it Generic!!!
public class GroupsSpinnerAdapter extends ArrayAdapter<GoodsGroup> {

    GoodsGroup[] groups;
    Context context;

    public GroupsSpinnerAdapter(Context context, int resource, GoodsGroup[] objects) {
        super(context, resource, objects);

        this.context=context;
        this.groups=objects;

    }

    @Override
    public int getCount() {
        return groups.length;
    }

    @Nullable
    @Override
    public GoodsGroup getItem(int position) {
        return groups[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(groups[position].getGroupName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(groups[position].getGroupName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }
}
