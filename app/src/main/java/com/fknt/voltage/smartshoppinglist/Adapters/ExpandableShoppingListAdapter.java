package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.GoodsItem;

import java.util.List;

/**
 * Created by voltage on 10.03.2017. Project SmartShoppingList
 */

public class ExpandableShoppingListAdapter extends BaseExpandableListAdapter {

    private List<GoodsGroup> groups;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context context;

    private ArrayMap<GoodsGroup,List<GoodsItem>> itemsTree;

    public ExpandableShoppingListAdapter(List<GoodsGroup> groups, int itemLayoutId, int groupLayoutId, Context context) {
        this.groups = groups;
        this.itemLayoutId = itemLayoutId;
        this.groupLayoutId = groupLayoutId;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
