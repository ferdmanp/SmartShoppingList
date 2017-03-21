package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.GoodsItem;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voltage on 10.03.2017. Project SmartShoppingList
 */

public class ExpandableShoppingListAdapter
        extends BaseExpandableListAdapter
        implements Filterable
{

    private List<GoodsGroup> groups;
    private List<GoodsItem> items;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context context;
    private Filter filter;

    private ArrayMap<GoodsGroup,List<GoodsItem>> itemsTree;
    private ArrayMap<GoodsGroup,List<GoodsItem>> suggestions;

//    public ExpandableShoppingListAdapter(List<GoodsGroup> groups, int itemLayoutId, int groupLayoutId, Context context) {
//        this(itemLayoutId,groupLayoutId,context);
//        this.groups = groups;
//
//    }

    public ExpandableShoppingListAdapter(List<GoodsItem> items, int itemLayoutId, int groupLayoutId, Context context) {
        this(itemLayoutId,groupLayoutId,context);
        this.items = items;
    }

    private ExpandableShoppingListAdapter(int itemLayoutId, int groupLayoutId, Context context) {
        this.itemLayoutId = itemLayoutId;
        this.groupLayoutId = groupLayoutId;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return itemsTree.keySet().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int result=0;
        GoodsGroup group=(GoodsGroup) getGroup(groupPosition);
        if(group!=null) {
           result= itemsTree
                    .get(group)
                    .size();
        }

        return  result;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemsTree.keyAt(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        GoodsGroup group=(GoodsGroup) getGroup(groupPosition);
        if(group!=null)
        {
            return itemsTree.get(group).get(childPosition);
        }
        else return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        GoodsGroup group=(GoodsGroup)getGroup(groupPosition);
        if(group!=null) return group.getId();
        else return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        GoodsItem child=(GoodsItem) getChild(groupPosition,childPosition);
        if(child!=null)
            return child.getId();
        else
            return 0;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=convertView;
        if(convertView==null)
        {
            LayoutInflater li=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=li.inflate(groupLayoutId,null);

            TextView textView=(TextView) view.findViewById(R.id.tv_group_header_name);
            ImageView ivExpansionIndicator=(ImageView) view.findViewById(R.id.ivExpandIndicator);
            if(textView!=null)
            {
                textView.setText(((GoodsGroup)getGroup(groupPosition)).getGroupName());
                if(isExpanded){
                    ivExpansionIndicator.setImageResource(R.drawable.ic_expand_less_black_24dp);
                    view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_bright) );
                }
                else{
                    ivExpansionIndicator.setImageResource(R.drawable.ic_expand_more_black_24dp);
                    view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_dark) );
                }
            }
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    @Override
    public Filter getFilter() {
        return goodsFilter;
    }

    private Filter goodsFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results= new FilterResults();

            if(constraint!=null)
            {
                suggestions.clear();

                List<GoodsItem> items=GoodsItem.SelectByNamePattern(constraint.toString().toLowerCase());
                suggestions= buildTree(items);
                results.values=suggestions;
                results.count=suggestions.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayMap<GoodsGroup,List<GoodsItem>> filteredTree;

            if(results.values!=null && results.count>0)
            {
                filteredTree=(ArrayMap<GoodsGroup,List<GoodsItem>>)results.values;
                itemsTree=filteredTree;
                notifyDataSetChanged();
            }


        }
    };

    private ArrayMap<GoodsGroup,List<GoodsItem>> buildTree(List<GoodsItem> items)
    {
        ArrayMap<GoodsGroup,List<GoodsItem>> result= new ArrayMap<>(items.size());
        for (GoodsItem item :
                items) {
            GoodsGroup group=item.getGoodsGroup();
            if(result.containsKey(group))//group already exists
            {
                result.get(group).add(item);
            }
            else
            {
                ArrayList<GoodsItem> child= new ArrayList<>();
                child.add(item);
                result.put(group,child);
            }
        }

        return result;
    }
}
