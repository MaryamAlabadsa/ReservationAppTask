package com.maryam.reservationapptask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maryam.reservationapptask.nav.BottomNavItem
import com.maryam.reservationapptask.ui.theme.yellowStatus

@Composable
fun BottomNavigation(navController: NavHostController, notfiNumber: Int) {
    val items = listOf(
        BottomNavItem.Service,
        BottomNavItem.Reservations,
        BottomNavItem.Chat,
        BottomNavItem.Wallet,
        BottomNavItem.More
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .clip( RoundedCornerShape(16.dp)),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Box {
//                        if (notfiNumber > 0) {
//                            Text(
//                                text = "13",
//                                modifier = Modifier
//                                    .background(color = yellowStatus, CircleShape)
//                                    .size(13.dp)
//                                    .align(
//                                        Alignment.TopStart
//                                    ),
//                                color = Color.White,
//                            )
//                        }
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
