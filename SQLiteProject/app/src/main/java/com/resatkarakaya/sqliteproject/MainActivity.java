package com.resatkarakaya.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase db = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");

            db.execSQL("INSERT INTO musicians (name,age) VALUES('Resat',22)");
            db.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Resat'");
            db.execSQL("DELETE FROM musicians WHERE id = 2");

           // Cursor cursor = db.rawQuery("SELECT * FROM musicians WHERE id=2",null);
           // Cursor cursor = db.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null);
            Cursor cursor = db.rawQuery("SELECT * FROM musicians",null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getString(ageIndex));
                System.out.println("Id: " + cursor.getString(idIndex));
            }
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}