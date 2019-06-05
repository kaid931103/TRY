package com.example.swipetodelete.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swipetodelete.R;
import com.example.swipetodelete.activity.ShopInforActivity;
import com.example.swipetodelete.model.Shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends CommonRVAdapter {

    //data
    private ArrayList<Shop> list;

    public RecyclerViewAdapter(Context context, ArrayList<Shop> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getInflater().inflate(R.layout.item_rv_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof ViewHolder) {
            final Shop item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            Picasso.with(getContext())
                    .load(item.getPicture())
                    .into(viewHolder.picture);
            viewHolder.name.setText(item.getName());

            viewHolder.cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (view.getContext(), ShopInforActivity.class);
                    intent.putExtra("drink", position);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<Shop> getData() {
        return list;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Shop item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        CardView cd;
        ImageView picture;
        TextView name;

        ViewHolder(View v) {
            super(v);
            cd = v.findViewById(R.id.card_view);
            picture = v.findViewById(R.id.iv_item_rv_picture);
            name = v.findViewById(R.id.tv_item_rv_name);
        }
    }
}



