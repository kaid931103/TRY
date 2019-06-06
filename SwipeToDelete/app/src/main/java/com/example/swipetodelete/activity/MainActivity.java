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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.swipetodelete.R;
import com.example.swipetodelete.adapter.RecyclerViewAdapter;
import com.example.swipetodelete.helper.SwipeToDeleteCallback;
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;
import com.example.swipetodelete.sqlite.DAOShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ui
    private RecyclerView recyclerView;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerViewAdapter mAdapter;
    private DAOShop shopData;

    //data
    private ArrayList<String> sortChoices = new ArrayList<>();
    private ArrayList<Shop> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data
        initMenu();
        // 建立資料庫物件
        shopData = new DAOShop(getApplicationContext());
        if (shopData.getCount() == 0) {
            shopData.initData();
        }
        list = shopData.getAll();

        //ui
        findViewById(R.id.am_tb_sort).setOnClickListener(this);
        recyclerView = findViewById(R.id.am_rv);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        FloatingActionButton fab = findViewById(R.id.am_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });
        Toolbar tb = findViewById(R.id.am_tb);
        tb.inflateMenu(R.menu.am_tb_menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.am_tb_add_new_shop:
                        Intent add_new_shop = new Intent(MainActivity.this, AddShopItemActivity.class);
                        startActivity(add_new_shop);
                        break;
                    case R.id.am_tb_setting:
                        Intent setting = new Intent(MainActivity.this, MusicSettingActivity.class);
                        startActivity(setting);
                        break;
                }
                return false;
            }
        });
        recyclerView.setAdapter(new RecyclerViewAdapter(this, list));
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

        //function
        getPermissionsAudio();
        enableSwipeToDeleteAndUndo();
    }

    private void getPermissionsAudio() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    private void initMenu() {
        //sort option
        sortChoices.add("A-Z");
        sortChoices.add("Z-A");
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();
                final int position = viewHolder.getAdapterPosition();
                assert mAdapter != null;
                final Shop item = mAdapter.getData().get(position);
                Log.d("xxx", String.valueOf(item));

                mAdapter.removeItem(position);
                shopData.delete(position);


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
        switch (v.getId()) {
            case R.id.am_tb_sort:
                int size = sortChoices.size();
                new AlertDialog.Builder(MainActivity.this)
                        .setItems(sortChoices.toArray(new String[size]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                switch (sortChoices.get(id)) {
                                    case "A-Z":
                                        Collections.sort(list, new Comparator<Shop>() {
                                            @Override
                                            public int compare(Shop s1, Shop s2) {
                                                int i = s1.name.compareTo(s2.name);
                                                if (i == 0) {
                                                    return 0;
                                                } else {
                                                    return i;
                                                }
                                            }
                                        });
                                        assert recyclerView.getAdapter() != null;
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        break;
                                    case "Z-A":
                                        Collections.reverse(list);
                                        assert recyclerView.getAdapter() != null;
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        break;
                                }
                            }
                        })
                        .show();
                break;
        }
    }
}