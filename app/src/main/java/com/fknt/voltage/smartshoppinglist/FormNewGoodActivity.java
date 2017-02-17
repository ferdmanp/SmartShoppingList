package com.fknt.voltage.smartshoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.fknt.voltage.smartshoppinglist.Adapters.GroupsSpinnerAdapter;

import java.util.List;
//http://stackoverflow.com/questions/1625249/android-how-to-bind-spinner-to-custom-object-list
public class FormNewGoodActivity extends AppCompatActivity

{

    private enum ShowMode {NEW,EDIT};

    GoodsItem formItem;
    ShowMode currentActivityMode;

    EditText etName;
    EditText etUnits;
    EditText etBasicPrice;
    Spinner spGroups;
    Button btnSave;
    Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_new_good);

        currentActivityMode=ShowMode.NEW;




        etName=(EditText) findViewById(R.id.etName);
        etUnits=(EditText) findViewById(R.id.etMeasureUnit);
        etBasicPrice=(EditText) findViewById(R.id.etBasicPrice);
        spGroups=(Spinner) findViewById(R.id.spGroups);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnCancel=(Button) findViewById(R.id.btnCancel);

        ArrayAdapter adapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,GoodsGroup.SelectAll());
        spGroups.setAdapter(adapter);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null && bundle.containsKey("EDIT_ITEM_KEY"))
        {
            int itemId=bundle.getInt("EDIT_ITEM");
            formItem=GoodsItem.SelectById(itemId);
            currentActivityMode=ShowMode.EDIT;
            etName.setText(formItem.getName());
            etUnits.setText(formItem.getUnit_name());
            etBasicPrice.setText(String.valueOf(formItem.getPrice()));
            spGroups.setSelection(adapter.getPosition(formItem.getGoodsGroup()));
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewItem();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






    }

    private void CreateNewItem() {

        String name=etName.getText().toString();
        String measure_unit=etUnits.getText().toString();
        double basic_price=Double.valueOf(etBasicPrice.getText().toString());
        GoodsGroup group=(GoodsGroup) spGroups.getSelectedItem();


        GoodsItem goodsItem= new GoodsItem();
        goodsItem.setName(name);
        goodsItem.setUnit_name(measure_unit);
        goodsItem.setPrice(basic_price);
        goodsItem.setGoodsGroup(group);
        goodsItem.save();
        goodsItem=null;
        group=null;

        finish();
    }

    private Activity getContext(){return  FormNewGoodActivity.this;}

    private void Refresh()
    {
        ArrayAdapter adapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,GoodsGroup.SelectAll());
        spGroups.setAdapter(adapter);
    }


}
