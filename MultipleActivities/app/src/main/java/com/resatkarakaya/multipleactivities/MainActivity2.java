package com.resatkarakaya.multipleactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("userInput");

        textView.setText(username);

    }

    public void changeScreen(View view){
        Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent2);
    }
}