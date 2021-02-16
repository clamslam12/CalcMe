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

public class Scientific extends AppCompatActivity {

    //declare views
    private Button simpleBtnInSci;
    private Button sci2BtnInSci1;
    private EditText x;
    private Button xSq;
    private Button xCu;
    private Button lnX;
    private Button eX;
    private Button oneOverX;
    private Button log10;
    private Button sin;
    private Button cos;
    private Button tan;
    private TextView answer;
    private Button clear;
    private Button google;
    private Button save;

    //shared prefs
    public static final String SHARED_PREFS_2 = "sharedPrefs2";
    public static final String ANSWER_2 = "answer2";
    public static final String X_2 = "x2";
    private String xShared2;
    private String answerShared2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific);
        //exception handling
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                System.exit(2);
            }
        });

        //initialize views with IDs
        x = (EditText) findViewById(R.id.etXInSci1);
        xSq = (Button) findViewById(R.id.btnSquared);
        xCu = (Button) findViewById(R.id.btnCubed);
        lnX = (Button) findViewById(R.id.btnLn);
        eX = (Button) findViewById(R.id.btnE);
        oneOverX = (Button) findViewById(R.id.btn1Over);
        log10 = (Button) findViewById(R.id.btnLog10);
        sin = (Button) findViewById(R.id.btnSin);
        cos = (Button) findViewById(R.id.btnCos);
        tan = (Button) findViewById(R.id.btnTan);
        answer = (TextView) findViewById(R.id.tvAnswerSci1);
        clear = (Button) findViewById(R.id.btnClearSci1);
        save = (Button) findViewById(R.id.btnSaveSci1);


        //set button functions

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();;
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x.setText("");
                answer.setText("");
                x.requestFocus();
            }
        });

        xSq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double numSquared = Math.pow(num, 2);
                answer.setText("Answer: " + String.valueOf(numSquared));
            }
        });

        xCu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double numCubed = Math.pow(num, 3);
                answer.setText("Answer: " + String.valueOf(numCubed));
            }

        });

        lnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double lnNum = Math.log(num);
                answer.setText("Answer: " + String.valueOf(lnNum));
            }
        });

        eX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double eNum = Math.exp(num);
                answer.setText("Answer: " + String.valueOf(eNum));
            }
        });

        oneOverX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double oneOverNum = 1 / num;
                answer.setText("Answer: " + String.valueOf(oneOverNum));
            }
        });

        log10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double log10Num = Math.log10(num);
                answer.setText("Answer: " + String.valueOf(log10Num));
            }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double sinNum = Math.sin(num);
                answer.setText("Answer: " + String.valueOf(sinNum));

            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double cosNum = Math.cos(num);
                answer.setText("Answer: " + String.valueOf(cosNum));

            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(x.getText().toString());
                double tanNum = Math.tan(num);
                answer.setText("Answer: " + String.valueOf(tanNum));
            }
        });





        //activities navigation

        simpleBtnInSci = (Button) findViewById(R.id.btnSimpleInSci2);
        simpleBtnInSci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        sci2BtnInSci1 = (Button) findViewById(R.id.btnSci2InSci1);
        sci2BtnInSci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScientific2();
            }
        });

        google = (Button) findViewById(R.id.btnGoogleSci1);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogle();
            }
        });

        loadData();
        updateViews();

    }
     //activities classes functions
     public void saveData(){
         SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_2, MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();

         editor.putString(X_2, x.getText().toString());
         editor.putString(ANSWER_2, answer.getText().toString());
         editor.apply();

         //display to user
         Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

     }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_2, MODE_PRIVATE);
        xShared2 = sharedPreferences.getString(X_2, "");
        answerShared2 = sharedPreferences.getString(ANSWER_2, "");


    }
    public void updateViews(){
        answer.setText(answerShared2);
        x.setText(xShared2);


    }
     public void openMainActivity(){
         Intent intent = new Intent(this, MainActivity.class);
         startActivity(intent);

     }
     public void openScientific2(){
        Intent intent = new Intent(this, Scientific2.class);
        startActivity(intent);
     }

     public void openGoogle(){
        Intent intent = new Intent(this, Google.class);
        startActivity(intent);
     }
}