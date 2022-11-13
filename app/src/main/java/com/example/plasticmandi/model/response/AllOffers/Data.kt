package com.example.plasticmandi.model.response.AllOffers

data class AllOffers(
    val code: String,
    val expireStatus: String,
    val id: String,
    val pickupLocation: String,
    val price: Double,
    val productDetails: ProductDetails,
    val quantity: Int,
    val referenceId: String,
    val status: Int,
    val validTill: String
)