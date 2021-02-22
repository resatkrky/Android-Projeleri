package com.resatkarakaya.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
   // static Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        //Data
        final ArrayList<String> landMarkNames = new ArrayList<>();
        landMarkNames.add("Pisa");
        landMarkNames.add("Eiffel");
        landMarkNames.add("Colleseo");
        landMarkNames.add("London Bridge");

        final ArrayList<String> countryName = new ArrayList<>();
        countryName.add("Italy");
        countryName.add("France");
        countryName.add("Italy");
        countryName.add("England");

        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        Bitmap eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eiffel);
        Bitmap colleseo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.colleseo);
        Bitmap bridge = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.londonbridge);

        final ArrayList<Bitmap> landMarkImages = new ArrayList<>();
        landMarkImages.add(pisa);
        landMarkImages.add(eiffel);
        landMarkImages.add(colleseo);
        landMarkImages.add(bridge);


        //Listview
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,landMarkNames);
        //Listelerdeki içerikleri göstermek için kullanılır.

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Tıklandığı sayfaya biligleri yollamak için
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(landMarkNames.get(i)); //Console çıkış verdi
                System.out.println(countryName.get(i));

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("name",landMarkNames.get(i)); //Tıklayınca Verileri Gosterir
                intent.putExtra("country",countryName.get(i));//Tıklayınca Verileri Gosterir
               // selectedImage = landMarkImages.get(i);
                Singleton singleton = Singleton.getInstance();
                singleton.setChosenImage(landMarkImages.get(i));

                startActivity(intent);

            }
        });

    }
}