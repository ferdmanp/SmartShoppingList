package com.fknt.voltage.smartshoppinglist.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.List;

/**
 * Created by voltage on 12.02.2017. Project SmartShoppingList
 */

public class GroupItemAdapter extends BaseAdapter {

    List<GoodsGroup> groups;
    Activity context;

    public GroupItemAdapter(Activity context,List<GoodsGroup> groups) {
        this.groups = groups;
        this.context = context;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        GoodsGroup item=(GoodsGroup) this.getItem(position);
        return item.getId();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;

        if(view==null)
        {
            GoodsGroup item=(GoodsGroup) this.getItem(position);
            view= LayoutInflater.from(context).inflate(R.layout.item_groups_list,null);
            TextView tvGroupName=(TextView) view.findViewById(R.id.tv_group_name);

            tvGroupName.setText(item.getGroupName());
        }

        return view;
    }
}
