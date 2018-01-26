package com.example.vuclip.liqyd.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vuclip.liqyd.models.Order;

import java.util.List;

/**
 * Created by Banty on 26/01/18.
 */

@Dao
public interface OrderDao {

    @Query("SELECT * FROM ORDERS")
    List<Order> getOrders();

    @Insert
    void insert(Order... order);
}
