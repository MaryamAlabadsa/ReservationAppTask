package com.maryam.reservationapptask.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.maryam.reservationapptask.items.ScreenHeaderItem
import com.maryam.reservationapptask.items.ServiceItem
import com.maryam.reservationapptask.nav.ScreensHolder
import com.maryam.reservationapptask.ui.theme.gray
import com.maryam.reservationapptask.ui.theme.grayBtn


@Composable
fun ServicesScreen(apiServiceModel: ApiServiceModel, navController: NavHostController) {
    val context = LocalContext.current
    apiServiceModel.getService(context)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(
            Modifier
                .fillMaxSize()
                .background(gray)) {
            ScreenHeaderItem("الخدمات")
//        ScreenHeaderItem(Title2 = R.string.service)
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                ScrollingContent(apiServiceModel, navController, context)
            }

        }
    }
}

@Composable
fun ScrollingContent(
    apiServiceModel: ApiServiceModel,
    navController: NavHostController,
    context: Context
) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.service_sections),
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        LazyList(apiServiceModel = apiServiceModel, navController = navController, context)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyList(apiServiceModel: ApiServiceModel, navController: NavHostController, context: Context) {
    LazyVerticalGrid(
        state = rememberLazyListState(),
        cells = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(apiServiceModel.liveCategoriesData) { category ->
            ServiceItem(
                icon = category.image, // Assuming 'icon' is a property of the Categories object
                text = category.name, // Assuming 'name' is a property of the Categories object
                onClick = {
                    val sharedPrefs: SharedPreferences =
                        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPrefs.edit()

                    val gson = Gson()
                    val dataJson = gson.toJson(category)
                    editor.putString("category_data", dataJson)
                    editor.apply()
                    navController.navigate(
                        ScreensHolder.ServicesDes.route
//                        ScreensHolder.ServicesDes.route +"/${dataJson}"
//                        ScreensHolder.ServicesDes.route + "/$category.id"+"/$category.name"+"/${dataJson}"

                    )
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            ServicesScreen(apiServiceModel)
        }
    }
}