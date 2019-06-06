package com.example.swipetodelete.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.swipetodelete.R;
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;
import com.example.swipetodelete.sqlite.DAODrink;
import com.example.swipetodelete.sqlite.DAOShop;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

public class AddDrinkItemActivity extends AppCompatActivity implements View.OnClickListener {

    //ui
    private EditText name;
    private EditText midPrice;
    private EditText bigPrice;

    //data
    private int position;
    private DAODrink daoDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink_item);

        position = getIntent().getIntExtra("position", 0);

        //ui
        name = findViewById(R.id.aadi_et_name);
        midPrice = findViewById(R.id.aadi_et_mid_price);
        bigPrice = findViewById(R.id.aadi_et_big_price);
        findViewById(R.id.aadi_bt_back).setOnClickListener(this);
        findViewById(R.id.aadi_bt_ok).setOnClickListener(this);

        //data
        daoDrink = new DAODrink(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aadi_bt_back:
                finish();
                break;
            case R.id.aadi_bt_ok:
                Drink drink = new Drink(position, name.getText().toString(), midPrice.getText().toString(), bigPrice.getText().toString());
                daoDrink.insert(drink);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
