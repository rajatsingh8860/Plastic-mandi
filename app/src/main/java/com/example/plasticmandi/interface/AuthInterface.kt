package com.example.plasticmandi.`interface`

import com.example.plasticmandi.request.LoginRequest
import com.example.plasticmandi.request.OtpRequest
import com.example.plasticmandi.response.OtpResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthInterface {

    @POST("app/auth/signin")
    @Headers("api-secret:pm@12345")
    suspend fun sendOtp(@Body params: OtpRequest) : Response<OtpResponse>

    @POST("app/auth/verify-otp")
    @Headers("api-secret:pm@12345")
    suspend fun login(@Body params: LoginRequest) : Response<OtpResponse>

}