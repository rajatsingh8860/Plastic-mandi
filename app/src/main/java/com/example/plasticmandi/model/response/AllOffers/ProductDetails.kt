package com.example.plasticmandi.model.response.AllOffers

data class ProductDetails(
    val company: Company,
    val grade: String,
    val id: String,
    val mfi: Double,
    val plastic: Plastic,
    val status: Int
)