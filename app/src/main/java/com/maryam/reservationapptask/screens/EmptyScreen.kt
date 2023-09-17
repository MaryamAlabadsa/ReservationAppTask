package com.maryam.reservationapptask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.items.ScreenHeaderItem
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme

@Composable
fun EmptyScreen(title: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScreenHeaderItem(Title = title)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.empty_screen),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().padding(top = 77.dp)
            )
            Text(
                text = stringResource(id = R.string.empty_screen_first_text),
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                fontSize = 30.sp
            )
            Text(
                text = stringResource(id = R.string.empty_screen_second_text),
                fontWeight = FontWeight.Light,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            EmptyScreen(R.string.app_name)
        }
    }
}