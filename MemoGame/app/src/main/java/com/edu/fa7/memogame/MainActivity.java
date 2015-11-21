package com.edu.fa7.memogame;

import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        setButtonImageValue();

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

    public void onClick(View view) {
        if (totalAcertos==0 && isInit){
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isInit = false;
        }
        switch (view.getId()){
            case R.id.btn_image_0x0:
                view.setBackgroundColor(colors.get(0));
                verifierColorButton(colors.get(0), view);
                break;
            case R.id.btn_image_0x1:
                view.setBackgroundColor(colors.get(1));
                verifierColorButton(colors.get(1), view);
                break;
            case R.id.btn_image_0x2:
                view.setBackgroundColor(colors.get(2));
                verifierColorButton(colors.get(2), view);
                break;
            case R.id.btn_image_0x3:
                view.setBackgroundColor(colors.get(3));
                verifierColorButton(colors.get(3), view);
                break;
            case R.id.btn_image_1x0:
                view.setBackgroundColor(colors.get(4));
                verifierColorButton(colors.get(4), view);
                break;
            case R.id.btn_image_1x1:
                view.setBackgroundColor(colors.get(5));
                verifierColorButton(colors.get(5), view);
                break;
            case R.id.btn_image_1x2:
                view.setBackgroundColor(colors.get(6));
                verifierColorButton(colors.get(6), view);
                break;
            case R.id.btn_image_1x3:
                view.setBackgroundColor(colors.get(7));
                verifierColorButton(colors.get(7), view);
                break;
            case R.id.btn_image_2x0:
                view.setBackgroundColor(colors.get(8));
                verifierColorButton(colors.get(8), view);
                break;
            case R.id.btn_image_2x1:
                view.setBackgroundColor(colors.get(9));
                verifierColorButton(colors.get(9), view);
                break;
            case R.id.btn_image_2x2:
                view.setBackgroundColor(colors.get(10));
                verifierColorButton(colors.get(10), view);
                break;
            case R.id.btn_image_2x3:
                view.setBackgroundColor(colors.get(11));
                verifierColorButton(colors.get(11), view);
                break;
            case R.id.btn_image_3x0:
                view.setBackgroundColor(colors.get(12));
                verifierColorButton(colors.get(12), view);
                break;
            case R.id.btn_image_3x1:
                view.setBackgroundColor(colors.get(13));
                verifierColorButton(colors.get(13), view);
                break;
            case R.id.btn_image_3x2:
                view.setBackgroundColor(colors.get(14));
                verifierColorButton(colors.get(14), view);
                break;
            case R.id.btn_image_3x3:
                view.setBackgroundColor(colors.get(15));
                verifierColorButton(colors.get(15), view);
                break;
        }

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
                if (endGame()){
                    Toast.makeText(getApplicationContext(),"venceu", Toast.LENGTH_SHORT).show();

                    setButtonImageValue();
                    changeStatusViewButton(true);
                    long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    chronometer.setActivated(false);

                    tvScores.setText(String.valueOf(elapsedMillis));
                    isInit = true;

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

    public void setButtonImageValue(){
        Collections.shuffle(colors);
        for (int item:imageIds){
            ImageButton btnDefault = (ImageButton) findViewById(item);
            btnDefault.setBackgroundColor(Color.GRAY);
            btnDefault.setClickable(true);
            totalAcertos = 0;
        }
    }

    public void changeStatusViewButton(Boolean status){
        for (int item:imageIds){
            ImageButton btnDefault = (ImageButton) findViewById(item);
            btnDefault.setEnabled(status);
        }
    }

    public boolean endGame(){
        if (totalAcertos == gridLayout.getRowCount()* gridLayout.getColumnCount()){
            return true;
        }else {
            return false;
        }
    }
}
