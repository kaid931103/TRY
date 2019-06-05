package com.example.swipetodelete;


import android.Manifest;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissionsAudio();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddDrinkItemActivity.class);
                startActivity(intent);
            }
        });



        recyclerView = findViewById(R.id.recyclerView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        populateRecyclerView();
        enableSwipeToDeleteAndUndo();


    }

    private void getPermissionsAudio() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                !=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent1 = new Intent(MainActivity.this,Setting.class);
                startActivity(intent1);
            return true;
            case R.id.action_sort:
                Intent intent2 = new Intent(MainActivity.this, Sort.class);
                startActivity(intent2);
                return true;
            case R.id.action_add:
                    Intent intent3=new Intent(MainActivity.this,Add.class);
                    startActivity(intent3);
                    return true;

        }
        return super.onOptionsItemSelected(item);
    }
      /*  int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent1 = new Intent(MainActivity.this, Sort.class);
            startActivity(intent1);
            return true;
        }
            if (id == R.id.action_sort) {
                Intent intent2 = new Intent(MainActivity.this, Sort.class);
                startActivity(intent2);
                return true;
            }
          if (id == R.id.action_add) {
            Intent intent3=new Intent(MainActivity.this,Add.class);
            startActivity(intent3);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    private void populateRecyclerView() {
        stringArrayList.add("MilkShop");
        stringArrayList.add("CoCo");
        stringArrayList.add("DaYung's");
        stringArrayList.add("Fifty Lan");
        stringArrayList.add("TigerSugar");
        stringArrayList.add("Queenny");
        stringArrayList.add("Joly");


        mAdapter = new RecyclerViewAdapter(stringArrayList);
        recyclerView.setAdapter(mAdapter);


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


}