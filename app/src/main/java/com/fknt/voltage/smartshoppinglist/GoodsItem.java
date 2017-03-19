package com.fknt.voltage.smartshoppinglist;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IntProperty;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

@Table(database = MyDatabase.class)
public class GoodsItem extends BaseModel
                        implements Serializable {

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
    @PrimaryKey(autoincrement = true)
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

    @Override
    public String toString() {
        return name;
    }


//    public void deleteItem()
//    {
//
//    }

    public static List<GoodsItem> SelectAll()
    {
        return SQLite
                .select()
                .from(GoodsItem.class)
                .queryList();
    }

    public static  List<GoodsItem> SelectListByGroupId(int groupId)
    {
        return  SQLite
                .select()
                .from(GoodsItem.class)
                .where(GoodsItem_Table.goodsGroup_id.eq(groupId))
                .queryList();
    }

    public static GoodsItem SelectById(int id)
    {
        return SQLite
                .select()
                .from(GoodsItem.class)
                .where(GoodsItem_Table.id.eq(id))
                .querySingle();
    }

    public static List<GoodsItem> SelectByNamePattern(String pattern)
    {
        return SQLite
                .select()
                .from(GoodsItem.class)
                .where(GoodsItem_Table.name.like(pattern))
                .queryList();
    }

    public static void DeleteAll()
    {
        SQLite
                .delete()
                .from(GoodsItem.class)
                .execute();
    }

    public static void DeleteAll(int groupId)
    {
        SQLite.delete()
                .from(GoodsItem.class)
                .where(GoodsItem_Table.goodsGroup_id.eq(groupId))
                .execute();
    }
}
