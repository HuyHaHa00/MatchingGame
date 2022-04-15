package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    SQLiteDatabase bxh;
    private String tenNguoiChoi;
    private String flipCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("tenNguoiChoi");
            flipCount = extras.getString("flipCount");
            //The key argument here must match that used in the other activity
        }

        DBHelper dbHelper = new DBHelper(this);
        KetQua kq = new KetQua(tenNguoiChoi,flipCount);
        dbHelper.addOne(kq);

        List<KetQua> listKQ = dbHelper.getAll();

    }


}