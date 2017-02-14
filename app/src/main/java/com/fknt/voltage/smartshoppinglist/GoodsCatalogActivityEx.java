package com.fknt.voltage.smartshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;

import com.fknt.voltage.smartshoppinglist.Adapters.ExpandableCatalogAdapter;
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
    private ExpandableCatalogAdapter adapter2;

    private static final String GOODS_GROUP_KEY  = "GOODS_GROUP";
    private static final String GOODS_ITEM_KEY  = "GOODS_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_catalog_ex);


        RefreshData();


    }



    private void RefreshData(){

        adapter2=new ExpandableCatalogAdapter(GoodsGroup.SelectAll()
                ,R.layout.item_expandable_item
                ,R.layout.item_expandable_header
                ,getContext());

        listView=(ExpandableListView) findViewById(R.id.elv_goods_list);
        listView.setIndicatorBounds(0,20);
        listView.setAdapter(adapter2);




    }

    @Override
    protected void onResume() {
        super.onResume();
        RefreshData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items_list, menu);
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
        Intent intent= new Intent(getContext(),FormNewGoodActivity.class);
        startActivity(intent);
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