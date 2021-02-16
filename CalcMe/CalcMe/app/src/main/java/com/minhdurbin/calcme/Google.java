package com.minhdurbin.calcme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Google extends AppCompatActivity {
    private Button simple;
    private Button sci1;
    private Button sci2;
    private Button search;
    private EditText etSearch;
    private Button clear;
    private Button save;

    //shared prefs
    public static final String SHARED_PREFS = "sharedPrefs4";
    public static final String ANSWER = "answer";

    private String answerShared;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);
        //exception handling
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                System.exit(2);
            }
        });

        //initialize views with IDs
        simple = (Button) findViewById(R.id.btnSimpleGoogle);
        sci1 = (Button) findViewById(R.id.btnSci1Google);
        sci2 = (Button) findViewById(R.id.btnSci2Google);
        search = (Button) findViewById(R.id.btnSearch);
        etSearch = (EditText) findViewById(R.id.etGoogle);
        clear = (Button) findViewById(R.id.btnClearGoogle);
        save = (Button) findViewById(R.id.btnSaveGoogle);


        //set button actions

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSimple();
            }
        });

        sci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSci1();
            }
        });

        sci2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSci2();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/search?q="+etSearch.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
                etSearch.requestFocus();
            }
        });

        loadData();
        updateViews();


    }
    //activities classes functions
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(ANSWER, etSearch.getText().toString());
        editor.apply();

        //display to user
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        answerShared = sharedPreferences.getString(ANSWER, "");

    }
    public void updateViews(){
        etSearch.setText(answerShared);

    }
    public void openSimple(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSci1(){
        Intent intent = new Intent(this, Scientific.class);
        startActivity(intent);
    }

    public void openSci2(){
        Intent intent = new Intent(this, Scientific2.class);
        startActivity(intent);
    }
}