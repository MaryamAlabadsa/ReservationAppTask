package com.maryam.reservationapptask

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.nav.NavGraph
import com.maryam.reservationapptask.screens.BottomNavigation
import com.maryam.reservationapptask.screens.HomeScreen
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme

class MainActivity : ComponentActivity() {
    private val READ_EXTERNAL_STORAGE_REQUEST = 123 // Choose any request code you like

    private fun checkPermission() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val granted = PackageManager.PERMISSION_GRANTED

        if (ContextCompat.checkSelfPermission(this, permission) != granted) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                READ_EXTERNAL_STORAGE_REQUEST
            )
        } else {
            // Permission is already granted, you can proceed with reading files
            // Call the method to read files here
        }
    }

    // Handle the result of the permission request
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with reading files
                // Call the method to read files here
            } else {
                // Permission denied, handle this case (e.g., show an explanation to the user)
            }
        }
    }

    lateinit var navController: NavHostController
    private val apiServiceModel: ApiServiceModel by viewModels() {
        SavedStateViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReservationAppTaskTheme {
                checkPermission()
                navController = rememberNavController()
                HomeScreen(apiServiceModel, navController)
            }
        }
    }
}
