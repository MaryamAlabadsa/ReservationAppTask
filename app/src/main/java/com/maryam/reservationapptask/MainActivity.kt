package com.maryam.reservationapptask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.nav.NavGraph
import com.maryam.reservationapptask.screens.BottomNavigation
import com.maryam.reservationapptask.screens.HomeScreen
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    private val apiServiceModel: ApiServiceModel by viewModels() {
        SavedStateViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReservationAppTaskTheme {
                navController = rememberNavController()
//                NavGraph(navHostController = navController, apiServiceModel)
//                Scaffold(
//                    bottomBar = {
//                        BottomNavigation(navController = navController,3)
//                    }
//                ) {
                HomeScreen(apiServiceModel, navController)

//                    NavGraph(navController, apiServiceModel = apiServiceModel)
//                }
            }
        }
    }
}
