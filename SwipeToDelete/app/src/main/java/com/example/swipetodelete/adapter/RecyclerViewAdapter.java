package com.example.swipetodelete.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.swipetodelete.R;
import com.example.swipetodelete.model.Shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends CommonRVAdapter {

    //data
    private List<Shop> list;

    public RecyclerViewAdapter(Context context, List<Shop> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getInflater().inflate(R.layout.item_rv_drink, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Shop item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            Picasso.with(getContext())
                    .load(item.getPicture())
                    .into(viewHolder.picture);
            viewHolder.name.setText(item.getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Shop> getData() {
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

        ImageView picture;
        TextView name;

        ViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.iv_item_rv_picture);
            name = v.findViewById(R.id.tv_item_rv_name);
        }
    }
}



