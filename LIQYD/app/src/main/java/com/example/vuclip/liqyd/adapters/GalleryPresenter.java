package com.example.vuclip.liqyd.adapters;

import android.content.Context;
import android.content.Intent;

import com.example.vuclip.liqyd.helper.IntentExtras;
import com.example.vuclip.liqyd.models.Product;
import com.example.vuclip.liqyd.ui.order_details.OrderDetailsActivity;

/**
 * Created by Banty on 21/01/18.
 */

public class GalleryPresenter {

    public void launchOrderDetailsActivity(Context context, Product product) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(IntentExtras.PRODUCT, product);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
