package com.abhipeiris.coffee_shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abhipeiris.coffee_shop.R;
import com.abhipeiris.coffee_shop.model.ProductModel;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> productModels;
    private Context context;

    private Listener listener;

    public interface  Listener{
        void onClick(int position);
    }

    public ProductAdapter(Context context,List<ProductModel> productModels) {
        this.productModels = productModels;
        this.context = context;
    }
    public void setListener(Listener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        CardView cardView = holder.cardView;
        ImageView image_product = cardView.findViewById(R.id.image_product);
        TextView product_name = cardView.findViewById(R.id.label_product_name);
        TextView product_price = cardView.findViewById(R.id.label_price);

//        Picasso.get().load("http://192.168.1.3:8080/images/" + productModels.get(position).getImage()).into(image_product);
//        Picasso.get().load("http://192.168.1.3:8080/images/1.png").into(image_product);

        Glide.with(context)
                .load(Uri.parse("http://192.168.1.3:8080/images/" + productModels.get(position).getImage()))
                .into(image_product);

        System.out.println("http://192.168.1.3:8080/images/" + productModels.get(position).getImage());

        product_name.setText(productModels.get(position).getProduct_name());
        product_price.setText(String.valueOf(productModels.get(position).getPrice()));


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.onClick(position);
                }

//                Toast.makeText(context, productModels.get(position).getProduct_name(), Toast.LENGTH_SHORT);
//                System.out.println( productModels.get(position).getProduct_id());
            }
        });

    }

    public ProductModel getSelected(int position){
        if(productModels != null){
            return productModels.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
