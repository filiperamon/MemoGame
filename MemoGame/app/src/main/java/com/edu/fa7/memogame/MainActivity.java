package com.edu.fa7.memogame;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private ImageButton imBtn00;
    private ImageButton imBtn01;
    private ImageButton imBtn02;
    private ImageButton imBtn03;
    private ImageButton imBtn10;
    private ImageButton imBtn11;
    private ImageButton imBtn12;
    private ImageButton imBtn13;
    private ImageButton imBtn20;
    private ImageButton imBtn21;
    private ImageButton imBtn22;
    private ImageButton imBtn23;
    private ImageButton imBtn30;
    private ImageButton imBtn31;
    private ImageButton imBtn32;
    private ImageButton imBtn33;

    private int[] imageIds;
    private int[][] matriz;
    private ArrayList<Integer> colors;
    private int countClick1 = -1;
    private int countClick2;
    private View view;
    private int totalAcertos = 0;
    private Chronometer chronometer;
    private TextView tvScores;
    private boolean isInit = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gridLayout = (GridLayout) findViewById(R.id.grid_layout_tabuleiro);
        imBtn00 = (ImageButton) findViewById(R.id.btn_image_0x0);
        imBtn01 = (ImageButton) findViewById(R.id.btn_image_0x1);
        imBtn02 = (ImageButton) findViewById(R.id.btn_image_0x2);
        imBtn03 = (ImageButton) findViewById(R.id.btn_image_0x3);
        imBtn10 = (ImageButton) findViewById(R.id.btn_image_1x0);
        imBtn11 = (ImageButton) findViewById(R.id.btn_image_1x1);
        imBtn12 = (ImageButton) findViewById(R.id.btn_image_1x2);
        imBtn13 = (ImageButton) findViewById(R.id.btn_image_1x3);
        imBtn20 = (ImageButton) findViewById(R.id.btn_image_2x0);
        imBtn21 = (ImageButton) findViewById(R.id.btn_image_2x1);
        imBtn22 = (ImageButton) findViewById(R.id.btn_image_2x2);
        imBtn23 = (ImageButton) findViewById(R.id.btn_image_2x3);
        imBtn30 = (ImageButton) findViewById(R.id.btn_image_3x0);
        imBtn31 = (ImageButton) findViewById(R.id.btn_image_3x1);
        imBtn32 = (ImageButton) findViewById(R.id.btn_image_3x2);
        imBtn33 = (ImageButton) findViewById(R.id.btn_image_3x3);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        tvScores = (TextView) findViewById(R.id.text_view_scores);

        imageIds = new int[]{
                R.id.btn_image_0x0,
                R.id.btn_image_0x1,
                R.id.btn_image_0x2,
                R.id.btn_image_0x3,
                R.id.btn_image_1x0,
                R.id.btn_image_1x1,
                R.id.btn_image_1x2,
                R.id.btn_image_1x3,
                R.id.btn_image_2x0,
                R.id.btn_image_2x1,
                R.id.btn_image_2x2,
                R.id.btn_image_2x3,
                R.id.btn_image_3x0,
                R.id.btn_image_3x1,
                R.id.btn_image_3x2,
                R.id.btn_image_3x3
        };

        colors = new ArrayList<>();
        colors.add(Color.BLACK);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.DKGRAY);
        colors.add(Color.BLACK);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.DKGRAY);

        setDefaultButtonImageValue();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(final View view) {
        if (totalAcertos==0 && isInit){
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isInit = false;
        }
        Animation animator = AnimationUtils.loadAnimation(this, R.anim.to_midle);
        view.setAnimation(animator);
        view.startAnimation(animator);


        switch (view.getId()){
            case R.id.btn_image_0x0:

                animationButton(view, colors.get(0));
                break;
            case R.id.btn_image_0x1:
                animationButton(view, colors.get(1));
                break;
            case R.id.btn_image_0x2:
                animationButton(view, colors.get(2));
                break;
            case R.id.btn_image_0x3:
                animationButton(view, colors.get(3));
                break;
            case R.id.btn_image_1x0:
                animationButton(view, colors.get(4));
                break;
            case R.id.btn_image_1x1:
                animationButton(view, colors.get(5));
                break;
            case R.id.btn_image_1x2:
                animationButton(view, colors.get(6));
                break;
            case R.id.btn_image_1x3:
                animationButton(view, colors.get(7));
                break;
            case R.id.btn_image_2x0:
                animationButton(view, colors.get(8));
                break;
            case R.id.btn_image_2x1:
                animationButton(view, colors.get(9));
                break;
            case R.id.btn_image_2x2:
                animationButton(view, colors.get(10));
                break;
            case R.id.btn_image_2x3:
                animationButton(view, colors.get(11));
                break;
            case R.id.btn_image_3x0:
                animationButton(view, colors.get(12));
                break;
            case R.id.btn_image_3x1:
                animationButton(view, colors.get(13));
                break;
            case R.id.btn_image_3x2:
                animationButton(view, colors.get(14));
                break;
            case R.id.btn_image_3x3:
                animationButton(view, colors.get(15));
                break;
        }

    }

    private void animationButton(final View view, final int color) {
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundColor(color);
                verifierColorButton(color, view);
            }
        }, 300);
    }

    public void verifierColorButton(int color, final View v){
        if (countClick1 == -1) {
            countClick1 = color;
            view = v;
            view.setClickable(false);
        }else {
            countClick2 = color;
            if(countClick1 == countClick2){
                countClick1 = -1;
                countClick2 = -1;
                view.setClickable(false);
                v.setClickable(false);
                totalAcertos +=2;
                if (isEndGame()){
                    Toast.makeText(getApplicationContext(),"venceu", Toast.LENGTH_SHORT).show();
                    setDefaultButtonImageValue();
                    changeStatusViewButton(true);
                    long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    chronometer.setActivated(false);

                    tvScores.setText(String.valueOf(elapsedMillis));
                    isInit = true;
                    showFinishDialog();

                }
            }else {
                changeStatusViewButton(false);
                Handler handler = new Handler(getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundColor(Color.GRAY);
                        v.setBackgroundColor(Color.GRAY);
                        changeStatusViewButton(true);
                        view.setClickable(true);
                    }
                }, 200);
                countClick1 = -1;
                countClick2 = -1;
            }
        }
    }

    public void setDefaultButtonImageValue(){
        Collections.shuffle(colors);

        for (int item = 0; item < colors.size(); item++){
            ImageButton btnDefault = (ImageButton) findViewById(imageIds[item]);
            btnDefault.setBackgroundColor(colors.get(item));
            btnDefault.setClickable(false);
        }

        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int item:imageIds){
                    ImageButton btnDefault = (ImageButton) findViewById(item);
                    btnDefault.setBackgroundColor(Color.GRAY);
                    btnDefault.setClickable(true);
                    totalAcertos = 0;
                }
            }
        },2000);


    }

    public void changeStatusViewButton(Boolean status){
        for (int item:imageIds){
            ImageButton btnDefault = (ImageButton) findViewById(item);
            btnDefault.setEnabled(status);
        }
    }

    public boolean isEndGame(){
     /*   if (totalAcertos == gridLayout.getRowCount()* gridLayout.getColumnCount()){
            return true;
        }else {
            return false;
        }*/
        return true;
    }

    public void showFinishDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reiniciar Jogo?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDefaultButtonImageValue();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}
