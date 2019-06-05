package com.example.swipetodelete.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swipetodelete.R;
import com.example.swipetodelete.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrinkRVAdapter extends CommonRVAdapter{

    //data
    private ArrayList<Drink> list;

    public DrinkRVAdapter(Context context, ArrayList<Drink> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.item_rv_drink, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Drink item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.name.setText(item.getDrinkName());
            viewHolder.midPrice.setText("M:" + item.getMidPrice());
            viewHolder.bigPrice.setText("L:" + item.getBigPrice());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView midPrice;
        TextView bigPrice;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_item_rv_drink_name);
            midPrice = v.findViewById(R.id.tv_item_rv_mid_price);
            bigPrice = v.findViewById(R.id.tv_item_rv_big_price);
        }
    }
}
