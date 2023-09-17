package com.maryam.reservationapptask.model.Request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Part

data class ProfileRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("country_id")
    val country_id: Number=1,

//    @SerializedName("gender")
//    val gender: String,

//    @SerializedName("avatar")
//    val avatar: Number,

    )