package com.fknt.voltage.smartshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://developer.alexanderklimov.ru/android/views/expandablelistview.php
//https://developer.android.com/reference/android/widget/SimpleExpandableListAdapter.html

public class GoodsCatalogActivityEx extends AppCompatActivity {

    private List<GoodsGroup> groups;
    private List<GoodsItem> goods;

    private ExpandableListAdapter adapter;
    private ExpandableListView listView;

    private static final String GOODS_GROUP_KEY  = "GOODS_GROUP";
    private static final String GOODS_ITEM_KEY  = "GOODS_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_catalog_ex);

        Map<String,GoodsGroup> groupsMap;
        Map<String,GoodsItem> goodsMap;
        ArrayList<Map<String,GoodsGroup>> groupData= new ArrayList<>();
        ArrayList<Map<String,GoodsItem>> goodsDataItem= new ArrayList<>();
        ArrayList<ArrayList<Map<String,GoodsItem>>> childData= new ArrayList<>();

        groups=getGroups();

        for(GoodsGroup group:groups)
        {
            groupsMap= new HashMap<>();
            groupsMap.put(GOODS_GROUP_KEY,group);
            groupData.add(groupsMap);
        }

        String groupFrom[]=new String[]{GOODS_GROUP_KEY};
        int groupTo[]=new int[]{android.R.id.text1};

        for(GoodsGroup group:groups)
        {

            goodsDataItem= new ArrayList<>();
            List<GoodsItem> items=getGoodsByGroupId(group.getId());

            for (GoodsItem item:items)
            {
                goodsMap= new HashMap<>();
                goodsMap.put(GOODS_ITEM_KEY,item);
                goodsDataItem.add(goodsMap);
            }
            childData.add(goodsDataItem);
        }

        String childFrom[]= new String[] {GOODS_ITEM_KEY};
        int childTo[]= new int[]{android.R.id.text1};

        adapter=new SimpleExpandableListAdapter(
                getContext(),
                groupData,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1,
                childFrom, childTo
        );

        listView=(ExpandableListView) findViewById(R.id.elv_goods_list);
        listView.setAdapter(adapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_new:
                CreateNewItem();
                break;
            case R.id.action_delete_all:
                DeleteAllItemsWithConfirm();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DeleteAllItemsWithConfirm() {

    }

    private void CreateNewItem() {

    }

    private void CreateNewItem(String itemName, GoodsGroup group)
    {

    }

    private Context getContext(){return GoodsCatalogActivityEx.this;}

    private List<GoodsGroup> getGroups()
    {
        return SQLite
                .select()
                .from(GoodsGroup.class)
                .queryList();
    }

    private List<GoodsItem> getGoodsByGroupId(int groupId)
    {
        return SQLite
                .select()
                .from(GoodsItem.class)
                .where(GoodsItem_Table.goodsGroup_id.eq(groupId))
                .queryList();
    }




}
