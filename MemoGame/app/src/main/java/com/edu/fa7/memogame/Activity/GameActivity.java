package com.edu.fa7.memogame.Activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.edu.fa7.memogame.R;
import com.edu.fa7.memogame.Utils.Record;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

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
    private int totalAcertos;
    private boolean isInit = true;
    protected int tamanhoTabuleiro;
    private String tipoDeJogo = "";
    private Record mRecord;
    private long elapsedMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        tvScores = (TextView) findViewById(R.id.text_view_scores);
        mRecord = new Record();
        mRecord.setName(getIntent().getExtras().getString(getString(R.string.personName)));

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
        setImagensTabuleiro();
        setDefaultButtonImageValue();
    }

    private void setImagensTabuleiro() {
        if (tipoDeJogo.equals(getString(R.string.times))) {
            imagens = setTimes(); //Times
        }else if(tipoDeJogo.equals(getString(R.string.carros))){
            imagens = setCarros(); //Carros
        }else if(tipoDeJogo.equals(getString(R.string.baralho))){
            imagens = setCartas(); // Cartas
        }else{
            imagens = setTimes();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_view_record) {
            startActivity(new Intent(GameActivity.this, ViewRecordActivity.class));
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

    private void animationButton(final View view, final int picture) {
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(picture);
                verifierColorButton(picture, view);
            }
        }, 300);
    }

    public void verifierColorButton(int picture, final View v){
        if (countClick1 == -1) {
            countClick1 = picture;
            view = v;
            view.setClickable(false);
        }else {
            countClick2 = picture;
            if(countClick1 == countClick2){
                countClick1 = -1;
                countClick2 = -1;
                view.setClickable(false);
                v.setClickable(false);
                totalAcertos +=2;

                if (isEndGame()){
                    changeStatusViewButton(true);
                    zerarChronometer();
                    isInit = true;
                    showFinishDialog();
                    registrarRecord();
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

    private void registrarRecord() {
        mRecord.setRecord(elapsedMillis);
        mRecord.save();
    }

    private void zerarChronometer() {
        elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        chronometer.setActivated(false);
        tvScores.setText(String.valueOf(elapsedMillis));
    }

    public void setDefaultButtonImageValue(){
        Collections.shuffle(imagens);

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
        builder.setTitle(getString(R.string.fim_de_jogo));
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDefaultButtonImageValue();
                mRecord = new Record();
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
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
        String tabuleiro = prefs.getString("listboard", "16");
        String game = prefs.getString("listgame", "times");
        tamanhoTabuleiro = Integer.valueOf(tabuleiro);
        tipoDeJogo = game;
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

    public ArrayList <Integer> setCarros() {
        ArrayList<Integer> imags = new ArrayList<>();

        imags.add(R.drawable.lamborghini);
        imags.add(R.drawable.lamborghini);
        imags.add(R.drawable.ferrari);
        imags.add(R.drawable.ferrari);
        imags.add(R.drawable.bmw);
        imags.add(R.drawable.bmw);
        imags.add(R.drawable.mercedes);
        imags.add(R.drawable.mercedes);
        imags.add(R.drawable.bugatti);
        imags.add(R.drawable.bugatti);
        imags.add(R.drawable.jaguar);
        imags.add(R.drawable.jaguar);
        imags.add(R.drawable.alfa_romeo);
        imags.add(R.drawable.alfa_romeo);
        imags.add(R.drawable.infiniti);
        imags.add(R.drawable.infiniti);

        if(tamanhoTabuleiro > 16){
            imags.add(R.drawable.mustang);
            imags.add(R.drawable.mustang);
            imags.add(R.drawable.porshe);
            imags.add(R.drawable.porshe);

            imBtn40.setVisibility(View.VISIBLE);
            imBtn41.setVisibility(View.VISIBLE);
            imBtn42.setVisibility(View.VISIBLE);
            imBtn43.setVisibility(View.VISIBLE);

            if(tamanhoTabuleiro > 20){
                imags.add(R.drawable.lambgallardo);
                imags.add(R.drawable.lambgallardo);
                imags.add(R.drawable.pagani);
                imags.add(R.drawable.pagani);

                imBtn50.setVisibility(View.VISIBLE);
                imBtn51.setVisibility(View.VISIBLE);
                imBtn52.setVisibility(View.VISIBLE);
                imBtn53.setVisibility(View.VISIBLE);
            }
        }

        return imags;
    }public ArrayList <Integer> setCartas() {
        ArrayList<Integer> imags = new ArrayList<>();

        imags.add(R.drawable.baralho_as);
        imags.add(R.drawable.baralho_as);
        imags.add(R.drawable.baralho_king);
        imags.add(R.drawable.baralho_king);
        imags.add(R.drawable.baralho_queen);
        imags.add(R.drawable.baralho_queen);
        imags.add(R.drawable.baralho_valete);
        imags.add(R.drawable.baralho_valete);
        imags.add(R.drawable.baralho_10);
        imags.add(R.drawable.baralho_10);
        imags.add(R.drawable.baralho_09);
        imags.add(R.drawable.baralho_09);
        imags.add(R.drawable.baralho_08);
        imags.add(R.drawable.baralho_08);
        imags.add(R.drawable.baralho_07);
        imags.add(R.drawable.baralho_07);

        if(tamanhoTabuleiro > 16){
            imags.add(R.drawable.baralho_06);
            imags.add(R.drawable.baralho_06);
            imags.add(R.drawable.baralho_05);
            imags.add(R.drawable.baralho_05);

            imBtn40.setVisibility(View.VISIBLE);
            imBtn41.setVisibility(View.VISIBLE);
            imBtn42.setVisibility(View.VISIBLE);
            imBtn43.setVisibility(View.VISIBLE);

            if(tamanhoTabuleiro > 20){
                imags.add(R.drawable.baralho_04);
                imags.add(R.drawable.baralho_04);
                imags.add(R.drawable.baralho_03);
                imags.add(R.drawable.baralho_03);

                imBtn50.setVisibility(View.VISIBLE);
                imBtn51.setVisibility(View.VISIBLE);
                imBtn52.setVisibility(View.VISIBLE);
                imBtn53.setVisibility(View.VISIBLE);
            }
        }

        return imags;
    }
}
