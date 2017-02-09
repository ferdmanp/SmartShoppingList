package com.fknt.voltage.smartshoppinglist.DAL;

import android.provider.BaseColumns;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

public final class ShoppingListDBContract {

    private ShoppingListDBContract() {

    }

    public static final String ID_FIELD_TYPE=" INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String TEXT_TYPE=" TEXT";
    public static final String COMMA=",";

    public static class ListItemHeaderTable implements BaseColumns {
        public static final String TABLE_NAME="tblGoodsListHeaders";
        public static final String COLUMN_NAME_ID="_id";
        public static final String COLUMN_NAME_NAME="Name";
        public static final String COLUMN_NAME_DATE_CREATED="date_create";
        public static final String COLUMN_NAME_DATE_CLOSE="date_close";
        public static final String COLUMN_NAME_LONGITUDE ="Longitude";
        public static final String COLUMN_NAME_LATITUDE ="Latitude";
        public static final String COLUMN_NAME_TOTALSUM =   "total_sum";




        public static final String SQL_CREATE_TABLE="create table if not exists "
                +ListItemHeaderTable.TABLE_NAME
                +"("
                +COLUMN_NAME_ID+ID_FIELD_TYPE+COMMA
                +COLUMN_NAME_NAME+TEXT_TYPE+COMMA
                +COLUMN_NAME_DATE_CREATED+TEXT_TYPE+COMMA
                +COLUMN_NAME_DATE_CLOSE+TEXT_TYPE+COMMA
                +COLUMN_NAME_LONGITUDE +TEXT_TYPE+COMMA
                +COLUMN_NAME_LATITUDE +TEXT_TYPE+COMMA
                +COLUMN_NAME_TOTALSUM+TEXT_TYPE
                +")";

        public static final String SQL_DELETE_TABLE="drop table if exists "+TABLE_NAME;
    }




}

