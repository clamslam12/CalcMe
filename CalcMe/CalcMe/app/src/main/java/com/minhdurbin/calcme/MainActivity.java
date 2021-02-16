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

public class MainActivity extends AppCompatActivity {
    //set references
    private Button sciBtnInSimple;
    private Button sci2BtnInSimple;
    private Button add;
    private Button subtract;
    private Button multiply;
    private Button divide;
    private TextView answer;
    private EditText x;
    private EditText y;
    private Button clear;
    private Button google;
    private Button saveButton;

    //shared preferences; value of variables must be different independent memory of activities
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String X = "x";
    public static final String Y ="y";
    public static final String ANSWER = "answer";

    private String xShared;
    private String yShared;
    private String answerShared;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //exception handling
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                System.exit(2);
            }
        });

        //get reference id's; initialize views
        add = (Button) findViewById(R.id.btnAdd);
        subtract = (Button) findViewById(R.id.btnSubtract);
        multiply = (Button) findViewById(R.id.btnMultiply);
        divide = (Button) findViewById(R.id.btnDivide);
        answer = (TextView) findViewById(R.id.tvAnswerSci1);
        x = (EditText) findViewById(R.id.etXInSci1);
        y = (EditText) findViewById(R.id.etY);
        clear = (Button) findViewById(R.id.btnClearSimple);
        saveButton = (Button) findViewById(R.id.btnSaveSimple);

        //set button actions

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number1 = Double.parseDouble(x.getText().toString());
                double number2 = Double.parseDouble((y.getText().toString()));
                double sum = number1 + number2;
                answer.setText("Answer: "+ String.valueOf(sum));
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number1 = Double.parseDouble(x.getText().toString());
                double number2 = Double.parseDouble((y.getText().toString()));
                double sum = number1 - number2;
                answer.setText("Answer: "+ String.valueOf(sum));

            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number1 = Double.parseDouble(x.getText().toString());
                double number2 = Double.parseDouble((y.getText().toString()));
                double sum = number1 * number2;
                answer.setText("Answer: "+ String.valueOf(sum));

            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number1 = Double.parseDouble(x.getText().toString());
                double number2 = Double.parseDouble((y.getText().toString()));
                double sum = number1 / number2;
                answer.setText("Answer: "+ String.valueOf(sum));
            }
        });


        //activities navigation
        sciBtnInSimple = (Button) findViewById(R.id.btnScientificInSimple);
        sciBtnInSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScientific();

            }
        });

        sci2BtnInSimple = (Button) findViewById(R.id.btnSci2InSimple);
        sci2BtnInSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScientific2();
            }
        });

        google = (Button) findViewById(R.id.btnGoogleSimple);
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
    public void openScientific(){
        Intent intent = new Intent(this, Scientific.class);
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