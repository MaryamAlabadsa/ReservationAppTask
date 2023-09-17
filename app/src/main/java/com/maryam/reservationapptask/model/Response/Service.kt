package com.maryam.reservationapptask.model.Response

import com.google.gson.annotations.SerializedName
import com.maryam.reservationapptask.model.BaseResponse

data class Services (
    @SerializedName("categories")
    var categories: ArrayList<Categories> = arrayListOf()
): BaseResponse()

data class Categories(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("parent") var parent: String? = null,
    @SerializedName("services") var services: ArrayList<Service> = arrayListOf()
)
data class Service(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("category_id") var categoryId: Int? = null,
    @SerializedName("price") var price: Int? = null

)