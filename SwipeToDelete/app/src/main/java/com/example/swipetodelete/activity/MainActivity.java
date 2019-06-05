package com.example.swipetodelete.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.swipetodelete.R;
import com.example.swipetodelete.adapter.RecyclerViewAdapter;
import com.example.swipetodelete.helper.SwipeToDeleteCallback;
import com.example.swipetodelete.model.Shop;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //ui
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;

    //data
    private ArrayList<String> sortChoices = new ArrayList<>();
    private ArrayList<Shop> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ui
        findViewById(R.id.am_tb_sort).setOnClickListener(this);
        recyclerView = findViewById(R.id.am_rv);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        FloatingActionButton fab = findViewById(R.id.am_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDrinkItemActivity.class);
                startActivity(intent);
            }
        });
        Toolbar tb = findViewById(R.id.am_tb);
        tb.inflateMenu(R.menu.am_tb_menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.am_tb_option:
                        break;
                }
                return false;
            }
        });

        //function
        getPermissionsAudio();
        initData();
        enableSwipeToDeleteAndUndo();
    }

    private void getPermissionsAudio() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    private void initData() {
        sortChoices.add("A-Z");
        sortChoices.add("Z-A");

//        list.add("MilkShop");
//        stringArrayList.add("CoCo");
//        stringArrayList.add("DaYung's");
//        stringArrayList.add("Fifty Lan");
//        stringArrayList.add("TigerSugar");
//        stringArrayList.add("Queenny");
//        stringArrayList.add("Joly");
//
//        mAdapter = new RecyclerViewAdapter(stringArrayList);
//        recyclerView.setAdapter(mAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.am_tb_sort:
                int size = sortChoices.size();
                new AlertDialog.Builder(MainActivity.this)
                        .setItems(sortChoices.toArray(new String[size]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = sortChoices.get(which);

                                //update list sort
                            }
                        })
                        .show();
                break;
        }
    }
}