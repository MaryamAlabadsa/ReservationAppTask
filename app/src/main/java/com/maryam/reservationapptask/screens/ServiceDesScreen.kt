package com.maryam.reservationapptask.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.google.gson.Gson
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.items.ServiceDesItem
import com.maryam.reservationapptask.items.TwoCircles
import com.maryam.reservationapptask.model.Response.Categories
import com.maryam.reservationapptask.nav.ScreensHolder
import com.maryam.reservationapptask.ui.theme.Purble
import com.maryam.reservationapptask.ui.theme.gray

@Composable
fun ServiceDesScreen(
    apiServiceModel: ApiServiceModel,
    navController: NavHostController,
//    name: String,
//    icon: String
) {
    val sharedPrefs: SharedPreferences =
        LocalContext.current.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val dataJson = sharedPrefs.getString("category_data", null)
    val category: Categories = gson.fromJson(dataJson, Categories::class.java)

    category.id.let {
        if (it != null) {
            apiServiceModel.getServiceDes(LocalContext.current, it)
        }
    }
//    var liveData=apiServiceModel.liveServicesDesData
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray)
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.service_des),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "ArrowBack Icon",
                            modifier = Modifier
                                .size(24.dp).align(Alignment.CenterStart)
                                .clickable { navController.popBackStack() },
                            tint = Color.White
                        )
                        category.name.let {
                            if (it != null) {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        category.image.let {
                            if (it != null) {
                                TwoCircles(first = 70, second = 60, it)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .height(50.dp)
                                .width(195.dp)
                                .align(Alignment.Center)
                                .padding(start = 8.dp),
                            shape = RoundedCornerShape(24.dp),
                            backgroundColor = Purble

                        ) {
                            Text(
                                text = "خدمات التجارة",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(top = 13.dp, start = 36.dp)
                            )
                        }

                    }
                    Text(
                        text = "خدمات التجارة",
//                    fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Card(
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                            Text(
                                text = "الرجاء اختيار احدى الخدمات التالية",
                                color = Color.Black,
                                fontSize = 14.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(top = 14.dp)
                            )
                            if (apiServiceModel.liveServicesDesData.size == 0) {
                                Box(
                                    modifier = Modifier
                                        .height(380.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.empty_box),
                                        contentDescription = "",
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(400.dp),
                                        contentScale = ContentScale.Crop,

                                        )
//                                    Text(
//                                        text = "لا توجد خدمات حاليا ", modifier = Modifier.align(
//                                            Alignment.Center
//                                        ),
//                                        textAlign = TextAlign.Center
//                                    )
                                }
                            } else {
                                LazyColumn {
                                    itemsIndexed(items = apiServiceModel.liveServicesDesData) { index, item ->
                                        ServiceDesItem(data = item,
                                            onClick = {
                                                navController.navigate(
                                                    "${ScreensHolder.SearchAdvisors.route}/${category.id}/${item.id}"
                                                )
                                            })
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}