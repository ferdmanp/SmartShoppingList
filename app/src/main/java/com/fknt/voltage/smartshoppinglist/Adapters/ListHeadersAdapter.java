package com.fknt.voltage.smartshoppinglist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fknt.voltage.smartshoppinglist.ListHeader;
import com.fknt.voltage.smartshoppinglist.R;

import java.util.List;

/**
 * Created by voltage on 09.02.2017. Project SmartShoppingList
 */

public class ListHeadersAdapter extends BaseAdapter {

    List<ListHeader> itemsList;
    Context context;

    public ListHeadersAdapter(List<ListHeader> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return itemsList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        ListHeader header= (ListHeader) this.getItem(position);
        return header.getId();
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(convertView==null)
        {
            ListHeader item=(ListHeader) this.getItem(position);
            view=LayoutInflater.from(context).inflate(R.layout.item_list_title,null);

            TextView tvTitle=(TextView) view.findViewById(R.id.tv_group_name);
            TextView tvDate=(TextView)view.findViewById(R.id.tv_group_date);
            TextView tvPrice=(TextView)view.findViewById(R.id.tv_group_price);

            tvTitle.setText(item.getTitle());
            tvDate.setText(String.valueOf(item.getDateCreate()));
            tvPrice.setText(String.valueOf(item.getTotalSum()));


        }
        return view;
    }
}
