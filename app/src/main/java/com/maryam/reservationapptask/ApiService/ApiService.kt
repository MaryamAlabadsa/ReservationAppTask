package com.example.myallwork.ApiService

import com.maryam.reservationapptask.model.Request.ComplaintRequest
import com.maryam.reservationapptask.model.Request.ProfileRequest
import com.maryam.reservationapptask.model.Response.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    //    @POST("auth/login/user")
//    suspend fun login(@Body loginRequest: LoginRequest): UserResponse
//
//    @POST("auth/register/user")
//    suspend fun register(@Body registerRequest: RegisterRequest): UserResponse
//
    @GET("general/get_categories")
    suspend fun getServices(): Services

    @GET("user/services/by_category")
    suspend fun getServicesDes(@Query("category_id") categoryId: Int): ServiceDes

    @GET("user/advisors/search")
    suspend fun searchAdvisors(
        @Query("category_id") categoryId: Int,
        @Query("service_id") serviceId: Int
    ): Advisors

    @GET("user/advisors/details")
    suspend fun getAdvisorDetails(
        @Query("advisor_id") advisorId: Int
    ): AdvisorDes

    @POST("user/advisors/make_complaint")
    suspend fun makeComplaint(@Body complaintRequest: ComplaintRequest): ComplaintResponse

    @Multipart
    @POST("user/update_profile")
    suspend fun profileUpdate(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("mobile") mobile: RequestBody,
        @Part("country_id") country_id: RequestBody,
        @Part file: MultipartBody.Part
    ): ProfileResponse


    companion object {
        var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null) {
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer 552|fFhhCtp4qkctBKmVaiHlVNjaJSG2CKxRP2gQj4RE"
                            )
                            .addHeader("Accept", "application/json")
                            .build()
                        chain.proceed(request)
                    }
                    .build()

                apiService = Retrofit.Builder()
                    .baseUrl(" https://ashal.selsela.site/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
//        https://ashal.selsela.site/api/v1/user/advisors/make_complaint

    }

}