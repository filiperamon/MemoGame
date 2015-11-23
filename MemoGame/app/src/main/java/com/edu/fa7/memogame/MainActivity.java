package com.edu.fa7.memogame;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
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

    private Chronometer chronometer;
    private TextView tvScores;
    private GridLayout gridLayout;

    private ImageButton imBtn00 ,imBtn01, imBtn02, imBtn03, imBtn10, imBtn11, imBtn12, imBtn13;
    private ImageButton imBtn20 ,imBtn21, imBtn22, imBtn23, imBtn30, imBtn31, imBtn32, imBtn33;
    private ImageButton imBtn40 ,imBtn41, imBtn42, imBtn43, imBtn50, imBtn51, imBtn52, imBtn53;

    private ArrayList<Integer> imagens;

    private int[] imageIds;
    private int countClick1 = -1;
    private int countClick2;
    private View view;
    private int totalAcertos = 0;
    private boolean isInit = true;
    private int tamanhoTabuleiro = 0; //16,20, 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        tvScores = (TextView) findViewById(R.id.text_view_scores);

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
        imBtn40 = (ImageButton) findViewById(R.id.btn_image_4x0);
        imBtn41 = (ImageButton) findViewById(R.id.btn_image_4x1);
        imBtn42 = (ImageButton) findViewById(R.id.btn_image_4x2);
        imBtn43 = (ImageButton) findViewById(R.id.btn_image_4x3);
        imBtn50 = (ImageButton) findViewById(R.id.btn_image_5x0);
        imBtn51 = (ImageButton) findViewById(R.id.btn_image_5x1);
        imBtn52 = (ImageButton) findViewById(R.id.btn_image_5x2);
        imBtn53 = (ImageButton) findViewById(R.id.btn_image_5x3);

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
                R.id.btn_image_3x3,
                R.id.btn_image_4x0,
                R.id.btn_image_4x1,
                R.id.btn_image_4x2,
                R.id.btn_image_4x3,
                R.id.btn_image_5x0,
                R.id.btn_image_5x1,
                R.id.btn_image_5x2,
                R.id.btn_image_5x3
        };

        displaySharedPreferences();

        imagens = setTimes();

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
                animationButton(view, imagens.get(0));
                break;
            case R.id.btn_image_0x1:
                animationButton(view, imagens.get(1));
                break;
            case R.id.btn_image_0x2:
                animationButton(view, imagens.get(2));
                break;
            case R.id.btn_image_0x3:
                animationButton(view, imagens.get(3));
                break;
            case R.id.btn_image_1x0:
                animationButton(view, imagens.get(4));
                break;
            case R.id.btn_image_1x1:
                animationButton(view, imagens.get(5));
                break;
            case R.id.btn_image_1x2:
                animationButton(view, imagens.get(6));
                break;
            case R.id.btn_image_1x3:
                animationButton(view, imagens.get(7));
                break;
            case R.id.btn_image_2x0:
                animationButton(view, imagens.get(8));
                break;
            case R.id.btn_image_2x1:
                animationButton(view, imagens.get(9));
                break;
            case R.id.btn_image_2x2:
                animationButton(view, imagens.get(10));
                break;
            case R.id.btn_image_2x3:
                animationButton(view, imagens.get(11));
                break;
            case R.id.btn_image_3x0:
                animationButton(view, imagens.get(12));
                break;
            case R.id.btn_image_3x1:
                animationButton(view, imagens.get(13));
                break;
            case R.id.btn_image_3x2:
                animationButton(view, imagens.get(14));
                break;
            case R.id.btn_image_3x3:
                animationButton(view, imagens.get(15));
                break;

            case R.id.btn_image_4x0:
                animationButton(view, imagens.get(16));
                break;
            case R.id.btn_image_4x1:
                animationButton(view, imagens.get(17));
                break;
            case R.id.btn_image_4x2:
                animationButton(view, imagens.get(18));
                break;
            case R.id.btn_image_4x3:
                animationButton(view, imagens.get(19));
                break;

            case R.id.btn_image_5x0:
                animationButton(view, imagens.get(20));
                break;
            case R.id.btn_image_5x1:
                animationButton(view, imagens.get(21));
                break;
            case R.id.btn_image_5x2:
                animationButton(view, imagens.get(22));
                break;
            case R.id.btn_image_5x3:
                animationButton(view, imagens.get(23));
                break;
        }

    }

    private void animationButton(final View view, final int color) {
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(color);
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
        Collections.shuffle(imagens);

        int i = 0;

        for (int item = 0; item < tamanhoTabuleiro; item++) {
            ImageButton btnDefault = (ImageButton) findViewById(imageIds[item]);
            btnDefault.setBackgroundResource(imagens.get(item));
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
        if (totalAcertos == tamanhoTabuleiro){

            return true;
        }else {
            return false;
        }
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

    public void displaySharedPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String tabuleiro = prefs.getString("listboard","Default Board");
        tamanhoTabuleiro = Integer.valueOf(tabuleiro);
    }

    public ArrayList <Integer> setImags() {
        ArrayList<Integer> imags = new ArrayList<>();

        imags.add(Color.BLACK);
        imags.add(Color.GREEN);
        imags.add(Color.BLUE);
        imags.add(Color.CYAN);
        imags.add(Color.MAGENTA);
        imags.add(Color.RED);
        imags.add(Color.YELLOW);
        imags.add(Color.DKGRAY);
        imags.add(Color.BLACK);
        imags.add(Color.GREEN);
        imags.add(Color.BLUE);
        imags.add(Color.CYAN);
        imags.add(Color.MAGENTA);
        imags.add(Color.RED);
        imags.add(Color.YELLOW);
        imags.add(Color.DKGRAY);

        if(tamanhoTabuleiro > 16){
            imags.add(Color.parseColor(getString(R.string.cor1)));
            imags.add(Color.parseColor(getString(R.string.cor2)));
            imags.add(Color.parseColor(getString(R.string.cor1)));
            imags.add(Color.parseColor(getString(R.string.cor2)));

            imBtn40.setVisibility(View.VISIBLE);
            imBtn41.setVisibility(View.VISIBLE);
            imBtn42.setVisibility(View.VISIBLE);
            imBtn43.setVisibility(View.VISIBLE);

            if(tamanhoTabuleiro > 20){
                imags.add(Color.parseColor(getString(R.string.cor3)));
                imags.add(Color.parseColor(getString(R.string.cor4)));
                imags.add(Color.parseColor(getString(R.string.cor3)));
                imags.add(Color.parseColor(getString(R.string.cor4)));

                imBtn50.setVisibility(View.VISIBLE);
                imBtn51.setVisibility(View.VISIBLE);
                imBtn52.setVisibility(View.VISIBLE);
                imBtn53.setVisibility(View.VISIBLE);
            }
        }
        return imags;
    }

    public ArrayList <Integer> setTimes() {
        ArrayList<Integer> imags = new ArrayList<>();

        imags.add(R.drawable.saopaulo);
        imags.add(R.drawable.saopaulo);
        imags.add(R.drawable.atleticomg);
        imags.add(R.drawable.atleticomg);
        imags.add(R.drawable.botafogo);
        imags.add(R.drawable.botafogo);
        imags.add(R.drawable.curintia);
        imags.add(R.drawable.curintia);
        imags.add(R.drawable.fluminense);
        imags.add(R.drawable.fluminense);
        imags.add(R.drawable.vasco);
        imags.add(R.drawable.vasco);
        imags.add(R.drawable.inter);
        imags.add(R.drawable.inter);
        imags.add(R.drawable.santos);
        imags.add(R.drawable.santos);

        if(tamanhoTabuleiro > 16){
            imags.add(R.drawable.palmeiras);
            imags.add(R.drawable.cruzeiro);
            imags.add(R.drawable.palmeiras);
            imags.add(R.drawable.cruzeiro);

            imBtn40.setVisibility(View.VISIBLE);
            imBtn41.setVisibility(View.VISIBLE);
            imBtn42.setVisibility(View.VISIBLE);
            imBtn43.setVisibility(View.VISIBLE);

            if(tamanhoTabuleiro > 20){
                imags.add(R.drawable.fortaleza);
                imags.add(R.drawable.fortaleza);
                imags.add(R.drawable.ceara);
                imags.add(R.drawable.ceara);

                imBtn50.setVisibility(View.VISIBLE);
                imBtn51.setVisibility(View.VISIBLE);
                imBtn52.setVisibility(View.VISIBLE);
                imBtn53.setVisibility(View.VISIBLE);
            }
        }

        return imags;
    }
}
