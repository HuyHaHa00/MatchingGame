package com.example.btlandroidmatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {
    String flipCount = new String();
    String tenNguoiChoi = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("tenNguoiChoi");
            flipCount = extras.getString("flipCount");
            //The key argument here must match that used in the other activity
        }

        TextView textviewflip = findViewById((R.id.txtFlip));
        textviewflip.setText(flipCount);
        TextView textviewName = findViewById(R.id.txtTen);
        textviewName.setText(tenNguoiChoi);

        Button btnChoiLai = findViewById(R.id.btnChoiLai);
        btnChoiLai.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ModeChoosingActivity.class);
            intent.putExtra("tenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });

        Button btnBXH = findViewById(R.id.btnBXH);
        btnBXH.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), LeaderboardActivity.class);//fix
            intent.putExtra("tenNguoiChoi",tenNguoiChoi);
            intent.putExtra("flipCount",flipCount);
            startActivity(intent);
        });

        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }
}