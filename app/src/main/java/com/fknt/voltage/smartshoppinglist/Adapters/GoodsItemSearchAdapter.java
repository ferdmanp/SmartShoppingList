package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;

import com.fknt.voltage.smartshoppinglist.GoodsItem;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.ArrayList;

/**
 * Created by voltage on 19.03.2017. Project SmartShoppingList
 */

public class GoodsItemSearchAdapter implements ListAdapter {

    Context context;
    ArrayList<GoodsItem> itemsList;

    public GoodsItemSearchAdapter(Context context, ArrayList<GoodsItem> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return this.itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((GoodsItem)this.getItem(position)).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;

        if(view==null)
        {
          view= LayoutInflater.from(this.context).inflate(R.layout.activity_shopping_list_editor,null);
            //AutoCompleteTextView
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
