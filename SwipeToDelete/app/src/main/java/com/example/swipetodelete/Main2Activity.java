package com.example.swipetodelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv1=(TextView) findViewById(R.id.text_view_1);
        TextView tv2=(TextView) findViewById(R.id.text_view_2);

        tv1.setText("來回答我問題");
        tv2.setText("你最歡哪一家飲料店?");

        tv1.setTextSize(40.0f);
        tv2.setTextSize(20.0f);
    }
}
