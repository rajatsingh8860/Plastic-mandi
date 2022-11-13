package com.example.plasticmandi.model.response.VerifyOtp

data class CompanyDetails(
    val accountantName: String,
    val accountantPhone: String,
    val address: String,
    val cin: String,
    val dateOfEstablishment: String,
    val gstNo: String,
    val id: String,
    val managerName: String,
    val managerPhone: String,
    val name: String,
    val pan: String,
    val pinCode: Int,
    val shippingDeliveryAddress: String,
    val status: Int,
    val tan: String
)