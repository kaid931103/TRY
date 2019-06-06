package com.example.swipetodelete.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.swipetodelete.helper.DBHelper;
import com.example.swipetodelete.model.Drink;

import java.util.ArrayList;

public class DAODrink {
    // 表格名稱
    public static final String TABLE_NAME = "Drink";

    // 編號表格欄位名稱，固定不變
    private static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    private static final String SHOP_COLUMN = "num";
    private static final String NAME_COLUMN = "name";
    private static final String MIDPRICE_COLUMN = "midPrice";
    private static final String BIGPRICE_COLUMN = "bigPrice";

    public static String CREATE_TABLE() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Table " + TABLE_NAME + " ( ");
        sb.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , ");
        sb.append(SHOP_COLUMN + " TEXT NOT NULL, ");
        sb.append(NAME_COLUMN + " TEXT NOT NULL, ");
        sb.append(MIDPRICE_COLUMN + " TEXT NOT NULL, ");
        sb.append(BIGPRICE_COLUMN + " TEXT NOT NULL) ");
        return sb.toString();
    }

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

    public boolean insert(Drink item) {

        ContentValues cv = new ContentValues();

        cv.put(SHOP_COLUMN, item.getPosition());
        cv.put(NAME_COLUMN, item.getDrinkName());
        cv.put(MIDPRICE_COLUMN, item.getMidPrice());
        cv.put(BIGPRICE_COLUMN, item.getBigPrice());

        long id =  db.insert(TABLE_NAME, null, cv);
        return id > 0;
    }

    // 讀取所有記事資料
    public ArrayList<Drink> getAll() {
        ArrayList<Drink> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, KEY_ID + " desc", null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    private Drink getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Drink result = new Drink();

        result.setPosition(cursor.getInt(1));
        result.setDrinkName(cursor.getString(2));
        result.setMidPrice(cursor.getString(3));
        result.setBigPrice(cursor.getString(4));

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

    public void initData() {

        //data
        Drink m1 = new Drink(6,"珍珠紅茶拿鐵", "55", "65");
        Drink m2 = new Drink(6,"布丁紅茶拿鐵", "55", "65");
        Drink m3 = new Drink(6,"仙草凍紅茶拿鐵", "55", "65");
        Drink m4 = new Drink(6,"手炒黑糖鮮奶", "60", "80");
        Drink m5 = new Drink(6,"珍珠紅茶鮮豆奶", "55", "65");
        Drink m6 = new Drink(6,"大甲芋頭鮮奶", "65", "85");
        insert(m1);
        insert(m2);
        insert(m3);
        insert(m4);
        insert(m5);
        insert(m6);

        Drink c1 = new Drink(5,"紅茶拿鐵咖啡", " ", "60");
        Drink c2 = new Drink(5,"紅豆珍珠鮮奶茶", " ", "55");
        Drink c3 = new Drink(5,"珍珠鮮奶茶", " ", "55");
        Drink c4 = new Drink(5,"布丁奶茶", " ", "40");
        Drink c5 = new Drink(5,"仙草凍奶茶", " ", "40");
        Drink c6 = new Drink(5,"珍珠奶茶", "", "40");
        Drink c7 = new Drink(5,"冬瓜鮮奶茶", "50", "");
        insert(c1);
        insert(c2);
        insert(c3);
        insert(c4);
        insert(c5);
        insert(c6);
        insert(c7);

        Drink d1 = new Drink(4,"珍珠鮮奶茶", "45", "55");
        Drink d2 = new Drink(4,"仙草凍奶茶", "35", "45");
        Drink d3 = new Drink(4,"珍珠奶茶", "35", "45");
        Drink d4 = new Drink(4,"冬瓜鮮奶茶", "45", "55");
        Drink d5 = new Drink(4,"觀音拿鐵", "50", "60");
        Drink d6 = new Drink(4,"蘋果多輕飲", " ", "70");
        Drink d7 = new Drink(4,"蔓越莓冰醋", " ", "50");
        insert(d1);
        insert(d2);
        insert(d3);
        insert(d4);
        insert(d5);
        insert(d6);
        insert(d7);

        Drink f1 = new Drink(3,"波霸奶茶", "40", "50");
        Drink f2 = new Drink(3,"珍珠奶茶", "40", "50");
        Drink f3 = new Drink(3,"珍珠紅茶拿鐵", "55", "65");
        Drink f4 = new Drink(3,"布丁奶茶", "50", "60");
        Drink f5 = new Drink(3,"四季春+珍波椰", "30", "40");
        Drink f6 = new Drink(3,"黃金烏龍拿鐵", "50", "60");
        Drink f7 = new Drink(3,"波霸烏龍奶茶", "40", "50");
        insert(f1);
        insert(f2);
        insert(f3);
        insert(f4);
        insert(f5);
        insert(f6);
        insert(f7);

        Drink t1 = new Drink(2,"波霸厚鮮奶", " ", "55");
        Drink t2 = new Drink(2,"波霸醇紅茶", " ", "40");
        Drink t3 = new Drink(2,"波霸醇綠茶", " ", "40");
        Drink t4 = new Drink(2,"紅茶拿鐵", " ", "45");
        Drink t5 = new Drink(2,"珍珠鮮奶", " ", "55");
        Drink t6 = new Drink(2,"綠茶拿鐵", " ", "45");
        Drink t7 = new Drink(2,"虎虎生風厚鮮奶", " ", "55");
        insert(t1);
        insert(t2);
        insert(t3);
        insert(t4);
        insert(t5);
        insert(t6);
        insert(t7);

        Drink q1 = new Drink(1,"魚池老紅茶", " ", "40");
        Drink q2 = new Drink(1,"紅烏龍茶", " ", "45");
        Drink q3 = new Drink(1,"紅烏龍奶茶", " ", "55");
        Drink q4 = new Drink(1,"翠玉紅茶", " ", "50");
        Drink q5 = new Drink(1,"薩蘭奶茶", " ", "55");
        Drink q6 = new Drink(1,"龍眼花茶", " ", "40");
        Drink q7 = new Drink(1,"蕎麥黑豆抹茶", " ", "45");
        insert(q1);
        insert(q2);
        insert(q3);
        insert(q4);
        insert(q5);
        insert(q6);
        insert(q7);

        Drink j1 = new Drink(0,"珍珠伯爵歐雷", " ", "65");
        Drink j2 = new Drink(0,"觀音歐雷", " ", "50");
        Drink j3 = new Drink(0,"洛神花茶", " ", "50");
        Drink j4 = new Drink(0,"極品莫希托", " ", "70");
        Drink j5 = new Drink(0,"柚檸萊姆", " ", "70");
        Drink j6 = new Drink(0,"森式可可", " ", "60");
        Drink j7 = new Drink(0,"泰式奶茶", " ", "50");
        insert(j1);
        insert(j2);
        insert(j3);
        insert(j4);
        insert(j5);
        insert(j6);
        insert(j7);
    }
}
