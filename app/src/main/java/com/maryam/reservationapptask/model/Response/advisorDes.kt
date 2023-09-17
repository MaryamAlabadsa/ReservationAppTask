package com.maryam.reservationapptask.model.Response

import com.google.gson.annotations.SerializedName
import com.maryam.reservationapptask.model.BaseResponse

data class AdvisorDes(
    @SerializedName("advisor") var advisor: Advisor? = Advisor()
) : BaseResponse()

data class User (

    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("name"            ) var name           : String? = null,
    @SerializedName("email"           ) var email          : String? = null,
    @SerializedName("mobile"          ) var mobile         : String? = null,
    @SerializedName("country_id"      ) var countryId      : Int?    = null,
    @SerializedName("avatar"          ) var avatar         : String? = null,
    @SerializedName("gender"          ) var gender         : String? = null,
    @SerializedName("status"          ) var status         : Int?    = null,
    @SerializedName("activation_code" ) var activationCode : String? = null,
    @SerializedName("accessToken"     ) var accessToken    : String? = null,
    @SerializedName("device_type"     ) var deviceType     : String? = null,
    @SerializedName("is_logged_out"   ) var isLoggedOut    : Int?    = null

)
data class Rates (

    @SerializedName("id"      ) var id      : Int?    = null,
    @SerializedName("rate"    ) var rate    : Int?    = null,
    @SerializedName("comment" ) var comment : String? = null,
    @SerializedName("user"    ) var user    : User?   = User()

)

data class Advisor (

    @SerializedName("id"                        ) var id                      : Int?                  = null,
    @SerializedName("name"                      ) var name                    : String?               = null,
    @SerializedName("avatar"                    ) var avatar                  : String?               = null,
    @SerializedName("status"                    ) var status                  : Int?                  = null,
    @SerializedName("rate_count"                ) var rateCount               : Int?                  = null,
    @SerializedName("total_rate"                ) var totalRate               : Double?               = null,
    @SerializedName("advisor_status"            ) var advisorStatus           : String?               = null,
    @SerializedName("mobile"                    ) var mobile                  : String?               = null,
    @SerializedName("country_id"                ) var countryId               : Int?                  = null,
    @SerializedName("is_logged_out"             ) var isLoggedOut             : Int?                  = null,
    @SerializedName("bio"                       ) var bio                     : String?               = null,
    @SerializedName("cv"                        ) var cv                      : String?               = null,
    @SerializedName("identity"                  ) var identity                : String?               = null,
    @SerializedName("can_chat"                  ) var canChat                 : Int?                  = null,
    @SerializedName("is_fav"                    ) var isFav                   : Int?                  = null,
    @SerializedName("is_features"               ) var isFeatures              : Boolean?              = null,
    @SerializedName("non_complete_reservations" ) var nonCompleteReservations : String?               = null,
    @SerializedName("is_pined"                  ) var isPined                 : Int?                  = null,
    @SerializedName("can_now_reservation"       ) var canNowReservation       : Int?                  = null,
    @SerializedName("gender_reservation_type"   ) var genderReservationType   : String?               = null,
    @SerializedName("device_type"               ) var deviceType              : String?               = null,
    @SerializedName("rates"                     ) var rates                   : ArrayList<Rates>      = arrayListOf(),
    @SerializedName("categories"                ) var categories              : ArrayList<Categories> = arrayListOf(),
    @SerializedName("promo_code"                ) var promoCode               : String?               = null,
    @SerializedName("welcom_message"            ) var welcomMessage           : String?               = null,
    @SerializedName("whatsapp_link"             ) var whatsappLink            : String?               = null

)