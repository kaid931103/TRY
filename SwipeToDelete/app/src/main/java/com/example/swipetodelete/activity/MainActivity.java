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
import com.example.swipetodelete.model.Drink;
import com.example.swipetodelete.model.Shop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ui
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;

    //data
    private ArrayList<String> sortChoices = new ArrayList<>();
    private List<Shop> list = new ArrayList<Shop>();

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
        Drink m1 = new Drink("珍珠紅茶拿鐵", "55", "65");
        Drink m2 = new Drink("布丁紅茶拿鐵", "55", "65");
        Drink m3 = new Drink("仙草凍紅茶拿鐵", "55", "65");
        Drink m4 = new Drink("手炒黑糖鮮奶", "60", "80");
        Drink m5 = new Drink("珍珠紅茶鮮豆奶", "55", "65");
        Drink m6 = new Drink("大甲芋頭鮮奶", "65", "85");
        mlist.add(m1);
        mlist.add(m2);
        mlist.add(m3);
        mlist.add(m4);
        mlist.add(m5);
        mlist.add(m6);
        Shop m = new Shop("Milk Shop", mlist);
        list.add(m);

        ArrayList<Drink> clist = new ArrayList<>();
        Drink c1 = new Drink("紅茶拿鐵咖啡", " ", "60");
        Drink c2 = new Drink("紅豆珍珠鮮奶茶", " ", "55");
        Drink c3 = new Drink("珍珠鮮奶茶", " ", "55");
        Drink c4 = new Drink("布丁奶茶", " ", "40");
        Drink c5 = new Drink("仙草凍奶茶", " ", "40");
        Drink c6 = new Drink("珍珠奶茶", "", "40");
        Drink c7 = new Drink("冬瓜鮮奶茶", "50", "");
        clist.add(c1);
        clist.add(c2);
        clist.add(c3);
        clist.add(c4);
        clist.add(c5);
        clist.add(c6);
        clist.add(c7);
        Shop c = new Shop("Coco", clist);
        list.add(c);

        ArrayList<Drink> dlist = new ArrayList<>();
        Drink d1 = new Drink("珍珠鮮奶茶", "45", "55");
        Drink d2 = new Drink("仙草凍奶茶", "35", "45");
        Drink d3 = new Drink("珍珠奶茶", "35", "45");
        Drink d4 = new Drink("冬瓜鮮奶茶", "45", "55");
        Drink d5 = new Drink("觀音拿鐵", "50", "60");
        Drink d6 = new Drink("蘋果多輕飲", " ", "70");
        Drink d7 = new Drink("蔓越莓冰醋", " ", "50");
        dlist.add(d1);
        dlist.add(d2);
        dlist.add(d3);
        dlist.add(d4);
        dlist.add(d5);
        dlist.add(d6);
        dlist.add(d7);
        Shop d = new Shop("DaYung's", dlist);
        list.add(d);

        ArrayList<Drink> flist = new ArrayList<>();
        Drink f1 = new Drink("波霸奶茶", "40", "50");
        Drink f2 = new Drink("珍珠奶茶", "40", "50");
        Drink f3 = new Drink("珍珠紅茶拿鐵", "55", "65");
        Drink f4 = new Drink("布丁奶茶", "50", "60");
        Drink f5 = new Drink("四季春+珍波椰", "30", "40");
        Drink f6 = new Drink("黃金烏龍拿鐵", "50", "60");
        Drink f7 = new Drink("波霸烏龍奶茶", "40", "50");
        flist.add(f1);
        flist.add(f2);
        flist.add(f3);
        flist.add(f4);
        flist.add(f5);
        flist.add(f6);
        flist.add(f7);
        Shop f = new Shop("Fifty Lan", flist);
        list.add(f);

        ArrayList<Drink> tlist = new ArrayList<>();
        Drink t1 = new Drink("波霸厚鮮奶", " ", "55");
        Drink t2 = new Drink("波霸醇紅茶", " ", "40");
        Drink t3 = new Drink("波霸醇綠茶", " ", "40");
        Drink t4 = new Drink("紅茶拿鐵", " ", "45");
        Drink t5 = new Drink("珍珠鮮奶", " ", "55");
        Drink t6 = new Drink("綠茶拿鐵", " ", "45");
        Drink t7 = new Drink("虎虎生風厚鮮奶", " ", "55");
        tlist.add(t1);
        tlist.add(t2);
        tlist.add(t3);
        tlist.add(t4);
        tlist.add(t5);
        tlist.add(t6);
        tlist.add(t7);
        Shop t = new Shop("TigerSugar", tlist);
        list.add(t);

        ArrayList<Drink> qlist = new ArrayList<>();
        Drink q1 = new Drink("魚池老紅茶", " ", "40");
        Drink q2 = new Drink("紅烏龍茶", " ", "45");
        Drink q3 = new Drink("紅烏龍奶茶", " ", "55");
        Drink q4 = new Drink("翠玉紅茶", " ", "50");
        Drink q5 = new Drink("薩蘭奶茶", " ", "55");
        Drink q6 = new Drink("龍眼花茶", " ", "40");
        Drink q7 = new Drink("蕎麥黑豆抹茶", " ", "45");
        qlist.add(q1);
        qlist.add(q2);
        qlist.add(q3);
        qlist.add(q4);
        qlist.add(q5);
        qlist.add(q6);
        qlist.add(q7);
        Shop q = new Shop("Queenny", qlist);
        list.add(q);

        ArrayList<Drink> jlist = new ArrayList<>();
        Drink j1 = new Drink("珍珠伯爵歐雷", " ", "65");
        Drink j2 = new Drink("觀音歐雷", " ", "50");
        Drink j3 = new Drink("洛神花茶", " ", "50");
        Drink j4 = new Drink("極品莫希托", " ", "70");
        Drink j5 = new Drink("柚檸萊姆", " ", "70");
        Drink j6 = new Drink("森式可可", " ", "60");
        Drink j7 = new Drink("泰式奶茶", " ", "50");
        jlist.add(j1);
        jlist.add(j2);
        jlist.add(j3);
        jlist.add(j4);
        jlist.add(j5);
        jlist.add(j6);
        jlist.add(j7);
        Shop j = new Shop("Joly", jlist);
        list.add(j);

        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
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
                                        break;
                                    case "Z-A":
                                        Collections.reverse(list);
                                        break;
                                }
                            }
                        })
                        .show();
                break;
        }
    }
}