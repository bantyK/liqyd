package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.models.Order;
import com.example.vuclip.liqyd.repository.OrderHistoryDataProvider;

import java.util.List;

/**
 * Created by Banty on 14/01/18.
 */

public class OrderHistoryFragment extends Fragment implements OrderHistoryDataProvider.OrderHistoryDataListener {
    private static final String TAG = "OrderHistoryFragment";

    public OrderHistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_order_history, container, false);
        new OrderHistoryDataProvider(this).getOrderHistoryData();
        return view;
    }


    @Override
    public void dataLoaded(List<Order> orderList) {
        Log.d(TAG, "dataLoaded: orderList size : " + orderList.size());
    }
}
