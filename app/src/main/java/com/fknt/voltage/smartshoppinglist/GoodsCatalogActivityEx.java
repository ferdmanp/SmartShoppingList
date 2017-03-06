package com.fknt.voltage.smartshoppinglist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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


    private ExpandableListView exListView;
    private ExpandableCatalogAdapter adapter2;
    private enum FormShowMode {EDIT_CATALOG, EDIT_LIST};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_catalog_ex);
        setTitle(getString(R.string.title_goods_list));


        RefreshData();


    }







    private void RefreshData(){

        adapter2=new ExpandableCatalogAdapter(GoodsGroup.SelectAll()
                ,R.layout.item_expandable_item
                ,R.layout.item_expandable_header
                ,getContext());


        exListView =(ExpandableListView) findViewById(R.id.elv_goods_list);

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
            case R.id.action_new_group:
                CreateNewGroup();
                break;
            case R.id.action_delete_all:
                DeleteAllItemsWithConfirm();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void CreateNewGroup() {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.title_new_group_dialog);
        final EditText input= new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.btn_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newGroupName=input.getText().toString();
                        CreateNewGroup(newGroupName);
                        dialog.dismiss();
                        RefreshData();
                    }
                });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void CreateNewGroup(String newGroupName) {
        GoodsGroup group= new GoodsGroup();
        group.setGroupName(newGroupName);
        group.save();
        group=null;
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








}
