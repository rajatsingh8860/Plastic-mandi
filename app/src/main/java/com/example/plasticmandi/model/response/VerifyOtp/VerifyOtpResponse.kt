package com.example.plasticmandi.model.response.VerifyOtp


data class VerifyOtpResponse(
    val code: Int,
    val `data`: Data,
    val success: Boolean
)