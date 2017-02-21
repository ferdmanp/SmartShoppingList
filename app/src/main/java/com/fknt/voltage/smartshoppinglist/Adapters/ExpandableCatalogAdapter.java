package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.GoodsItem;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.List;

/**
 * Created by SD on 14.02.2017.
 */
//https://habrahabr.ru/post/147546/

public class ExpandableCatalogAdapter extends BaseExpandableListAdapter {

    private List<GoodsGroup> groups;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context context;

    private ArrayMap<GoodsGroup,List<GoodsItem>> itemsTree;

    public ExpandableCatalogAdapter(List<GoodsGroup> groups, int itemLayoutId, int groupLayoutId, Context context) {
        this.groups = groups;
        this.itemLayoutId = itemLayoutId;
        this.groupLayoutId = groupLayoutId;
        this.context = context;
        initTree();

    }

    private View.OnClickListener OnEditClickListener;

    public View.OnClickListener getOnEditClickListener() {
        return OnEditClickListener;
    }

    public void setOnEditClickListener(View.OnClickListener onEditClickListener) {
        OnEditClickListener = onEditClickListener;
    }

    public View.OnClickListener getOnDeleteClickListener() {
        return OnDeleteClickListener;
    }

    public void setOnDeleteClickListener(View.OnClickListener onDeleteClickListener) {
        OnDeleteClickListener = onDeleteClickListener;
    }

    private View.OnClickListener OnDeleteClickListener;

    private void initTree()
    {
        itemsTree= new ArrayMap<>();
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
//        Log.d("MYDEBUG",String.valueOf(i));
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
            //DONE Описать лайаут
            view= LayoutInflater.from(this.context).inflate(groupLayoutId,null);

        }

        TextView tvGroupHeader=(TextView) view.findViewById(R.id.tv_group_header_name);
        ImageView ivExpansionIndicator=(ImageView) view.findViewById(R.id.ivExpandIndicator);

        tvGroupHeader.setText(this.getGroup(groupPosition).toString());
        //ivExpansionIndicator.setImageResource(R.drawable.groups_selector);

        if(isExpanded)
        {
            ivExpansionIndicator.setImageResource(R.drawable.ic_expand_less_black_24dp);
            view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_bright) );
        }
        else
        {
            ivExpansionIndicator.setImageResource(R.drawable.ic_expand_more_black_24dp);
            view.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_dark) );
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=convertView;

        if(view==null)
        {
            //DONE Описать лайаут
            view= LayoutInflater.from(this.context).inflate(itemLayoutId,null);


        }

        TextView tvItemName=(TextView) view.findViewById(R.id.tv_item_name);
//            ImageButton ivDelete=(ImageButton) view.findViewById(R.id.ivDelete);
//            ImageButton ivEdit=(ImageButton) view.findViewById(R.id.ivEdit);
        ImageView ivDelete=(ImageView) view.findViewById(R.id.ivDelete);
        ImageView ivEdit=(ImageView) view.findViewById(R.id.ivEdit);

        tvItemName.setText(this.getChild(groupPosition,childPosition).toString());
//            ivDelete.setOnClickListener(this.OnDeleteClickListener);
//            ivEdit.setOnClickListener(this.OnEditClickListener);
//            ivDelete.setTag(this.getChild(groupPosition,childPosition));
//            ivEdit.setTag(this.getChild(groupPosition,childPosition));
        view.setTag(this.getChild(groupPosition,childPosition));


        return view;
    }
}
