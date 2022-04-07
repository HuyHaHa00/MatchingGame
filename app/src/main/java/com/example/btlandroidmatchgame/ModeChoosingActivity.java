package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ModeChoosingActivity extends AppCompatActivity {

    ImageButton btnChude1,btnChude2,btnChude3,btnChude4;
    String tenNguoiChoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_choose);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("tenNguoiChoi");
            //The key argument here must match that used in the other activity
        }

        btnChude1 = findViewById(R.id.btnChude1);
        btnChude1.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("chuDeChoi","hoaqua");
            intent.putExtra("TenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });

        btnChude2 = findViewById(R.id.btnChude2);
        btnChude2.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("chuDeChoi","oto");
            intent.putExtra("TenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });

        btnChude3 = findViewById(R.id.btnChude3);
        btnChude3.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("chuDeChoi","dongvat");
            intent.putExtra("TenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });

        btnChude4 = findViewById(R.id.btnChude4);
        btnChude4.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("chuDeChoi","hoathinh");
            intent.putExtra("TenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });
    }
}