package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OpenActivity extends AppCompatActivity {

    Button btnTiepTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_screen);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);

        EditText editText2 = (EditText) findViewById(R.id.txtTenNguoiChoi);
        String tenNguoiChoi = editText2.getText().toString();

        btnTiepTuc.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ModeChoosingActivity.class);
            intent.putExtra("tenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });
    }
}