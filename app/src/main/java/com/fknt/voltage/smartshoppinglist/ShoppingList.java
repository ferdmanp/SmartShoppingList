package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SD on 10.02.2017.
 */

@Table(database = MyDatabase.class)
public class ShoppingList extends BaseModel {

    public ShoppingList() {
        super();
    }

    public ShoppingList(ListHeader title, GoodsItem good, double quantity, double realPrice, double cost) {
        this.title = title;
        this.good = good;
        this.quantity = quantity;
        this.realPrice = realPrice;
        this.cost = cost;
    }

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    @ForeignKey(tableClass = ListHeader.class)
    ListHeader title;

    @Column
    @ForeignKey(tableClass = GoodsItem.class)
    GoodsItem good;

    @Column
    double quantity;

    @Column
    double realPrice;

    @Column
    double cost;

    @Column(defaultValue = "false")
    boolean isBought;


}
