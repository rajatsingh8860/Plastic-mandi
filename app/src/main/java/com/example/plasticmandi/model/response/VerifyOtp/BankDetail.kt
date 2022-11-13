package com.example.plasticmandi.model.response.VerifyOtp

data class BankDetail(
    val accountHolderName: String,
    val accountNo: String,
    val bankName: String,
    val branchName: String,
    val id: String,
    val ifscCode: String,
    val status: Int
)