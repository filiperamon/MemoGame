package com.edu.fa7.memogame.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.fa7.memogame.R;

public class MainScreenActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnOption;
    private EditText etPesonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sreen);

        btnStart = (Button) findViewById(R.id.btn_iniciar);
        btnOption = (Button) findViewById(R.id.btn_opcoes);
        etPesonName = (EditText) findViewById(R.id.personName);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle parameter = new Bundle();
                if(etPesonName.getText().toString().length()>0)
                    parameter.putString(getString(R.string.personName),etPesonName.getText().toString());
                else
                    parameter.putString(getString(R.string.personName),getString(R.string.personName));

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtras(parameter);
                startActivity(intent);
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_record) {
            startActivity(new Intent(MainScreenActivity.this, ViewRecordActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
