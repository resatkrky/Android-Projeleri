package com.resatkarakaya.hesapmakinasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.number1Text);
        editText2 = findViewById(R.id.number2Text);
        textView = findViewById(R.id.resultText);


    }

    public void topla(View view){
        if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            textView.setText("Enter Number!");
        }
        else{
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());

            int result = number1 + number2;

            textView.setText("Result: " + result);
        }
    }

    public void cikar(View view){
        if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            textView.setText("Enter Number!");
        }
        else{
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());

            int result = number1 - number2;

            textView.setText("Result: " + result);
        }
    }

    public void carp(View view){
        if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            textView.setText("Enter Number!");
        }
        else{
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());

            int result = number1 * number2;

            textView.setText("Result: " + result);
        }
    }

    public void bol(View view){
        if(editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            textView.setText("Enter Number!");
        }
        else{
            int number1 = Integer.parseInt(editText1.getText().toString());
            int number2 = Integer.parseInt(editText2.getText().toString());

            int result = number1 / number2;

            textView.setText("Result: " + result);
        }
    }

}