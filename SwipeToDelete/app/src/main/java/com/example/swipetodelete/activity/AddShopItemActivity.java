package com.example.swipetodelete.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.swipetodelete.R;

public class AddShopItemActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aasi_bt_back:
                finish();
                break;
            case R.id.aasi_bt_ok:
                break;
        }
    }
}
