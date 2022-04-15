package com.example.btlandroidmatchgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "";
    private ImageButton[][] imageButtons = new ImageButton[4][4];//ban choi
    public ArrayList<card> Cardlist = new ArrayList<>(); //mang cac card

    //mang anh
    private int[] listAnhHoaQua = new int[]
            {R.drawable.a00,R.drawable.a01,R.drawable.a02,
                    R.drawable.a03,R.drawable.a04,R.drawable.a05,
                    R.drawable.a06,R.drawable.a07,R.drawable.a08,R.drawable.a09};
    private int[] listAnhXe = new int[]
            {R.drawable.a10,R.drawable.a11,R.drawable.a12,
                    R.drawable.a13,R.drawable.a14,R.drawable.a15,
                    R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19};
    private int[] listAnhDongVat = new int[]
            {R.drawable.a20,R.drawable.a21,R.drawable.a22,
                    R.drawable.a23,R.drawable.a24,R.drawable.a25,
                    R.drawable.a26,R.drawable.a27,R.drawable.a28,R.drawable.a29};
    private int[] listAnhHoatHinh = new int[]
            {R.drawable.a30,R.drawable.a31,R.drawable.a32,
                    R.drawable.a33,R.drawable.a34,R.drawable.a35,
                    R.drawable.a36,R.drawable.a37,R.drawable.a38,R.drawable.a39};

    int ButtonImgRes[] = new int[3];
    int ButtonID[]= new int[3];
    int CardIndex[] = new int[3];

    private int flipTurn = 0; // lượt chơi

    private int flip = 0;
    private int match = 0;

    private TextView textviewflip;
    private TextView textviewmatch;
    private TextView textviewname;

    String chuDe = new String();
    String tenNguoiChoi = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen_4x4);

        textviewflip = findViewById(R.id.text_view_flip);
        textviewmatch = findViewById(R.id.text_view_match);
        textviewname = findViewById(R.id.text_view_name);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("TenNguoiChoi");
            chuDe = extras.getString("chuDeChoi");
            textviewname.setText("Player: "+ tenNguoiChoi);
            //The key argument here must match that used in the other activity
        }
        gameInit();
    }

    private void gameInit() {
        int[] listAnh = layListAnh();
        int[] listso = new int[]{0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};// mang nay la vi tri cua listanh
        shuffleArray(listso);//xao tron random cac vi tri
        int vitri = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Cardlist.add(new card(listso[vitri],true) );//them 1 card moi, them thong tin ve img res va flipable
                String ImagebuttonID = "ImageButton_" + i + j;
                int resID = getResources().getIdentifier(ImagebuttonID, "id", getPackageName());
                imageButtons[i][j] = findViewById(resID);
                imageButtons[i][j].setTag(vitri);

                imageButtons[i][j].setOnClickListener(view -> {
                    CardIndex[flipTurn] = (int) view.getTag();//lay duoc tag cua button (lay duoc index cua card trong cardlist)
                    if(Cardlist.get(CardIndex[flipTurn]).isFlipable()){
                        ButtonImgRes[flipTurn] = Cardlist.get(CardIndex[flipTurn]).card_img_res;
                        //gan img res tu card vao mang ButtonImgRes, phuc vu muc dich so sanh
                        ButtonID[flipTurn] = view.getId();
                        //lay id cua button roi cho vao mang id,
                        //phuc vu muc dich neu anh ko trung thi co the dung id de chuyen ve anh cu
                        ((ImageButton) view).setImageResource(listAnh[(Cardlist.get(CardIndex[flipTurn]).getCard_img_res())]);
                        Cardlist.get(CardIndex[flipTurn]).setFlipable(false); // ko cho lat lai nua
                        flipTurn++;
                        if(flipTurn == 2){
                            checkMatch();
                        }
                    }
                    else{
                        Toast.makeText(this, "Card nay da duoc lat roi", Toast.LENGTH_SHORT).show();
                    }
                });
                vitri++;
            }
        }
        Button btnEnd = findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), EndGame.class);
            intent.putExtra("flipCount",flip);
            intent.putExtra("tenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        });
    }

    private int[] layListAnh() {
        if(Objects.equals(chuDe, "oto")) {
            return listAnhXe;
        }
        if(Objects.equals(chuDe,"dongvat")) {
            return listAnhDongVat;
        }
        if(Objects.equals(chuDe, "hoathinh")) {
            return listAnhHoatHinh;
        }
        if(Objects.equals(chuDe,"hoaqua")) {
            return listAnhHoaQua;
        }
        else return listAnhXe;
    }

    private void checkMatch() {
        if(ButtonImgRes[0]==ButtonImgRes[1]) {
            match++;
            flip++;
            textviewmatch.setText("Match: " + match);
            textviewflip.setText("Flip: "+ flip );
            flipTurn = 0;
        }
        else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable (){
                @Override
                public void run(){
                    ImageButton button1 = findViewById(ButtonID[0]);
                    button1.setImageResource(R.drawable.dauhoi);
                    ImageButton button2 = findViewById(ButtonID[1]);
                    button2.setImageResource(R.drawable.dauhoi);
                    flipTurn = 0;
                    flip++;
                    textviewflip.setText("Flip: "+ flip );
                    Cardlist.get(CardIndex[0]).setFlipable(true);//cho lat lai
                    Cardlist.get(CardIndex[1]).setFlipable(true);
                }
            }, 1000);
        }
        if (match == 8) {
            Intent intent = new Intent(getBaseContext(), EndGame.class);
            intent.putExtra("flipCount",flip);
            intent.putExtra("tenNguoiChoi",tenNguoiChoi);
            startActivity(intent);
        }
    }



    static void shuffleArray(int[] ar)// ham de random cac gia tri trong mang
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public void onClick(View view) {

    }
}