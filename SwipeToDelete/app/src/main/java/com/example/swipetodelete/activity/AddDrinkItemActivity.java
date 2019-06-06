package com.example.swipetodelete.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.swipetodelete.R;

public class AddDrinkItemActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink_item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aadi_bt_back:
                finish();
                break;
            case R.id.aadi_bt_ok:
                break;
        }
    }
}
