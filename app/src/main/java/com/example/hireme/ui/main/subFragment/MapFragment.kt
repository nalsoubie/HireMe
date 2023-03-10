package com.example.hireme.ui.main.subFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hireme.R
import com.example.hireme.utils.generateMapCameraLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_job_detail_fragement.*
import kotlinx.android.synthetic.main.fragment_map.*


open class MapFragment constructor(val latLng: LatLng) : Fragment(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap


    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(latLng: LatLng) =
            MapFragment(latLng)
    }

    override fun onMapReady(p0: GoogleMap) {
        val location = LatLng(latLng.latitude, latLng.longitude)

        map?.let {
            googleMap = p0
            googleMap.apply {
                this.moveCamera(CameraUpdateFactory.newLatLng(generateMapCameraLocation(location)))
                //this.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-33.100,151.100), 10f))
                this.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title("Work Location")
                )

            }
        }
    }


}
