package com.fknt.voltage.smartshoppinglist;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "ShoppingListDB";
    public static final int VERSION=1;
}
