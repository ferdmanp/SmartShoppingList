package com.fknt.voltage.smartshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.fknt.voltage.smartshoppinglist.Adapters.ExpandableCatalogAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

//http://developer.alexanderklimov.ru/android/views/expandablelistview.php
//https://developer.android.com/reference/android/widget/SimpleExpandableListAdapter.html

public class GoodsCatalogActivityEx extends AppCompatActivity
{

    private enum ClickListenerMode {EDIT, DELETE}

    private class GoodsItemClickListener implements View.OnClickListener
    {

        private GoodsItem item;
        ClickListenerMode clickListenerMode;

        public GoodsItemClickListener(GoodsItem item, ClickListenerMode clickListenerMode) {
            this.item = item;
            this.clickListenerMode = clickListenerMode;
        }

        @Override
        public void onClick(View v) {
            switch (clickListenerMode) {
                case DELETE:
                    ConfirmDialog dlg=new ConfirmDialog(getContext(), getString(R.string.delete_dialog_title), new Callable<Object>() {
                        @Override
                        public Object call() throws Exception {
                            DeleteItem(item);
                            return true;
                        }
                    });
                    dlg.Show();
                    RefreshData();
                    break;
                case EDIT:
                    Intent intent=new Intent(getContext(),FormNewGoodActivity.class);
                    intent.putExtra("EDIT_ITEM_ID",item.getId());
                    startActivity(intent);
                    RefreshData();
                    break;
            }
        }
    }

    private List<GoodsGroup> groups;
    private List<GoodsItem> goods;

    private ExpandableListAdapter adapter;
    private ExpandableListView exListView;
    private ExpandableCatalogAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_catalog_ex);


        RefreshData();



        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ImageView ivDelete=(ImageView)v.findViewById(R.id.ivDelete);
                ImageView ivEdit=(ImageView)v.findViewById(R.id.ivEdit);

                GoodsItem item=(GoodsItem) v.getTag();

                //ivDelete.setOnClickListener(new GoodsItemClickListener(item,ClickListenerMode.DELETE));
                ivEdit.setOnClickListener(new GoodsItemClickListener(item,ClickListenerMode.EDIT));

                RefreshData();


                return true;
            }
        });


    }

    private void DeleteItem(GoodsItem item)
    {
        item.delete();
        RefreshData();
    }





    private void RefreshData(){


        adapter2=new ExpandableCatalogAdapter(GoodsGroup.SelectAll()
                ,R.layout.item_expandable_item
                ,R.layout.item_expandable_header
                ,getContext());

        //adapter2.registerDataSetObserver(this);

        exListView =(ExpandableListView) findViewById(R.id.elv_goods_list);

//        Configuration config=getContext().getResources().getConfiguration();
//        exListView.setIndicatorBounds(config.screenWidthDp-50,config.screenWidthDp-20);
        //exListView.setGroupIndicator();
        exListView.setAdapter(adapter2);
        exListView.invalidateViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RefreshData();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        ConfirmDialog dlg= new ConfirmDialog(getContext()
                , getResources().getString(R.string.delete_all_confirmation)
                , new Callable<Object>() {
            @Override
            public Boolean call() throws Exception {
                deleteAllGoods();
                return true;
            }
        });
        dlg.Show();

    }

    private void deleteAllGoods() {
        GoodsItem.DeleteAll();
        RefreshData();
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
