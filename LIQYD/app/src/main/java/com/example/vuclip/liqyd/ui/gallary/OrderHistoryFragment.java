package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.adapters.OrderHistoryListAdapter;
import com.example.vuclip.liqyd.models.Order;
import com.example.vuclip.liqyd.repository.OrderHistoryDataProvider;

import java.util.List;

/**
 * Created by Banty on 14/01/18.
 */

public class OrderHistoryFragment extends Fragment implements OrderHistoryDataProvider.OrderHistoryDataListener {
    private static final String TAG = "OrderHistoryFragment";

    private ListView orderHistoryList;
    private TextView noPurchaseTextView;

    public OrderHistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_order_history, container, false);
        orderHistoryList = view.findViewById(R.id.order_history_list);
        noPurchaseTextView = view.findViewById(R.id.no_purchase_text);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new OrderHistoryDataProvider(this).getOrderHistoryData();
    }

    @Override
    public void dataLoaded(List<Order> orderList) {
        Log.d(TAG, "dataLoaded: orderList size : " + orderList.size());
        if (orderList.size() > 0) {
            noPurchaseTextView.setVisibility(View.GONE);
            orderHistoryList.setVisibility(View.VISIBLE);
            OrderHistoryListAdapter adapter = new OrderHistoryListAdapter(orderList, getContext());
            orderHistoryList.setAdapter(adapter);
        }
    }
}
