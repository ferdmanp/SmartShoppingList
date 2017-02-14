package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

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

    @Override
    public String toString() {
        return groupName+"("+String.valueOf(id)+")";
    }

    public List<GoodsItem> GetChildItems()
    {
        return GoodsItem.SelectListByGroupId(this.getId());
    }

    public static List<GoodsGroup> SelectAll()
    {
        return SQLite
                .select()
                .from(GoodsGroup.class)
                .queryList();
    }

    public static List<GoodsItem> SelectChildItems(GoodsGroup group)
    {
        return SelectChildItems(group.getId());
    }

    private static List<GoodsItem> SelectChildItems(int groupId) {
        return GoodsItem.SelectListByGroupId(groupId);
    }
}
