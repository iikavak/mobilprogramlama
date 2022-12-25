package com.example.catchthekenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView[] imageArry;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize
        timeText=(TextView) findViewById(R.id.timeText);
        scoreText=(TextView) findViewById(R.id.scoreText);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);

        imageArry=new ImageView[]{imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10};

        hideImages();
        score=0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: "+ l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("time off");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArry){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("restart");
                alert.setMessage("are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //restart
                        Intent intent=getIntent();
                        finish();//güncel aktivitiye bitiricek
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"game over", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();

    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score:" + score);


    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArry){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArry[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}