package com.example.plasticmandi.model.response.VerifyOtp

data class Data(
    val accessToken: String,
    val address: String,
    val adhaar: String,
    val attachments: List<Attachment>,
    val bankDetails: List<BankDetail>,
    val code: String,
    val companyDetails: CompanyDetails,
    val email: String,
    val firstName: String,
    val id: String,
    val isEmailVerified: Boolean,
    val isMobileVerified: Boolean,
    val lastName: String,
    val mobile: String,
    val pan: String,
    val role: Int,
    val status: Int
)