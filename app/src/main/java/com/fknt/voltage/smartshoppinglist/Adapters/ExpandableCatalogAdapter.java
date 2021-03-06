package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.ConfirmDialog;
import com.fknt.voltage.smartshoppinglist.FormNewGoodActivity;
import com.fknt.voltage.smartshoppinglist.GoodsGroup;
import com.fknt.voltage.smartshoppinglist.GoodsItem;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;


/**
 * Created by SD on 14.02.2017.
 */
//https://habrahabr.ru/post/147546/

public class ExpandableCatalogAdapter extends BaseExpandableListAdapter
{

    private class DeleteAction implements Callable<Object>
    {
        private GoodsItem item;

        public DeleteAction(GoodsItem item) {
            this.item = item;
        }

        @Override
        public Object call() throws Exception {
            deleteItem(item);
            return null;
        }
    }

    private List<GoodsGroup> groups;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context context;

    private ArrayMap<GoodsGroup,List<GoodsItem>> itemsTree;

    public ExpandableCatalogAdapter(List<GoodsGroup> groups, int itemLayoutId, int groupLayoutId, Context context) {
        //Log.d("DEBUG","Ctor");
        this.groups = groups;
        this.itemLayoutId = itemLayoutId;
        this.groupLayoutId = groupLayoutId;
        this.context = context;
        initTree();
        OnDeleteClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("DEBUG",v.getContext().toString());
                GoodsItem item=(GoodsItem) v.getTag();

                ConfirmDialog dlg= new ConfirmDialog(v.getContext(), "Delete element " + item,
                        new DeleteAction(item)
                        );
                dlg.Show();


            }
        };

    }

    private void deleteItem(GoodsItem item)
    {
        if(item!=null)
        {
            deleteFromTree(item);
            deleteFromDB(item);
            reInitTree();
            notifyDataSetChanged();
        }
    }

    private void reInitTree() {
        this.groups=GoodsGroup.SelectAll();
        initTree();

    }


    private void deleteFromDB(GoodsItem item) {
        item.delete();
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

    //DONE Организовать удаление єлемента из дерева
    private boolean deleteFromTree(GoodsItem item)
    {
        boolean result = false;
        GoodsGroup group=item.getGoodsGroup();
        if(itemsTree.containsKey(group)) {
           for(Iterator<Map.Entry<GoodsGroup,List<GoodsItem>>> iterator=itemsTree.entrySet().iterator();iterator.hasNext();)
           {
               Map.Entry<GoodsGroup,List<GoodsItem>> groupEntry=iterator.next();
               if(groupEntry.getKey().getId()==group.getId())
               {
                   for(Iterator<GoodsItem> itemIterator=groupEntry.getValue().iterator();iterator.hasNext();)
                   {
                       GoodsItem mItem = itemIterator.next();
                       if(mItem.getId()==item.getId()) mItem.delete();

                   }
               }
           }
        }
        return  result;

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

        tvGroupHeader.setText(this.getGroup(groupPosition).toString()
        +"("+this.getChildrenCount(groupPosition)+")"
        );
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
        final GoodsItem item=(GoodsItem) this.getChild(groupPosition,childPosition);

        if(view==null)
        {
            //DONE Описать лайаут
            view= LayoutInflater.from(this.context).inflate(itemLayoutId,null);


        }

        TextView tvItemName=(TextView) view.findViewById(R.id.tv_item_name);

        ImageView ivDelete=(ImageView) view.findViewById(R.id.ivDelete);
        ImageView ivEdit=(ImageView) view.findViewById(R.id.ivEdit);

        tvItemName.setText(this.getChild(groupPosition,childPosition).toString());

        ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDialog dlg= new ConfirmDialog(
                            v.getContext(),"Delete "+item.getName()+"?",
                            new DeleteAction(item)
                    );

                    dlg.Show();

                   //deleteItem(item);
                }
            });
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId=item.getId();
                Intent intent=new Intent(v.getContext(), FormNewGoodActivity.class);
//                Bundle params=new Bundle();
//                params.putInt("ITEM_ID",itemId);
//                intent.putExtra("params",params);
                intent.putExtra("ITEM_ID",itemId);
                v.getContext().startActivity(intent);
            }
        });

        view.setTag(this.getChild(groupPosition,childPosition));


        return view;
    }






    /////////////-----EXPERIMENTAL----///

    private ArrayList<DataSetObserver> observes= new ArrayList<>();

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        observes.add(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for(DataSetObserver observer:observes)
        {
            observer.onChanged();
        }
    }

    //---
}
