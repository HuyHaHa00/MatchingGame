package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class OpenActivity extends AppCompatActivity {

    Button btnTiepTuc;
    String tenNguoiChoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_screen);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);

        EditText editText2 = (EditText) findViewById(R.id.edtNhapTen);

        btnTiepTuc.setOnClickListener(view -> {
            tenNguoiChoi = editText2.getText().toString();
            Intent intent = new Intent(getBaseContext(), ModeChoosingActivity.class);
            intent.putExtra("name",tenNguoiChoi);
            startActivity(intent);
        });
    }
}