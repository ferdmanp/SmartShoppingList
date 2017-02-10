package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SD on 10.02.2017.
 */

@Table(database = MyDatabase.class)
public class GoodsGroup extends BaseModel {

    public GoodsGroup() {
        super();
    }

    public GoodsGroup(String groupName) {
        super();
        this.groupName = groupName;
    }

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String groupName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
