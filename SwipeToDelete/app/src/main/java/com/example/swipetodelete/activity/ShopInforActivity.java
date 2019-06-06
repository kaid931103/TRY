package com.example.swipetodelete.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.swipetodelete.R;
import com.example.swipetodelete.adapter.DrinkRVAdapter;
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;
import com.example.swipetodelete.sqlite.DAODrink;
import com.example.swipetodelete.sqlite.DAOShop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PropertyResourceBundle;

public class ShopInforActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Drink> list;
    private DAODrink daoDrink;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_infor);

        //data
        list = new ArrayList<>();
        position = getIntent().getIntExtra("drink", 0);
        // 建立資料庫物件
        daoDrink = new DAODrink(getApplicationContext());
        if (daoDrink.getCount() == 0) {
            daoDrink.initData();
            Log.d("xxx", "y");
        }

        for (Drink item : daoDrink.getAll()) {
            if(item.getPosition()==position){
                list.add(item);
            }
        }
        //ui
        RecyclerView rv = findViewById(R.id.asi_rv);
        findViewById(R.id.asi_bt_back).setOnClickListener(this);
        findViewById(R.id.asi_bt_add).setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new DrinkRVAdapter(this, list));
        assert rv.getAdapter() != null;
        rv.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asi_bt_back:
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
                break;
            case R.id.asi_bt_add:
                Intent add = new Intent(this, AddDrinkItemActivity.class);
                add.putExtra("position",position);
                startActivity(add);
        }
    }
}
