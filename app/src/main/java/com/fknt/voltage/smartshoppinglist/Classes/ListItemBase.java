package com.fknt.voltage.smartshoppinglist.Classes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

public abstract class ListItemBase {

    public abstract int GetId();

    public abstract View getView(Activity context, View convertView, ViewGroup parent);


}

