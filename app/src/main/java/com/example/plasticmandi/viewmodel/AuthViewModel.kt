package com.example.plasticmandi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plasticmandi.model.User
import com.example.plasticmandi.model.request.LoginRequest
import com.example.plasticmandi.model.request.OtpRequest
import com.example.plasticmandi.model.response.OtpResponse
import com.example.plasticmandi.repository.AuthRepository
import com.example.plasticmandi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

     val sendOtpResponse: MutableLiveData<Resource<OtpResponse>> = MutableLiveData()

    fun sendOtp(otpRequest: OtpRequest) =
        viewModelScope.launch {
            sendOtpResponse.postValue(Resource.Loading())
            val response :  Response<OtpResponse> = repository.sendOtp(otpRequest)
            sendOtpResponse.postValue(handleOtpResponse(response))

        }

    private fun handleOtpResponse(response: Response<OtpResponse>): Resource<OtpResponse> {
        if (response.isSuccessful)
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse,response.code())
            }
        return Resource.Error(response.message(),response.code())

    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            repository.login(loginRequest)
        }
    }

    fun setUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUser(user)
        }
    }
}