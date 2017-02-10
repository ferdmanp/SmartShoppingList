package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */
@Table(database = MyDatabase.class)
public class ListHeader extends BaseModel {

    public ListHeader() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public ListHeader(int id, String title, Date dateCreate, double totalSum) {
        super();
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        TotalSum = totalSum;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public double getTotalSum() {
        return TotalSum;
    }

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String title;

    @Column
    Date dateCreate;

    @Column
    double TotalSum;


}
