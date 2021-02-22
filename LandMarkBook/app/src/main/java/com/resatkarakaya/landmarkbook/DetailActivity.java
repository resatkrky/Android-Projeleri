package com.resatkarakaya.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//import static com.resatkarakaya.landmarkbook.MainActivity.selectedImage;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView landMarkNameText;
    TextView CountryNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        landMarkNameText = findViewById(R.id.landMarkNameText);
        CountryNameText = findViewById(R.id.countryNameText);

        Intent intent = getIntent();
        String landMarkName = intent.getStringExtra("name");
        String CountryName = intent.getStringExtra("country");
        landMarkNameText.setText(landMarkName);
        CountryNameText.setText(CountryName);
        Singleton singleton = Singleton.getInstance();
        imageView.setImageBitmap(singleton.getChosenImage());

       // imageView.setImageBitmap(selectedImage);

    }
}