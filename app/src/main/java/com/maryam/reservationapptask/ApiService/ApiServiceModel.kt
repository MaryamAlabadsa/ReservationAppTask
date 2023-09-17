package com.example.myallwork.ViewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myallwork.ApiService.ApiService
import com.maryam.reservationapptask.model.Request.ComplaintRequest
import com.maryam.reservationapptask.model.Request.ProfileRequest
import com.maryam.reservationapptask.model.Response.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody


class ApiServiceModel : ViewModel() {

    var liveServicesData by mutableStateOf(Services())
    var liveAdvisorsDesData by mutableStateOf(AdvisorDes())
    var liveComplaintData by mutableStateOf(ComplaintResponse())
    var liveServicesDesData: List<Service> by mutableStateOf(listOf())
    var liveCategoriesData: List<Categories> by mutableStateOf(listOf())
    var liveAdvisorsData: List<AdvisorsData> by mutableStateOf(listOf())

    fun getService(
        context: Context,
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val allWorkResponse = apiService.getServices()
                if (allWorkResponse.status == true) {
                    liveServicesData = allWorkResponse
                    liveCategoriesData = allWorkResponse.categories
                } else {
                    Toast.makeText(context, allWorkResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show();
                    Log.e("errormmmm", allWorkResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun getServiceDes(
        context: Context,
        data: Int
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val allWorkResponse = apiService.getServicesDes(data)
                if (allWorkResponse.status == true) {
                    liveServicesDesData = allWorkResponse.services
                } else {
                    Toast.makeText(context, allWorkResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show();
                    Log.e("errormmmm", allWorkResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun searchAdvisors(
        context: Context,
        categoryId: Int,
        serviceId: Int
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val userResponse = apiService.searchAdvisors(categoryId, serviceId)
                if (userResponse.status == true) {
                    liveAdvisorsData = userResponse.advisors
                } else {
                    Toast.makeText(context, userResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show();
                    Log.e("errormmmm", userResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun getAdvisorDetails(
        context: Context,
        advisorId: Int
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val userResponse = apiService.getAdvisorDetails(advisorId)
                if (userResponse.status == true) {
                    liveAdvisorsDesData = userResponse
                } else {
                    Toast.makeText(context, userResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show();
                    Log.e("errormmmm", userResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun makeComplaint(
        context: Context,
        complaintRequest: ComplaintRequest
//        advisorId: Int
    ) {
        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val userResponse = apiService.makeComplaint(complaintRequest)
                if (userResponse.status == true) {
                    Toast.makeText(context, userResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show()

                    liveComplaintData = userResponse
                } else {
                    Toast.makeText(context, userResponse.responseMessage + "", Toast.LENGTH_LONG)
                        .show()
                    Log.e("errormmmm", userResponse.toString())
                }
            } catch (e: Exception) {
                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun updateProfile(
        context: Context,
        profileRequest: ProfileRequest,
        imagePart: MultipartBody.Part?
    ) {
        val name = RequestBody.create("text/plain".toMediaTypeOrNull(), profileRequest.name)
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), profileRequest.email)
        val mobile = RequestBody.create("text/plain".toMediaTypeOrNull(), profileRequest.mobile)
        val countryId = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            profileRequest.country_id.toString()
        )

        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()
                val userResponse = imagePart?.let {
                    apiService.profileUpdate(
                        name,
                        email,
                        mobile,
                        countryId,
                        it
                    )
                }
                if (userResponse != null) {
                    if (userResponse.status == true) {
                        //                    liveAdvisorsDesData = userResponse
                        Toast.makeText(
                            context,
                            userResponse.responseMessage + "",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.e("errors2", userResponse.toString())

                    } else {
                        Toast.makeText(context, "error"+ "", Toast.LENGTH_LONG).show();
                        Toast.makeText(context, userResponse.responseMessage + "", Toast.LENGTH_LONG).show();
                        Log.e("errors", userResponse.toString())
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "error22"+ "", Toast.LENGTH_LONG).show();

                // Handle exception if any error occurs during the API request
                Toast.makeText(context, e.message + "", Toast.LENGTH_LONG).show();
                Log.e("errormmmm", e.message.toString())

            }
        }
    }


}
