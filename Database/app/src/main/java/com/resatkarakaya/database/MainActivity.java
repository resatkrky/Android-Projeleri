package com.resatkarakaya.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"Toast Message",Toast.LENGTH_LONG).show();

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textVieww);

        sharedPreferences = this.getSharedPreferences("com.resatkarakaya.database", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("StoredAge",0);

        if(storedAge == 0){
            textView.setText("Your Age: ");
        }
        else{
            textView.setText("Your Age: " + storedAge);
        }


    }



    public void save(View view){
        if(!editText.getText().toString().matches("")){
            int age = Integer.parseInt(editText.getText().toString());
            textView.setText("Your age: " + age);

            sharedPreferences.edit().putInt("StoredAge",age).apply();

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Save");
            alert.setMessage("Are you sure?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //save
                }
            })
        }
    }

    public void delete(View view){
        int storedData = sharedPreferences.getInt("storedAge",0);

        if(storedData != 0){
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your Age: ");
        }
    }

}