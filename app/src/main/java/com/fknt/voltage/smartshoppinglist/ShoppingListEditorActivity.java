package com.fknt.voltage.smartshoppinglist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.fknt.voltage.smartshoppinglist.Adapters.ListItemAdapter;
import com.fknt.voltage.smartshoppinglist.Adapters.ListItemAdapterEx;

import java.util.ArrayList;

public class ShoppingListEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_editor);

//        ListItemAdapterEx srchAdapter=new ListItemAdapterEx(
//                this.getContext(),
//                R.layout.activity_shopping_list_editor,
//                R.id.tv_goods_item_label,
//                (ArrayList<GoodsItem>) GoodsItem.SelectAll()
//        );
        ArrayAdapter<GoodsItem> adapter= new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_dropdown_item_1line,
                GoodsItem.SelectAll()
        );
        AutoCompleteTextView actvSearchItem=(AutoCompleteTextView) findViewById(R.id.actvGoodsItem);
        actvSearchItem.setThreshold(2);
        actvSearchItem.setTextColor(Color.RED);
        actvSearchItem.setAdapter(adapter);

        actvSearchItem.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
    }

    private Context getContext(){return ShoppingListEditorActivity.this;}
}
