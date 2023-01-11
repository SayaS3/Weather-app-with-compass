package com.studioartagonist.weather

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.studioartagonist.weather.network.detectUserLocation
import com.studioartagonist.weather.viewmodel.LocationViewModel

class MainActivity : AppCompatActivity() {
    private val locationViewModel: LocationViewModel by viewModels()


    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                getUserLocation()

            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                getUserLocation()

            }
            else -> {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )


    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        detectUserLocation(this) {
            locationViewModel.setNewLocationLiveData(it)
        }

    }
}




