package com.fknt.voltage.smartshoppinglist;

import android.app.Activity;
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


    GoodsGroup selectedGroup;

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

        etName=(EditText) findViewById(R.id.etName);
        etUnits=(EditText) findViewById(R.id.etMeasureUnit);
        etBasicPrice=(EditText) findViewById(R.id.etBasicPrice);
        spGroups=(Spinner) findViewById(R.id.spGroups);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnCancel=(Button) findViewById(R.id.btnCancel);




//        final GroupsSpinnerAdapter adapter= new GroupsSpinnerAdapter(getContext(),android.R.layout.simple_spinner_item,groupsList.toArray(new GoodsGroup[groupsList.size()]));
//        spGroups.setAdapter(adapter);
//        spGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                selectedGroup=adapter.getItem(i);
//            }
//        });

        ArrayAdapter adapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,GoodsGroup.SelectAll());
        spGroups.setAdapter(adapter);



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
