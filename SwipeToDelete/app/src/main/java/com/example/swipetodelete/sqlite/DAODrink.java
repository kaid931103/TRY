package com.example.swipetodelete.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.swipetodelete.helper.DBHelper;
import com.example.swipetodelete.model.Shop;

import java.util.ArrayList;

public class DAODrink {
    // 表格名稱
    public static final String TABLE_NAME = "Drink";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String SHOP_COLUMN = "shop";
    private static final String NAME_COLUMN = "name";
    private static final String MIDPRICE_COLUMN = "midPrice";
    private static final String BIGPRICE_COLUMN = "bigPrice";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SHOP_COLUMN + "TEXT NOT NULL, " +
                    NAME_COLUMN + " TEXT NOT NULL, " +
                    MIDPRICE_COLUMN + "TEXT NOT NULL," +
                    BIGPRICE_COLUMN + "TEXT NOT NULL)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DAODrink(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public boolean insert(Shop item, int position) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(SHOP_COLUMN, item.getName());
        cv.put(NAME_COLUMN, item.getMenu().get(position).getDrinkName());
        cv.put(MIDPRICE_COLUMN, item.getMenu().get(position).getMidPrice());
        cv.put(BIGPRICE_COLUMN, item.getMenu().get(position).getBigPrice());

        return db.insert(TABLE_NAME, null, cv) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where, null) > 0;
    }

    // 讀取所有記事資料
    public ArrayList<Shop> getAll() {
        ArrayList<Shop> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Shop get(long id) {
        // 準備回傳結果用的物件
        Shop item = new Shop();
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }
        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 取得最新的本地端id
    public Shop getLast() {
        // 準備回傳結果用的物件
        Shop item = new Shop();
        // 使用編號為查詢條件
        // 執行查詢
        Cursor result = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", "1");

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }
        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    private Shop getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Shop result = new Shop();

        result.setName(cursor.getString(1));
        result.setPicture(cursor.getInt(2));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }
}
