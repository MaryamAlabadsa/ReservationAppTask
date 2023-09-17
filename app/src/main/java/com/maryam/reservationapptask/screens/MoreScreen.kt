package com.maryam.reservationapptask.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myallwork.ViewModel.ApiServiceModel
import com.maryam.reservationapptask.R
import com.maryam.reservationapptask.model.Request.ProfileRequest
import com.maryam.reservationapptask.ui.theme.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


@Composable
fun MoreScreen(
    apiServiceModel: ApiServiceModel
) {
    var context = LocalContext.current
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var mobile by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.more_bac),
                contentDescription = "",
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop,

                )
            Column(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "إعدادات الحساب",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier
//                    .align(Alignment.TopCenter)
                        .padding(top = 24.dp),
                )
                Box(
                    modifier = Modifier
                        .clickable {
                            launcher.launch("image/*")
                        }
                        .padding(top = 80.dp)
                ) {
                    if (imageUri == null) {
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(86.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .align(Alignment.TopCenter)

                        )
                    } else {
                        imageUri?.let {
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap.value = MediaStore.Images
                                    .Media.getBitmap(context.contentResolver, it)

                            } else {
                                val source = ImageDecoder
                                    .createSource(context.contentResolver, it)
                                bitmap.value = ImageDecoder.decodeBitmap(source)
                            }

                            bitmap.value?.let { btm ->
                                Image(
                                    bitmap = btm.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(86.dp)
                                        .clip(RoundedCornerShape(100.dp))
                                        .align(Alignment.TopCenter)
                                )

                            }
                        }

                    }

                    Image(
                        painter = painterResource(id = R.drawable.update_profile),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)

//                    .clip(RoundedCornerShape(100.dp))
                            .align(Alignment.BottomStart)
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(Color.White, CircleShape),
                    shape = RoundedCornerShape(24.dp),

                    ) {
                    Column() {
                        Text(
                            text = "المعلومات الشخصية",
                            color = grayText,
                            modifier = Modifier.padding(top = 36.dp, start = 16.dp, bottom = 16.dp)
                        )
                        Text(
                            text = "الاسم كاملاً ",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        TextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(vertical = 16.dp, horizontal = 16.dp),
                            placeholder = {
                                Text(
                                    text = "أدخل الاسم كاملاً",
                                    fontSize = 14.sp,
                                    color = grayText
                                )
                            },
                        )
                        Text(
                            text = "رقم الجوال ", modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        TextField(
                            value = mobile,
                            onValueChange = {
                                mobile = it
                            },
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(vertical = 16.dp, horizontal = 16.dp),
                            placeholder = {
                                Text(
                                    text = "+966 000 0000 00 ً",
                                    fontSize = 14.sp,
                                    color = grayText
                                )
                            },
                        )

                        Text(
                            text = "البريد الالكتروني  ",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(vertical = 16.dp, horizontal = 16.dp),
                            placeholder = {
                                Text(
                                    text = "example@example.comً",
                                    fontSize = 14.sp,
                                    color = grayText
                                )
                            },
                        )

                        Button(
                            modifier = Modifier
//                                .weight(1f)
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(end = 48.dp, start = 48.dp, top = 16.dp, bottom = 16.dp),
                            onClick = {
                                if (name.text.isEmpty() || mobile.text.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "تأمد من تعبئة جميع الحقول",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else if (mobile.text.length != 9) {
                                    Toast.makeText(
                                        context,
                                        "رقم الهاتف يجب ان يكون 9 ارقام",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
//                                    val file = File(Environment.getExternalStorageDirectory().toString() + File.separator)
//                                    file.createNewFile()
//
//                                    // Convert bitmap to byte array
//                                    val baos = ByteArrayOutputStream()
//                                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos) // It can be also saved as JPEG
//                                    convertBitmapToFile(context,bitmap)
//                                    val part = MultipartBody.Part.createFormData(
//                                        "pic", "myPic", RequestBody.create(
//                                            "image/*".toMediaTypeOrNull(),
//                                            inputStream.readBytes()
//                                        )
//                                    )

                                    var profileRequest = ProfileRequest(
                                        name = name.text,
                                        email = email.text,
                                        mobile = mobile.text,
                                    )
                                    apiServiceModel.updateProfile(
                                        context,
                                        profileRequest = profileRequest
                                    )
                                }

//                                Toast.makeText(context, message, duration).show()
                            },
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = LightBlue
                            )
                        ) {
                            Text(" حفظ", color = Color.White)
                        }
                    }

                }

            }


        }
    }
}
fun convertBitmapToFile(context: Context, bitmap: Bitmap): Uri {
    val file = File(Environment.getExternalStorageDirectory().toString() + File.separator)
    file.createNewFile()

    // Convert bitmap to byte array
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos) // It can be also saved as JPEG
    val bitmapdata = baos.toByteArray()

    // Write the bitmap data to the file
    val fos = FileOutputStream(file)
    fos.write(bitmapdata)
    fos.flush()
    fos.close()

    // Return the Uri of the saved file
    return Uri.fromFile(file)
}

//fun uriToPainter(context: Context, uri: Uri): Painter {
//    val file = File(uri.toString()) // Convert URI to File if needed
//    // Load the image and return a Painter
//    return painterResource(id = R.drawable.ic_launcher_foreground)
//}
@Preview(showBackground = true)
@Composable
fun DefaultPreview22() {
    ReservationAppTaskTheme {
        Column(modifier = Modifier.fillMaxSize()) {
//            MoreScreen()
        }
    }
}