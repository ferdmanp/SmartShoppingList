package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.GoodsItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SD on 14.02.2017.
 */
//https://habrahabr.ru/post/147546/

public class ExpandableCatalogAdapter extends BaseExpandableListAdapter {

    private List<GoodsGroup> groups;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;

    private HashMap<GoodsGroup,List<GoodsItem>> itemsTree;

    public ExpandableCatalogAdapter(List<GoodsGroup> groups, int itemLayoutId, int groupLayoutId, Context ctx) {
        this.groups = groups;
        this.itemLayoutId = itemLayoutId;
        this.groupLayoutId = groupLayoutId;
        this.ctx = ctx;

    }

    private void initTree()
    {
        itemsTree= new HashMap<>();
        for(GoodsGroup group: groups)
        {
            itemsTree.put(group,group.GetChildItems());
        }
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return itemsTree
                .get(groups.get(i))
                .size();
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return itemsTree
                .get(groups.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return groups.get(i).getId();
    }

    @Override
    public long getChildId(int i, int i1) {
        return itemsTree
                .get(groups.get(i))
                .get(i1)
                .getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,  View convertView, ViewGroup parent) {
        View view=convertView;

        if(view==null)
        {
            //TODO Описать лайаут
            view= LayoutInflater.from(this.ctx).inflate(groupLayoutId,null);

        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;

        if(view==null)
        {
            //TODO Описать лайаут
            view= LayoutInflater.from(this.ctx).inflate(itemLayoutId,null);

        }

        return view;
    }
}
