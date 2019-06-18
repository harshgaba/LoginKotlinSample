package com.harshgaba.loginkotlinsample.ui.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.utils.LAT
import com.harshgaba.loginkotlinsample.utils.LNG


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val lat:String=intent.getStringExtra(LAT)
        val lng:String=intent.getStringExtra(LNG)

        val latlng = LatLng(lat.toDouble(), lng.toDouble())
        mMap.addMarker(MarkerOptions().position(latlng).title(""))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))
    }
}
