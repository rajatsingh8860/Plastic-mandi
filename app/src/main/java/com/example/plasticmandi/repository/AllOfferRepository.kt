package com.example.plasticmandi.repository

import com.example.plasticmandi.RetrofitInstance

class AllOfferRepository {

    suspend fun getAllOffers() =
        RetrofitInstance.allOfferApi.getAllOffers()

}