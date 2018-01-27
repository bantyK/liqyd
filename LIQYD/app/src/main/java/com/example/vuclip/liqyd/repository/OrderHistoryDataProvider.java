package com.example.vuclip.liqyd.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.vuclip.liqyd.database.AppDatabase;
import com.example.vuclip.liqyd.models.Order;

import java.util.List;

/**
 * Created by Banty on 26/01/18.
 */

public class OrderHistoryDataProvider {

    private OrderHistoryDataListener mOrderHistoryDataListener;

    public OrderHistoryDataProvider(OrderHistoryDataListener listener) {
        this.mOrderHistoryDataListener = listener;
    }

    public void getOrderHistoryData() {
        new OrderHistoryTask(mOrderHistoryDataListener).execute();
    }
    public interface OrderHistoryDataListener {
        void dataLoaded(List<Order> orderList);
    }

    private static class OrderHistoryTask extends AsyncTask<Void, Void, List<Order>> {

        private final OrderHistoryDataListener listener;

        public OrderHistoryTask(OrderHistoryDataListener orderHistoryDataListener) {
            this.listener = orderHistoryDataListener;
        }

        @Override
        protected List<Order> doInBackground(Void... voids) {
            return AppDatabase.getAppDatebase().orderDao().getOrders();
        }

        @Override
        protected void onPostExecute(List<Order> orders) {
            listener.dataLoaded(orders);
        }
    }
}

