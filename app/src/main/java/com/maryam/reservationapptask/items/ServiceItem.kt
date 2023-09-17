package com.maryam.reservationapptask.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.ui.theme.LightBlue
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme
import com.maryam.reservationapptask.ui.theme.gray


@Composable
fun ServiceItem(text: String?, icon: String?, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onClick()
        },
    ) {
        if (icon != null) {
            TwoCircles(90, 80, icon)
        }
        if (text != null) {
            Text(
                text = text,
                color = Color.Black,
                modifier = Modifier
                    .align(CenterHorizontally),
                fontFamily = FontFamily.Serif,
                fontSize = 12.sp
            )
        }
    }

}

@Composable
fun TwoCircles(first: Int, second: Int, icon: String) {
    Column(
        modifier = Modifier
            .padding(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularCard(
            modifier = Modifier
                .size((first + 10).dp)
        ) {
            // First Circle - White
            CircularCard(
                modifier = Modifier
                    .size(first.dp),
                backgroundColor = Color.White
            ) {
                // Inner Circle - Blue
                CircularGradientCard(
                    modifier = Modifier
                        .size(second.dp),
                    backgroundColor = linear
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(icon)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.user),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)

                    )

                }
            }
        }


    }
}

@Composable
fun CircularCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,

    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun CircularGradientCard(
    modifier: Modifier = Modifier,
    backgroundColor: Brush,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}


val linear = Brush.linearGradient(
    0.0f to Color(0x5936A9E1), // LightBlue with alpha (e.g., 0xFF for full opacity)
    500.0f to Color.White,
    start = Offset.Zero,
    end = Offset.Infinite
)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            ServiceItem()
        }
    }
}