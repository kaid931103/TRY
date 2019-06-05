package com.example.swipetodelete.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.swipetodelete.R;

public class AddDrinkItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink_item);

        TextView tv1 = findViewById(R.id.text_view_1);
        TextView tv2 = findViewById(R.id.text_view_2);

        tv1.setText("來回答我問題");
        tv2.setText("你最歡哪一家飲料店?");

        tv1.setTextSize(40.0f);
        tv2.setTextSize(20.0f);
    }
}
