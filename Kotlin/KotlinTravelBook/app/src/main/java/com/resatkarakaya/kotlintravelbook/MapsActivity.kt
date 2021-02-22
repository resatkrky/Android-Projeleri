package com.resatkarakaya.kotlintravelbook

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intentToMain = intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    val myListener = object : GoogleMap.OnMapLongClickListener{
        override fun onMapLongClick(p0: LatLng?) {
            val geocoder = Geocoder(applicationContext, Locale.getDefault())

            var address = ""
            try {
                if(p0 != null){
                    val addressList = geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if (addressList != null && addressList.size > 0){
                        if(addressList[0].thoroughfare != null){
                            address += addressList[0].thoroughfare
                            if(addressList[0].subThoroughfare != null){
                                address += addressList[0].subThoroughfare
                            }
                        }
                    }

                }else{
                    address = "New Place"
                }
            }
            catch (e : Exception){
                e.printStackTrace()
            }
            mMap.clear()
            mMap.addMarker(MarkerOptions().position().title(address))

            val newPlace = Place(address,p0.latitude,p0.longitude)

            val Dialog = AlertDialog.Builder(this@MapsActivity)

            Dialog.setCancelable(false)
            Dialog.setTitle("Are You Sure")
            Dialog.setMessage(newPlace.address)
            Dialog.setPositiveButton("Yes"){dialog , which ->
                //SQLite save işlemi
                try {
                    val db = openOrCreateDatabase("Places",Context.MODE_PRIVATE,null)
                    db.execSQL("CREATE TABLE IF NOT EXISTS places (address VARCHAR , latitude DOUBLE , longitude DOUBLE)")
                    val toCompile = "INSERT INTO places (address , latitude ,longitude) VALUES (?,?,?)"
                    val sqLiteStatement = db.compileStatement(toCompile)
                    sqLiteStatement.bindString(1,newPlace.address)
                    sqLiteStatement.bindDouble(2,newPlace.latitude!!)
                    sqLiteStatement.bindDouble(3,newPlace.longitude!!)
                    sqLiteStatement.execute()

                }catch (e : Exception){
                    e.printStackTrace()
                }
                Toast.makeText(this@MapsActivity,"New Place Created",Toast.LENGTH_LONG).show()


            }.setNegativeButton("No"){dialog , which ->
                Toast.makeText(this@MapsActivity,"Canceled!",Toast.LENGTH_LONG).show()
            }
            Dialog.show()

        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                if(p0 != null){

                    val sharedPreferences = this@MapsActivity.getPreferences(com.resatkarakaya.kotlintravelbook,Context.MODE_PRIVATE)
                    val firstTimeCheck = sharedPreferences.getBoolean("notFirstTime",false)
                    if(firstTimeCheck == false){
                        mMap.clear()
                        val newUserLocation = LatLng(p0.latitude,p0.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newUserLocation,15f))
                        sharedPreferences.edit().putBoolean("notFirstTime",true).apply()
                    }


                }
            }

        }
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            //izin verilmediğinde
        }
        else{ //ilk defadan sonra izin verildiğinde
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)

            val intent = intent
            val info = intent.getStringArrayExtra("info")

            if(info.equals("new")) {
                val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER )
                if (lastLocation != null){
                    val lastlocationLaLng = LatLng(lastLocation.latitude,lastLocation.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastlocationLaLng,15f))
                }
            }
            else{
                mMap.clear()
                val selectedPlace = intent.getSerializableExtra("selectedPlace") as Place
                val selectedLocation = LatLng(selectedPlace.latitude,selectedPlace.longitude!!)
                mMap.addMarker(MarkerOptions().title(selectedPlace.address).position(selectedLocation))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation,15f))
            }


        }

         fun onRequestPermissionsResult( //ilk defa izin verildiğinde
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if(requestCode == 1){
                if(grantResults.size > 0){
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2,2f,locationListener)
                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }
}