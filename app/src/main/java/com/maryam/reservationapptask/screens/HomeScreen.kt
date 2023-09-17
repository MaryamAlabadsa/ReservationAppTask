package com.maryam.reservationapptask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.nav.BottomNavItem
import com.maryam.reservationapptask.nav.NavGraph
import com.maryam.reservationapptask.ui.theme.yellowStatus
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    apiServiceModel: ApiServiceModel,
    navControllerHome: NavHostController,
) {
    NavGraph(navControllerHome, apiServiceModel = apiServiceModel)
}

