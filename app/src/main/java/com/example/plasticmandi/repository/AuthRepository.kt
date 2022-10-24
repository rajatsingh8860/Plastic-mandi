package com.example.plasticmandi.repository

import com.example.plasticmandi.RetrofitInstance
import com.example.plasticmandi.api.AuthApi
import com.example.plasticmandi.db.UserDao
import com.example.plasticmandi.db.UserDatabase
import com.example.plasticmandi.model.User
import com.example.plasticmandi.model.request.LoginRequest
import com.example.plasticmandi.model.request.OtpRequest

class AuthRepository(private val db: UserDatabase) {

    suspend fun sendOtp(otpRequest: OtpRequest) =
        RetrofitInstance.authApi.sendOtp(otpRequest)


    suspend fun login(loginRequest : LoginRequest){
        RetrofitInstance.authApi.login(loginRequest)
    }

    suspend fun setUser(user : User){
        db.getUserDao().setUser(user)
    }

}