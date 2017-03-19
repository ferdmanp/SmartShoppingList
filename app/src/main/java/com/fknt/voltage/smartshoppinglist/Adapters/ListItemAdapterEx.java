package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voltage on 19.03.2017. Project SmartShoppingList
 */

public class ListItemAdapterEx extends ArrayAdapter<GoodsItem> {

    Context context;
    ArrayList<GoodsItem> itemsAll;
    ArrayList<GoodsItem> items;
    ArrayList<GoodsItem> suggestions;
    int viewResourceId;
    int textViewResourceId;

    public ListItemAdapterEx(Context context, int resource, int textViewResourceId, ArrayList<GoodsItem> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.viewResourceId=resource;
        this.textViewResourceId=textViewResourceId;
        this.items=objects;
        this.itemsAll=(ArrayList<GoodsItem>) objects.clone();
        this.suggestions = new ArrayList<>();
    }

    public ListItemAdapterEx(Context context, int resource, ArrayList<GoodsItem> objects) {
        super(context, resource, objects);
        this.context=context;
        this.viewResourceId=resource;
        this.textViewResourceId=-1;
        this.items=objects;
        this.itemsAll=(ArrayList<GoodsItem>) objects.clone();
        this.suggestions = new ArrayList<>();

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= convertView;

        if(view==null)
        {
            LayoutInflater li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=li.inflate(this.viewResourceId,null);
        }

        GoodsItem item=this.getItem(position);
        TextView label= (TextView) view.findViewById(this.textViewResourceId);
        if(label!=null){
            label.setText(item.getName());
        }

        return view;
    }

    @Nullable
    @Override
    public GoodsItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return this.nameFilter;
    }

    Filter nameFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results= new FilterResults();

            if(constraint!=null)
            {
                suggestions.clear();
                //Option 1: from DB
                //suggestions=(ArrayList<GoodsItem>) GoodsItem.SelectByNamePattern(constraint.toString());
                //Option 2: from all items list
                for (GoodsItem item:itemsAll) {
                    if(item.getName().toLowerCase().contains(constraint.toString().toLowerCase()))
                        suggestions.add(item);
                }
                results.values=suggestions;
                results.count=suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            ArrayList<GoodsItem> filteredList= new ArrayList<>();
            if(results!=null && results.count>0)
            {
                filteredList=(ArrayList<GoodsItem>) results.values;
                clear();
                for (GoodsItem item :
                        filteredList) {
                    add(item);
                }
                notifyDataSetChanged();
            }

        }
    };
}
