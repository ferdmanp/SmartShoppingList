package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fknt.voltage.smartshoppinglist.GoodsItem;

import java.util.List;

/**
 * Created by SD on 10.02.2017.
 */

public class ListItemAdapter extends BaseAdapter {

    Context context;
    List<GoodsItem> items;

    public ListItemAdapter(Context context, List<GoodsItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        GoodsItem item=(GoodsItem) this.getItem(i);
        return item.getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;

        if(view==null)
        {
            GoodsItem item=(GoodsItem) this.getItem(i);

        }

        return  view;
    }
}
