package com.example.swipetodelete.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.swipetodelete.R;
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;
import com.example.swipetodelete.sqlite.DAOShop;

import java.util.ArrayList;

public class AddShopItemActivity extends AppCompatActivity implements View.OnClickListener {

    //ui
    private EditText name;
    //data
    private DAOShop daoShop;
    private ArrayList<Drink> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_item);

        //ui
        name = findViewById(R.id.aasi_et_name);
        findViewById(R.id.aasi_bt_back).setOnClickListener(this);
        findViewById(R.id.aasi_bt_ok).setOnClickListener(this);

        //data
        daoShop = new DAOShop(getApplicationContext());
        list = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aasi_bt_back:
                finish();
                break;
            case R.id.aasi_bt_ok:
                Shop shop = new Shop(name.getText().toString(), R.drawable.ic_my_icon, list);
                daoShop.insert(shop);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
