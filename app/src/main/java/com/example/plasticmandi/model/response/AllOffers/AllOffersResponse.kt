package com.example.plasticmandi.model.response.AllOffers

data class AllOffersResponse(
    val code: Int,
    val `data`: List<AllOffers>,
    val meta: Meta,
    val success: Boolean
)