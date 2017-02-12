package com.fknt.voltage.smartshoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fknt.voltage.smartshoppinglist.Adapters.GroupItemAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class GroupsListActivity extends AppCompatActivity {

    List<GoodsGroup> groupsList;
    ListView lvGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);
        setTitle(R.string.title_activity_groups_list);

        groupsList=SelectAllGroups();
        GroupItemAdapter adapter= new GroupItemAdapter(getContext(),groupsList);

        lvGroups=(ListView)findViewById(R.id.lv_goods_groups);
        lvGroups.setAdapter(adapter);



    }

    private Activity getContext() {
        return GroupsListActivity.this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_groups_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_new:
                CreateNewGroup();
                break;
            case R.id.action_delete_all:
                DeleteAllItemsWithConfirm();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DeleteAllItemsWithConfirm()
    {
        ConfirmDialog dlg= new ConfirmDialog(getContext(), getString(R.string.delete_all_confirmation), new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                DeleteAllItems();
                return true;
            }
        });

        dlg.Show();
    }

    private void DeleteAllItems() {
        SQLite.delete(GoodsGroup.class).execute();
        Refresh();
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
                        Refresh();
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

    private void Refresh() {
        groupsList=SelectAllGroups();
        GroupItemAdapter adapter= new GroupItemAdapter(getContext(),groupsList);
        lvGroups=(ListView)findViewById(R.id.lv_goods_groups);
        lvGroups.setAdapter(adapter);
    }

    private void CreateNewGroup(String newGroupName) {
        GoodsGroup group= new GoodsGroup();
        group.setGroupName(newGroupName);
        group.save();
        group=null;
    }

    private List<GoodsGroup> SelectAllGroups(){
        return SQLite
                .select()
                .from(GoodsGroup.class)
                .queryList();
    }

    private String GetGroupById(int id)
    {
        return SQLite
                .select()
                .from(GoodsGroup.class)
                .where(GoodsGroup_Table.id.eq(id))
                .queryList()
                .get(0)
                .getGroupName();
    }


}
