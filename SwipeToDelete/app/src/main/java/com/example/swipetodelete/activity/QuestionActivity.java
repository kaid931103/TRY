package com.example.swipetodelete.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipetodelete.R;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioButton mRb01;
    private RadioButton mRb02;
    private RadioButton mRb03;
    private RadioButton mRb04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();

        findViewById(R.id.aq_bt_back).setOnClickListener(this);
    }

    private void initView() {
        mRb01 =  findViewById(R.id.rb01);
        mRb02 =  findViewById(R.id.rb02);
        mRb03 =  findViewById(R.id.rb03);
        mRb04 =  findViewById(R.id.rb04);
        TextView mOkBt = findViewById(R.id.ok_bt);

        mOkBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aq_bt_back:
                finish();
                break;
            case R.id.ok_bt:
                if (mRb01.isChecked()) {
                    Toast.makeText(QuestionActivity.this, "小笨蛋~答錯惹喔", Toast.LENGTH_SHORT).show();
                }
                if (mRb02.isChecked()) {
                    Toast.makeText(QuestionActivity.this, "親愛的~答錯惹喔", Toast.LENGTH_SHORT).show();
                }
                if (mRb03.isChecked()) {
                    Toast.makeText(QuestionActivity.this, "小聰明~答錯惹喔", Toast.LENGTH_SHORT).show();
                }
                if (mRb04.isChecked()) {
                    Toast.makeText(QuestionActivity.this, "你好棒! 答對了喔!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
