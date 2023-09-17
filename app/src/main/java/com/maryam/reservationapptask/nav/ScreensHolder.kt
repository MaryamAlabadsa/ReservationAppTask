package com.maryam.reservationapptask.nav

import androidx.compose.ui.graphics.vector.ImageVector
import com.maryam.reservationapptask.R

sealed class ScreensHolder(
    val route:String
){
    object Home:ScreensHolder(route = "Home")
    object Services:ScreensHolder(route = "Services")
    object ServicesDes:ScreensHolder(route = "ServicesDes")
    object SearchAdvisors:ScreensHolder(route = "searchAdvisors")
    object GetAdvisorDetails:ScreensHolder(route = "getAdvisorDetails")
    object Reservations:ScreensHolder(route = "reservations" )
    object Chat:ScreensHolder(route = "Chat")
    object Wallet:ScreensHolder(route = "Wallet")
    object More:ScreensHolder(route = "More" )
}
sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Service : BottomNavItem(route = "Services", title = "الخدمات", icon = R.drawable.service_ico)
    object Reservations : BottomNavItem(route = "reservations", title = "الحجوزات", icon = R.drawable.reservations)
    object Chat : BottomNavItem(route = "Chat",title = "الاستفسارت", icon = R.drawable.chat)
    object Wallet : BottomNavItem(route = "Wallet",title = "المحفظة", icon = R.drawable.wallet)
    object More : BottomNavItem(route = "More",title = "المزيد", icon = R.drawable.more)
}