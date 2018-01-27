package com.example.vuclip.liqyd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.models.Order;

import java.util.List;

/**
 * Created by Banty on 27/01/18.
 */

public class OrderHistoryListAdapter extends BaseAdapter {
    private final List<Order> mOrders;
    private final Context mContext;

    public OrderHistoryListAdapter(List<Order> orders, Context context) {
        mOrders = orders;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mOrders.get(position).getOrderId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.order_history_single_list, parent, false);
        ((ImageView) view.findViewById(R.id.product_image)).setImageResource(((Order) getItem(position)).getProductImageResource());
        ((TextView) view.findViewById(R.id.product_name)).setText(((Order) getItem(position)).getProductName());
        ((TextView) view.findViewById(R.id.order_placed)).setText(((Order) getItem(position)).getPurchaseDate());
        ((TextView) view.findViewById(R.id.total_amount_paid)).setText(String.valueOf(((Order) getItem(position)).getTotalAmountPaid()));
        return view;
    }
}
