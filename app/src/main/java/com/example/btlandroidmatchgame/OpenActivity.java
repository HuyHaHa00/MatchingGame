package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OpenActivity extends AppCompatActivity {

    TextView txtName;
    Button btnTiepTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_screen);

        txtName = findViewById(R.id.txtTenNguoiChoi);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ModeChoosingActivity.class);
            intent.putExtra("tenNguoiChoi",txtName.getText());
            startActivity(intent);
        });
    }
}