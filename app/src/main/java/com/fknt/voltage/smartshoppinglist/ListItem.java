package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

@Table(database = MyDatabase.class)
public class ListItem extends BaseModel {

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ListItem() {

        super();
    }

    public ListItem(int id, ListHeader listHeader, String name, double quantity, String unit_name, double price, double cost) {
        super();
        this.id = id;
        this.listHeader = listHeader;
        this.name = name;
        this.quantity = quantity;
        this.unit_name = unit_name;
        this.price = price;
        this.cost = cost;
    }

    @Column
    @PrimaryKey
    int id;

    @Column
    @ForeignKey
    ListHeader listHeader;

    @Column
    String name;

    @Column
    double quantity;

    @Column
    String unit_name;

    @Column
    double price;

    @Column
    double cost;
}
