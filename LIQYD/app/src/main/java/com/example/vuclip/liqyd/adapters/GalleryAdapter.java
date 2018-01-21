package com.example.vuclip.liqyd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.models.Product;
import com.example.vuclip.liqyd.repository.DataProvider;

/**
 * Created by Banty on 15/01/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private static final String TAG = "GalleryAdapter";
    private final DataProvider mDataProvider;
    private final Context mContext;
    private GalleryPresenter galleryPresenter;

    public GalleryAdapter(DataProvider dataProvider, Context context) {
        mDataProvider = dataProvider;
        mContext = context;
        galleryPresenter = new GalleryPresenter();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_gallary_single_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mDataProvider.getProductList().get(position);
        putValuesInViewHolder(holder, product);
        registerBuyButtonClickListener(holder.buyButton, product);
    }

    private void registerBuyButtonClickListener(View buyButton, final Product product) {
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryPresenter.launchOrderDetailsActivity(mContext, product);
            }
        });
    }

    private void putValuesInViewHolder(ViewHolder holder, Product product) {
        holder.productImage.setImageResource(product.getDrawableImage());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return mDataProvider.getProductCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button buyButton;

        public ViewHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            buyButton = itemView.findViewById(R.id.buy_button);
        }
    }
}
