package com.maryam.reservationapptask.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.model.Response.Service
import com.maryam.reservationapptask.ui.theme.LightBlueBorder
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme

@Composable
fun ServiceDesItem(data: Service, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp, start = 2.dp, end = 2.dp, bottom = 7.dp)
            .clickable() {
                onClick()
            }
            .border(
                width = 1.dp,
                color = LightBlueBorder,
                shape = RoundedCornerShape(38.dp)
            ),
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "Service Description",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(start = 8.dp)
                        .align(alignment = Alignment.CenterStart),
                    alignment = Alignment.Center,
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(LightBlueBorder)
                )
                data.name?.let {
                    Text(
                        text = it,
                        color = Color.Black,
                        fontSize = 12.sp,
                        //                    textAlign = TextAlign.,
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .align(Alignment.CenterStart)
                    )
                }
                Text(
                    text = "${data.price} ريال", // Use ${} to concatenate the price with " ريال "
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(end = 38.dp)
                        .align(Alignment.CenterEnd)
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "ArrowBack Icon",
                    modifier = Modifier
                        .size(34.dp)
                        .padding(end = 16.dp)
                        .align(alignment = Alignment.CenterEnd),
                    tint = LightBlueBorder
                )
            }
        }

    }
}

@Composable
fun DotSpacer() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(2.dp) // Adjust the height of the divider line
        )
//        Divider(
//            color = Color.Black, // Dot color
//            thickness = 2.dp, // Adjust the thickness of the divider line
//        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(2.dp) // Adjust the height of the divider line
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            ServiceDesItem(item)
        }
    }
}