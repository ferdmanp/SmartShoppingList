package com.fknt.voltage.smartshoppinglist.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fknt.voltage.smartshoppinglist.DAL.ShoppingListDBContract.ListItemHeaderTable;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "GoodsList.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ListItemHeaderTable.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ListItemHeaderTable.SQL_DELETE_TABLE);
        onCreate(db);
    }

}

