package com.maryam.reservationapptask.model.Request

import com.google.gson.annotations.SerializedName

data class ComplaintRequest (
    @SerializedName("advisor_id")
    val advisor_id: Number,

    @SerializedName("text")
    val text: String,

    )