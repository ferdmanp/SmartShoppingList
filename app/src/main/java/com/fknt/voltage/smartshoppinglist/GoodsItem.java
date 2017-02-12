package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

@Table(database = MyDatabase.class)
public class GoodsItem extends BaseModel {



    public GoodsItem() {

        super();
    }

    public GoodsItem( GoodsGroup goodsGroup, String name, String unit_name, double price) {
        super();
        this.goodsGroup = goodsGroup;
        this.name = name;
        this.unit_name = unit_name;
        this.price = price;
    }

    @Column
    @PrimaryKey
    int id;

    @Column
    @ForeignKey(tableClass = GoodsGroup.class)
    GoodsGroup goodsGroup;

    @Column
    String name;

    @Column
    String unit_name;

    @Column
    double price;

    @Column
    Date lastBuyDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsGroup getGoodsGroup() {
        return goodsGroup;
    }

    public void setGoodsGroup(GoodsGroup goodsGroup) {
        this.goodsGroup = goodsGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getLastBuyDate() {
        return lastBuyDate;
    }

    public void setLastBuyDate(Date lastBuyDate) {
        this.lastBuyDate = lastBuyDate;
    }
}
