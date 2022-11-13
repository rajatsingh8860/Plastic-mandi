package com.example.plasticmandi.model.response.AllOffers

data class Pagination(
    val currentPage: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int
)