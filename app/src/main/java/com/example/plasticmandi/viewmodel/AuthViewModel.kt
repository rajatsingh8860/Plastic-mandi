package com.example.plasticmandi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plasticmandi.model.User
import com.example.plasticmandi.model.request.LoginRequest
import com.example.plasticmandi.model.request.OtpRequest
import com.example.plasticmandi.model.response.OtpResponse
import com.example.plasticmandi.model.response.VerifyOtp.VerifyOtpResponse
import com.example.plasticmandi.repository.AuthRepository
import com.example.plasticmandi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val sendOtpResponse: MutableLiveData<Resource<OtpResponse>> = MutableLiveData()
    val verifyOtpResponse: MutableLiveData<Resource<VerifyOtpResponse>> = MutableLiveData()

    fun sendOtp(otpRequest: OtpRequest) =
        viewModelScope.launch {
            sendOtpResponse.postValue(Resource.Loading())
            val response: Response<OtpResponse> = repository.sendOtp(otpRequest)
            if (response.isSuccessful)
                response.body()?.let { resultResponse ->
                    sendOtpResponse.postValue(Resource.Success(resultResponse, response.code()))
                }
            else {
                sendOtpResponse.postValue(Resource.Error(response.message(), response.code()))
            }

    }


    private fun setUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUser(user)
        }
    }

    fun login(otp: Int, phoneNumber: String) {
        viewModelScope.launch {
            sendOtpResponse.postValue(Resource.Loading())
            val response =
                repository.login(LoginRequest(phoneNumber, otp))
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    setUser(User(
                        firstName = resultResponse.data.firstName,
                        lastName = resultResponse.data.lastName,
                        code = resultResponse.data.id,
                        phone = resultResponse.data.mobile,
                        email = resultResponse.data.email,
                        accessToken = resultResponse.data.accessToken
                    ))
                    verifyOtpResponse.postValue(Resource.Success(resultResponse, response.code()))

                }
            } else {
                verifyOtpResponse.postValue(Resource.Error(response.message(), response.code()))
            }
        }
    }
}