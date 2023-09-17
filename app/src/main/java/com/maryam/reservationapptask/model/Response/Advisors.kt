package com.maryam.reservationapptask.model.Response

import com.google.gson.annotations.SerializedName
import com.maryam.reservationapptask.model.BaseResponse

data class Advisors(
    @SerializedName("advisors") var advisors: ArrayList<AdvisorsData> = arrayListOf()
) : BaseResponse()

data class AdvisorsData (

    @SerializedName("id"             ) var id            : Int?     = null,
    @SerializedName("name"           ) var name          : String?  = null,
    @SerializedName("mobile"         ) var mobile        : String?  = null,
    @SerializedName("country_id"     ) var countryId     : Int?     = null,
    @SerializedName("avatar"         ) var avatar        : String?  = null,
    @SerializedName("bio"            ) var bio           : String?  = null,
    @SerializedName("rate_count"     ) var rateCount     : Int?     = null,
    @SerializedName("total_rate"     ) var totalRate     : Double?  = null,
    @SerializedName("advisor_status" ) var advisorStatus : String?  = null,
    @SerializedName("is_fav"         ) var isFav         : Int?     = null,
    @SerializedName("device_type"    ) var deviceType    : String?  = null,
    @SerializedName("is_logged_out"  ) var isLoggedOut   : Int?     = null,
    @SerializedName("is_features"    ) var isFeatures    : Boolean? = null,
    @SerializedName("is_pined"       ) var isPined       : Int?     = null,
    @SerializedName("iban"           ) var iban          : String?  = null,
    @SerializedName("identity"       ) var identity      : String?  = null,
    @SerializedName("can_chat"       ) var canChat       : Int?     = null

)