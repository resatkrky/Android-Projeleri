package com.resatkarakaya.kotlintravelbook

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var placesArray = ArrayList<Place>()

    //Oncreate ve Onoptions ile menu işlemleri
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater //menu olşturuldu
        menuInflater.inflate(R.menu.add_place,menu) //menuye seçenek eklendi

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_place_id){ //eklenen seçeneğin ne iş yapılacağı belirtildi

            val intent = Intent (applicationContext,MapsActivity::class.java) //MapsActivity'e gitmesi ayarladım
            intent.putExtra("info","new")

            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val db = openOrCreateDatabase("Places", Context.MODE_PRIVATE,null)
            val cursor = db.rawQuery("SELECT * FROM places",null)

            val addressIndex = cursor.getColumnIndex("address")
            val latitudeIndex = cursor.getColumnIndex("latitude")
            val longitudeIndex = cursor.getColumnIndex("longitude")
            //Veritabanından sutun cursor'a verileri gösterdim

            while(cursor.moveToNext()){
                val addressFromDatabase = cursor.getString(addressIndex)
                val latitudeFromDatabase = cursor.getString(latitudeIndex)
                val longitudeFromDatabase = cursor.getString(longitudeIndex)
                //cursora gösterdiğim verileri daha sonra teker teker aldım

                val myPlace = Place(addressFromDatabase,latitudeFromDatabase,longitudeFromDatabase)

                placesArray.add(myPlace)

                println(myPlace.address)
            }
            cursor.close()

        }catch (e : Exception){
            e.printStackTrace()
        }

        val customAdapter = CustomAdapter(placesArray,this)
        listView.adapter = customAdapter

        listView.setOnItemClickListener{adapterView, view, i, l ->
            val intent = Intent(this@MainActivity,MapsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("selectedPlace",placesArray.get(i))
            startActivity(intent)
        }
    }
}