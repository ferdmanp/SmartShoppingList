package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.DAL.ShoppingListDBContract;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.Date;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

public class ListHeadersCursorAdapter extends CursorAdapter {

    /**
     * Recommended constructor.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ListHeadersCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_title,parent,false);
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvHeader=(TextView) view.findViewById(R.id.tv_group_name);
        TextView tvDate=(TextView)view.findViewById(R.id.tv_group_date);
        TextView tvPrice=(TextView)view.findViewById(R.id.tv_group_price);

        String header_title=cursor.getString(cursor.getColumnIndexOrThrow(ShoppingListDBContract.ListItemHeaderTable.COLUMN_NAME_NAME));
        String header_date=cursor.getString(cursor.getColumnIndexOrThrow(ShoppingListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CREATED));
        double header_sum=cursor.getDouble(cursor.getColumnIndexOrThrow(ShoppingListDBContract.ListItemHeaderTable.COLUMN_NAME_TOTALSUM));

        tvHeader.setText(header_title);
        tvDate.setText(header_date);
        tvPrice.setText(String.valueOf(header_sum));
    }
}
