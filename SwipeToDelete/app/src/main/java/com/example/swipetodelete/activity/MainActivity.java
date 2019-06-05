package com.example.swipetodelete.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.swipetodelete.R;
import com.example.swipetodelete.adapter.RecyclerViewAdapter;
import com.example.swipetodelete.helper.SwipeToDeleteCallback;
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ui
    private RecyclerView recyclerView;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerViewAdapter mAdapter;

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
                switch (menuItem.getItemId()) {
                    case R.id.am_tb_option:
                        break;
                    case R.id.am_tb_setting:
                        Intent intent = new Intent(MainActivity.this, MusicSettingActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        recyclerView.setAdapter(new RecyclerViewAdapter(this,list));

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
        //sort option
        sortChoices.add("A-Z");
        sortChoices.add("Z-A");

        //data
        ArrayList<Drink> mlist = new ArrayList<>();
        Shop m = new Shop("Milk Shop",R.drawable.milk_shop, mlist);
        list.add(m);

        ArrayList<Drink> clist = new ArrayList<>();
        Shop c = new Shop("Coco",R.drawable.coco, clist);
        list.add(c);

        ArrayList<Drink> dlist = new ArrayList<>();
        Shop d = new Shop("DaYung's",R.drawable.da_yung_s, dlist);
        list.add(d);

        ArrayList<Drink> flist = new ArrayList<>();
        Shop f = new Shop("Fifty Lan",R.drawable.fifty_lan, flist);
        list.add(f);

        ArrayList<Drink> tlist = new ArrayList<>();
        Shop t = new Shop("TigerSugar",R.drawable.tiger_sugar, tlist);
        list.add(t);

        ArrayList<Drink> qlist = new ArrayList<>();
        Shop q = new Shop("Queenny",R.drawable.queenny, qlist);
        list.add(q);

        ArrayList<Drink> jlist = new ArrayList<>();
        Shop j = new Shop("Joly",R.drawable.joly, jlist);
        list.add(j);

        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();
                final int position = viewHolder.getAdapterPosition();
                assert mAdapter != null;
                final Shop item = mAdapter.getData().get(position);
                Log.d("xxx",String.valueOf(item));

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
                                                }else{
                                                    return i;
                                                }
                                            }
                                        });
                                        assert recyclerView.getAdapter() != null;
                                        recyclerView.getAdapter().notifyDataSetChanged();                                        break;
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