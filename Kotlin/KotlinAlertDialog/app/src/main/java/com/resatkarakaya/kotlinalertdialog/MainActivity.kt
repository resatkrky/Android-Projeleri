package com.resatkarakaya.kotlinalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_LONG).show()

        //Context

        //Activity Context ->this

        //App Context -> applicationContext

    }

    fun save(view : View){
        val Alert = AlertDialog.Builder(this)
        Alert.setTitle("save")
        Alert.setMessage("Are You Sure?")
        Alert.setPositiveButton("Yes"){dialog, which -> //PositiveButton'a tıklanınca ne yapılacağını gösterir
            Toast(applicationContext,"Saved",Toast.LENGTH_LONG).show()
        }
        Alert.setNegativeButton("No"){dialog,which ->
            Toast(applicationContext,"Not Saved",Toast.LENGTH_LONG).show()
        }
        Alert.show()
    }
}