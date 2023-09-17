package com.maryam.reservationapptask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.items.ServiceAdvisorsItem
import com.maryam.reservationapptask.nav.ScreensHolder
import com.maryam.reservationapptask.ui.theme.AppBlue
import com.maryam.reservationapptask.ui.theme.gray

@Composable
fun SearchAdvisorsScreen(
    apiServiceModel: ApiServiceModel,
    navController: NavHostController,
    category_id: Int?,
    service_id: Int,
) {

    if (category_id != null) {
        apiServiceModel.searchAdvisors(LocalContext.current, category_id, service_id)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(AppBlue)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowForward,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(
                text = "مقدمي الخدمة  ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.White
            )

        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollingContent(apiServiceModel,navController)
        }

    }
}

@Composable
fun ScrollingContent(apiServiceModel: ApiServiceModel,navController: NavHostController) {
//    var scrollState by rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "مقدمي الخدمة  ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
        ) {
            itemsIndexed(items = apiServiceModel.liveAdvisorsData) { index, item ->
                ServiceAdvisorsItem(
                    data = item
                ) {
                    navController.navigate(
                        "${ScreensHolder.GetAdvisorDetails.route}/${item.id}"
                    )
                }
            }
        }

    }
}