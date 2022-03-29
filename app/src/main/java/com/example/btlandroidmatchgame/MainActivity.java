package com.example.btlandroidmatchgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton[][] imageButtons = new ImageButton[4][4];//ban choi
    public ArrayList<card> Cardlist = new ArrayList<>(); //mang cac card

    //mang anh
    private int[] listAnh = new int[]
            {R.drawable.a00,R.drawable.a01,R.drawable.a02,
                    R.drawable.a03,R.drawable.a04,R.drawable.a05,
                    R.drawable.a06,R.drawable.a07,R.drawable.a08,R.drawable.a09,};

    int ButtonImgRes[] = new int[3];
    int ButtonID[]= new int[3];
    int CardIndex[] = new int[3];

    private int flipTurn = 0; // lượt chơi

    private int flip = 0;
    private int match = 0;

    private TextView textviewflip;
    private TextView textviewmatch;
    private TextView textviewtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen_4x4);

        textviewflip = findViewById(R.id.text_view_flip);
        textviewmatch = findViewById(R.id.text_view_match);
        textviewtime = findViewById(R.id.text_view_time);

        gameInit();
    }

    private void gameInit() {
        int[] listso = new int[]{0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};// mang nay la vi tri cua listanh
        shuffleArray(listso);//xao tron random cac vi tri
        int vitri = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Cardlist.add(new card(listso[vitri],true) );
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
                        ((ImageButton) view).setImageResource(listAnh[Cardlist.get(CardIndex[flipTurn]).getCard_img_res()]);
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
    }

    private void checkMatch() {
        if(ButtonImgRes[0]==ButtonImgRes[1]) {
            match++;
            textviewmatch.setText("Match: " + match);
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