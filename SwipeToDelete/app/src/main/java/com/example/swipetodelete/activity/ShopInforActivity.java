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

public class ShopInforActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_infor);

        //

        //ui
        rv = findViewById(R.id.asi_rv);
        findViewById(R.id.asi_bt_back).setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setAdapter(new DrinkRVAdapter(this, shop.getMenu()));
//        Log.d("xxx",shop.getMenu().get(1).getDrinkName());
        assert rv.getAdapter() != null;
        //rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.asi_bt_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
