package com.example.btlandroidmatchgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "bxh.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE BXH (ID INTEGER PRIMARY KEY AUTOINCREMENT, PLAYERNAME TEXT, FLIPCOUNT STRING)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(KetQua kq) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PLAYERNAME", kq.getName());
        cv.put("FLIPCOUNT", kq.getFlipCount());
        long insert = db.insert("BXH", null, cv);
        if (insert == -1) return false;
        else return true;
    }

    public List<KetQua> getAll() {
        List<KetQua> returnList = new ArrayList<>();
        //get data
        String queryString = "SELECT * FROM BXH";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            //loop qua ket qua
            do {
                String name = cursor.getString(1);
                String flipCount = cursor.getString(2);

                KetQua kq = new KetQua(name,flipCount);
                returnList.add(kq);
            } while (cursor.moveToNext());
        }
        else {
            //ko co database
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
