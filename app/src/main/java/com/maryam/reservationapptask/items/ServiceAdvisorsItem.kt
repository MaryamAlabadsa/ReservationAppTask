package com.maryam.reservationapptask.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.model.Response.AdvisorsData
import com.maryam.reservationapptask.model.Response.Service
import com.maryam.reservationapptask.nav.ScreensHolder
import com.maryam.reservationapptask.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ServiceAdvisorsItem(data: AdvisorsData, onClick: () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(24.dp),
            backgroundColor = Color.White,

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data.avatar)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.user),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(70.dp)
                            .width(86.dp)
                            .clip(RoundedCornerShape(24.dp))
//                            .weight(1f)

                    )
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(start = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Center items vertically
                        ) {
                            data.name?.let {
                                Text(
                                    text = it,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    fontSize = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(68.dp))
                            data.advisorStatus?.let { statusBox(status = it) }
                            Spacer(modifier = Modifier.width(14.dp))
                            Card(
                                shape = RoundedCornerShape(44.dp),
                                backgroundColor = blueIconFav
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "ArrowBack Icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(3.dp),
                                    tint = grayIcon
                                )
                            }
                        }
                        Row {
                            data.rateCount?.let {
                                RatingBar(
                                    rating = it,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(10.dp)
                                        .align(Alignment.CenterVertically)

                                )
                            }
                            Text(
                                text = data.totalRate.toString(),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 4.dp)
                            )
                            Row(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "(", fontSize = 9.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 4.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "ArrowBack Icon",
                                    modifier = Modifier
                                        .size(12.dp)
                                        .padding(1.dp),
                                    tint = grayIcon
                                )
                                Text(
                                    text = "${data.totalRate})", fontSize = 9.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 1.dp)
                                )
                            }
//                            TextWithIcon()
                        }
                        data.bio?.let {
                            Text(
                                text = it,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                Box(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.align(Alignment.CenterStart), // You might want to use fillMaxWidth to occupy the entire row
                    ) {

                        if (data.isFav == 1) {
                            Image(
                                painter = painterResource(id = R.drawable.fav),
                                contentDescription = "Service Description",
                                modifier = Modifier
                                    .size(34.dp)
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterVertically), // Align the Icon to the center vertically in the Row

                            )
                            Text(
                                text = "مميز",
                                color = red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterVertically), // Align the Icon to the center vertically in the Row

                            )
                        }


                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "ArrowBack Icon",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd), // Align the Icon to the center vertically in the Row
                        tint = grayIcon
                    )
                }

            }
        }
    }
}

@Composable
fun statusBox(status: String) {
    var backgroundColor: Color
    var textColor: Color
    var dotColor: Color
    var text: String
    if (status == "active") {
        backgroundColor = grayStatus
        textColor = Color.Black
        dotColor = greenDot
        text = "نشط"
    } else if (status == "inactive") {
        backgroundColor = grayStatus
        textColor = Color.Black
        dotColor = grayDot
        text = "بالخارج"
    } else {
        backgroundColor = yellowStatus
        textColor = Color.Black
        dotColor = yellowDot
        text = "مشغول"

    }

    Card(
        modifier = Modifier
            .height(20.dp)
            .width(70.dp),
//            .align(Alignment.CenterVertically),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .height(5.dp)
                    .width(5.dp),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = dotColor
            ) {}
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = text,
                color = textColor,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
//                    .align(Alignment.Center)
            )
        }
    }
}

//@Composable
//fun TextWithIcon() {
//    buildAnnotatedString {
//        append('(')
//        append(Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = ""))
//        append("3)")
//    }
//}
@Composable
fun TextWithIcon() {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        append("(02")
        // Append a placeholder string "[icon]" and attach an annotation "inlineContent" on it.
        appendInlineContent(myId, "[icon]")
        append(")")

    }

    val inlineContent = mapOf(
        Pair(
            // This tells the [CoreText] to replace the placeholder string "[icon]" by
            // the composable given in the [InlineTextContent] object.
            myId,
            InlineTextContent(
                // Placeholder tells text layout the expected size and vertical alignment of
                // children composable.
                Placeholder(
                    width = 12.sp,
                    height = 12.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                )
            ) {
                // This Icon will fill maximum size, which is specified by the [Placeholder]
                // above. Notice the width and height in [Placeholder] are specified in TextUnit,
                // and are converted into pixel by text layout.

                Icon(Icons.Filled.Person, "", tint = Color.Red)
            }
        )
    )

    Text(
        text = text,
        modifier = Modifier.width(100.dp),
        inlineContent = inlineContent
    )
}

// Use the Text composable to display the annotated string
//    androidx.compose.foundation.text.Text(
//        text = annotatedString
//    )
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            ServiceProviderItem(item) {
//                navController.navigate(
//                    "${ScreensHolder.ServicesDes.route}/${category.id}/${item.id}"
//                )
//            }
        }
    }
}