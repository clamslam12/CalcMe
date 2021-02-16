package com.minhdurbin.calcme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Scientific2 extends AppCompatActivity {

    //declaring views
    private Button simple;
    private Button sci1;
    private EditText x;
    private EditText y;
    private Button xToY;
    private Button xToYRoot;
    private TextView answer;
    private Button clear;
    private Button google;
    private Button save;

    //shared preferences; value of variables must be different independent memory of activities
    public static final String SHARED_PREFS = "sharedPrefs3";
    public static final String X = "x3";
    public static final String Y ="y3";
    public static final String ANSWER = "answer3";

    private String xShared;
    private String yShared;
    private String answerShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific2);
        //exception handling
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                System.exit(2);
            }
        });

        //initializing views

        x = (EditText) findViewById(R.id.etXInSci2);
        y = (EditText) findViewById(R.id.etYInSci2);
        xToY = (Button) findViewById(R.id.btnXToTheY);
        xToYRoot = (Button) findViewById(R.id.btnXToTheYRoot);
        answer = (TextView) findViewById(R.id.tvAnswerSci2);
        clear = (Button) findViewById(R.id.btnClearSci2);
        save = (Button) findViewById(R.id.btnSaveGoogle);


        //set button actions

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }
        });

        xToY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double num2 = Double.parseDouble(y.getText().toString());
                double numToNum2 = Math.pow(num, num2);
                answer.setText("Answer: " + String.valueOf(numToNum2));
            }
        });

        xToYRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double num2 = Double.parseDouble(y.getText().toString());
                double numToNum2Root = Math.pow(num, 1/num2);
                answer.setText("Answer: " + String.valueOf(numToNum2Root));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x.setText("");
                y.setText("");
                answer.setText("");
                x.requestFocus();
            }
        });


        //activities navigation
        simple = (Button) findViewById(R.id.btnSimpleInSci2);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSimple();
            }
        });

        sci1 = (Button) findViewById(R.id.btnSci1InSci2);
        sci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScientific1();
            }
        });

        google = (Button) findViewById(R.id.btnGoogleSci2);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogle();
            }
        });

        loadData();
        updateViews();

    }
    //activities classes
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(X, x.getText().toString());
        editor.putString(Y, y.getText().toString());
        editor.putString(ANSWER, answer.getText().toString());
        editor.apply();

        //display to user
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        answerShared = sharedPreferences.getString(ANSWER, "");
        xShared = sharedPreferences.getString(X, "");
        yShared = sharedPreferences.getString(Y, "");

    }
    public void updateViews(){
        answer.setText(answerShared);
        x.setText(xShared);
        y.setText(yShared);

    }
    public void openSimple(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openScientific1(){
        Intent intent = new Intent(this, Scientific.class);
        startActivity(intent);
    }
    public void openGoogle(){
        Intent intent = new Intent(this, Google.class);
        startActivity(intent);
    }
}