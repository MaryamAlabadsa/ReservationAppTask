package com.maryam.reservationapptask.model.Response

import com.google.gson.annotations.SerializedName
import com.maryam.reservationapptask.model.BaseResponse

data class ProfileResponse(
    @SerializedName("returned") var returned        : String?  = null
):BaseResponse()
