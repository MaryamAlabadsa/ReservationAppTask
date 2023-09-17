package com.maryam.reservationapptask.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.items.*
import com.maryam.reservationapptask.model.Response.AdvisorDes
import com.maryam.reservationapptask.model.Response.Categories
import com.maryam.reservationapptask.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ServiceProviderDesScreen(
    apiServiceModel: ApiServiceModel,
    navHostController: NavHostController,
    advisorId: Int
) {
    val liveData = apiServiceModel.liveAdvisorsDesData
    apiServiceModel.getAdvisorDetails(LocalContext.current, advisorId)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        BottomSheetLayout(liveData, apiServiceModel, advisorId, navHostController)

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    liveData: AdvisorDes,
    apiServiceModel: ApiServiceModel,
    advisorId: Int,
    navHostController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
//        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = modalSheetState,
        sheetContent = {
            Column(
                //...
            ) {
                SheetContent(coroutineScope, modalSheetState, apiServiceModel, advisorId)
            }
        },
//        sheetContentColor = Color.White,
        sheetBackgroundColor = Color.White
    ) {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gray),
//                contentAlignment = Alignment.Center,
            ) {
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
                            tint = Color.White,
                            modifier = Modifier.clickable { navHostController.popBackStack() },

                            )
                    }
                    Text(
                        text = " تفاصيل مقدم الخدمة  ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible)
                                    modalSheetState.hide()
                                else
                                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .background(color = Color.Transparent),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppBlue.copy(
                                alpha = 0.7F, // Set a valid alpha value, e.g., 0.7 for 70% opacity
                            )
                        )
                    )
                    {
                        Text(

                            text = " تقديم شكوى  ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                }
                screenContent(liveData = liveData, apiServiceModel = apiServiceModel)
            }

        }
    }
}

@Composable
fun screenContent(liveData: AdvisorDes, apiServiceModel: ApiServiceModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FirstBox(modifier = Modifier.weight(1f), liveData)
        liveData.advisor?.let {
            SecondBox(
                modifier = Modifier.weight(1f), apiServiceModel = apiServiceModel,
                it.categories
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstBox(modifier: Modifier, liveData: AdvisorDes) {
    var context = LocalContext.current
    val message = "يتم تطوير هذه الخاصية قريبا"
    val duration = Toast.LENGTH_LONG

    var advisorDes = liveData.advisor
    ScreenCard(modifier) {
        Column(modifier = Modifier.padding(top = 22.dp, start = 8.dp, end = 8.dp)) {
            Row() {
                if (advisorDes != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(advisorDes.avatar)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.user),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(86.dp)
                            .width(86.dp)
                            .clip(RoundedCornerShape(100.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically, // Center items vertically
                    ) {
                        if (advisorDes != null) {
                            advisorDes.name?.let {
                                Text(
                                    text = it,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    fontSize = 14.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(110.dp))
                        if (advisorDes != null) {
                            advisorDes.advisorStatus?.let { statusBox(status = it) }
                        }
                    }
                    Row(modifier = Modifier.padding(4.dp)) {
                        if (advisorDes != null) {
                            advisorDes.rateCount?.let {
                                RatingBar(
                                    rating = it,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(10.dp)
                                        .align(Alignment.CenterVertically)

                                )
                            }
                        }
                        if (advisorDes != null) {
                            Text(
                                text = advisorDes.totalRate.toString(), fontSize = 9.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 4.dp)
                            )
                        }
                    }
                    if (advisorDes != null) {
                        advisorDes.mobile?.let {
                            Text(
                                it,
                                fontSize = 12.sp,
                                color = AppBlue,
                                modifier = Modifier.padding(4.dp)

                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {


                if (advisorDes != null) {
                    advisorDes.bio?.let {
                        Text(
                            text = it, textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 16.dp),
                            //                    .align(Alignment.Start),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light, // Use fontWeight instead of FontStyle
                            fontStyle = FontStyle.Normal // Use fontStyle instead of FontStyle
                        )
                    }
                }

                Row(modifier = Modifier.align(Alignment.BottomCenter)) {

                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 8.dp, bottom = 24.dp)
                            .padding(),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(color = LightBlue, width = 1.dp),
                        onClick = {
                            Toast.makeText(context, message, duration).show()
                        },
                    ) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = LightBlue
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("طلب استفسار", color = LightBlue)
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp, start = 8.dp, bottom = 24.dp),
                        onClick = {
                            Toast.makeText(context, message, duration).show()
                        },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = LightBlue
                        )
                    ) {
                        Text("حجز ألان", color = Color.White)
                    }


                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecondBox(
    modifier: Modifier,
    apiServiceModel: ApiServiceModel,
    categories: ArrayList<Categories>
) {
    ScreenCard(modifier = modifier) {
        Column {
            TopSecondScreen()
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                itemsIndexed(categories) { index, category ->
                    contentSecondBox(index + 1, data = category)
                }
            }
        }
    }
}

@Composable
fun TopSecondScreen() {
    CircularCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        // First Circle - White
        CircularCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            backgroundColor = grayBtn
        ) {
            // Inner Circle - Blue
            CircularCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
//                    .height(90.dp)
//                    .width(150.dp),
                backgroundColor = Color.White
            ) {
                Text(
                    text = "الخدمات المقدمة",
                    color = LightBlue,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun contentSecondBox(num: Int, data: Categories) {
    Row() {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.image)
                    .crossfade(true)
//                    .colorSpace(Col.White)
                    .build(),
                placeholder = painterResource(R.drawable.user),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(56.dp),
//                colorFilter = Color.Red

            )
            data.name?.let { Text(text = it, color = Color.Black, fontSize = 12.sp) }
        }
        if (num % 3 != 0) {
            Spacer(
                modifier = Modifier
                    .height(61.dp)
                    .width(2.dp)
            )

        }
    }
}

@Composable
fun ScreenCard(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White, CircleShape),
        shape = RoundedCornerShape(24.dp),

        ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            ServiceProviderDesScreen()
        }
    }
}