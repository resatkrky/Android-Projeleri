package com.resatkarakaya.kotlindata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    var ageFromPreferences : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        //lateinit sonradan initialize edicem demek

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Veritabanına kaydetmek SQLite
        sharedPreferences = this.getSharedPreferences("com.resatkarakaya.kotlindata", Context.MODE_PRIVATE)

        //Veritabanından çekmek
        ageFromPreferences = sharedPreferences.getInt("age",-1)

        if(ageFromPreferences == 0){
            textView.text ="Your Age"
        }
        else{
            textView.text = "Your Age: $ageFromPreferences"
        }
    }

    fun save(view : View){

        val myAge = ageText.text.toString().toIntOrNull()

        if(myAge != null){
            textView.text = "Your Age"+ $myAge
            sharedPreferences.edit().putInt("age",myAge).apply()

        }
    }

    fun delete(view : View){
        ageFromPreferences = sharedPreferences.getInt("age",-1)

        if(ageFromPreferences != -1){
            sharedPreferences.edit().remove("age").apply()
            textView.text = "Your Age"
        }

    }
}