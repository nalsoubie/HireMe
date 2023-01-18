package com.example.hireme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hireme.model.Job
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_job_detail_fragement.*
import kotlinx.android.synthetic.main.fragment_map_fragement.*


class ExpandedMapFragment : Fragment(), OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    lateinit var item: Job


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        expandedMap.onCreate(savedInstanceState)
        expandedMap.onResume()
        expandedMap.getMapAsync(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expand_map_back_arrow.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(
                R.id.jobDetailFragment,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = (arguments?.get("item") as Job?)!!

        return inflater.inflate(R.layout.fragment_map_fragement, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ExpandedMapFragment()
    }

    override fun onMapReady(p0: GoogleMap) {
        val location = LatLng(item.Location?.latitude!!, item.Location?.longitude!!)

        map?.let {
            googleMap = p0
            googleMap.apply {
                moveCamera(CameraUpdateFactory.newLatLng(location))
                addMarker(
                    MarkerOptions()
                        .position(location)
                        .title("Work Location")
                )

            }
        }
    }
}