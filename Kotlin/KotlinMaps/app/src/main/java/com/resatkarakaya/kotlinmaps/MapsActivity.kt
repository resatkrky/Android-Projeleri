package com.resatkarakaya.kotlinmaps

import android.Manifest
import android.app.Activity
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
import com.google.android.gms.maps.*

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

    val myListener = object : GoogleMap.OnMapLongClickListener {
        //Haritaya uzun tıklayınca adres gösterir
        //Normalde bu fonskyion en alttaydı ama onMaoReady içinde çağrılamadığı için buraya koydum
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()

            val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())

            if (p0 != null) {
                val address = ""

                try {
                    val addressList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)

                    if (addressList != null && addressList.size > 0) {
                        if (addressList[0].thoroughfare != null) { //thorouhgfare adres adı demek
                            address += addressList[0].thoroughfare

                            if (addressList[0].subThoroughfare != null) {
                                address += addressList[0].subThoroughfare
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(address))

            } else {
                Toast.makeText(applicationContext, "Try Again", Toast.LENGTH_LONG).show()
            }

        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapLongClickListener(myListener)

        // Add a marker in Sydney and move the camera
        // val sydney = LatLng(-34.0, 151.0)
        // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                if (p0 != null) {
                    mMap.clear() //açınca markerları siler eski olanları yani tek bir tane marker olur

                    val userLocation = LatLng(p0.latitude, p0.altitude) //kullanıcının konumu alındı
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                    //Marker ekledim
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                    // Enlem ve boylamdan adres türetmek
                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())

                    try {
                        val addresList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)

                        if (addresList != null && addresList.size > 0) {
                            println(addresList.get(0).toString())
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
            }

            override fun onProviderEnabled(provider: String) {
                super.onProviderEnabled(provider)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                super.onStatusChanged(provider, status, extras)
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Kullanıcı izni verilmediyse
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            //İzin istendi
        } else {
            //Daha önceden izin verildiyse konum alındı LocationListener'a aktarıldı.
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                2f,
                locationListener
            )
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            //GPS_PROVIDER'da son lokasyon var mı ona bakıyorum
            if (lastLocation != null) {
                val lastKnowLatLng = LatLng(lastLocation.latitude, lastLocation.altitude)
                mMap.addMarker(MarkerOptions().position(lastKnowLatLng).title("Your Location"))
                //Marker ekledim
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnowLatLng, 15f))
            }

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            //Kullanıcı o an izin verdiğinde yapılacak işlemler

            if (requestCode == 1) {
                if (grantResults.size > 0) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            0,
                            2f,
                            locationListener
                        )
                    }
                }
            }

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
