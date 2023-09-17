package com.maryam.reservationapptask.nav

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.screens.*

@Composable
fun NavGraph(
    navHostController: NavHostController,
    apiServiceModel: ApiServiceModel
) {
    NavHost(navController = navHostController, startDestination = ScreensHolder.Services.route) {
        composable(ScreensHolder.Home.route) {
            HomeScreen(apiServiceModel, navHostController)
        }
        composable(ScreensHolder.Services.route) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navHostController, 3)
                }
            ) {
                ServicesScreen(apiServiceModel, navHostController)
            }
        }
        composable(
            "${ScreensHolder.SearchAdvisors.route}/{category_id}/{service_id}",
            arguments = listOf(
                navArgument("category_id") { type = NavType.IntType },
                navArgument("service_id") { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            val category_id = navBackStackEntry.arguments?.getInt("category_id")
            val service_id = navBackStackEntry.arguments?.getInt("service_id")
            if (service_id != null) {
                SearchAdvisorsScreen(
                    apiServiceModel,
                    navHostController, category_id, service_id
                )


            }
        }
        composable(
            "${ScreensHolder.GetAdvisorDetails.route}/{advisorId}",
            arguments = listOf(
                navArgument("advisorId") { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            val advisorId = navBackStackEntry.arguments?.getInt("advisorId")
            if (advisorId != null) {
                ServiceProviderDesScreen(
                    apiServiceModel,
                    navHostController,
                    advisorId
                )


            }
        }

        composable(ScreensHolder.ServicesDes.route) {
            ServiceDesScreen(
                apiServiceModel,
                navHostController
            )
        }
        composable(ScreensHolder.Reservations.route) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navHostController, 3)
                }
            ) {
                EmptyScreen("الحجوزات")
            }
        }
        composable(ScreensHolder.Chat.route) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navHostController, 3)
                }
            ) {
                EmptyScreen("الاستفسارت")
            }
        }
        composable(ScreensHolder.Wallet.route) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navHostController, 3)
                }
            ) {
                EmptyScreen("المحفظة")
            }
        }
        composable(ScreensHolder.More.route) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navHostController, 3)
                }
            ) {
                MoreScreen(apiServiceModel)
            }
        }

    }
}