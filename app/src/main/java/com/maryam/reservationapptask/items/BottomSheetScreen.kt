package com.maryam.reservationapptask.items

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.model.Request.ComplaintRequest
import com.maryam.reservationapptask.ui.theme.LightBlue
import com.maryam.reservationapptask.ui.theme.ReservationAppTaskTheme
import com.maryam.reservationapptask.ui.theme.yellowStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContent(
    coroutineScope: CoroutineScope,
    modalSheetState: ModalBottomSheetState,
    apiServiceModel: ApiServiceModel,
    advisorId: Int
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var context = LocalContext.current
    Column(
//        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch { modalSheetState.hide() }
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
            }
            Text(
                text = "إبلاغ عن موظف",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(
                    Alignment.TopCenter
                ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Service Description",
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 16.dp)
            )
            Text(
                text = "صديقنا",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = yellowStatus,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = " اخبرنا سبب عدم رضاك وسنعمل علي حل المشكلة ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }
        Spacer(modifier = Modifier.height(38.dp))

        Column() {
            Text(
                text = " نص الشكوى ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                placeholder = { Text(text = "أكتب نص الشكوى هنا") },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(70.dp)
                    .padding(end = 48.dp, start = 48.dp, bottom = 24.dp),
                onClick = {
                    var complaintRequest=ComplaintRequest(advisorId, text.text )
                    apiServiceModel.makeComplaint(context,complaintRequest)
                    coroutineScope.launch { modalSheetState.hide() }
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightBlue
                )
            ) {
                Text("إرسال ", color = Color.White)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            SheetContent(coroutineScope, modalSheetState)
        }
    }
}