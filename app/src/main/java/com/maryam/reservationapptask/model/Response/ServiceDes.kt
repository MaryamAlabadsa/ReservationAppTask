package com.maryam.reservationapptask.model.Response

import com.google.gson.annotations.SerializedName
import com.maryam.reservationapptask.model.BaseResponse

data class ServiceDes(
    @SerializedName("services") var services: ArrayList<Service> = arrayListOf()
) : BaseResponse()