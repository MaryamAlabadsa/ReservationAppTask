package com.maryam.reservationapptask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//import kotlinx.parcelize.Parcelize


//@Parcelize
open class BaseResponse(
    @SerializedName("status"           ) var status          : Boolean?              = null,
    @SerializedName("response_message" ) var responseMessage : String?               = null,
)
//    : Parcelable