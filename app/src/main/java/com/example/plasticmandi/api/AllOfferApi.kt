package com.example.plasticmandi.api
import com.example.plasticmandi.model.response.AllOffers.AllOffersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AllOfferApi {
    @GET("app/offers?sort=createdAt:desc")
    @Headers("api-secret:pm@12345","Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoiZjc4ZDRlOTgtYTBmNC00NzIwLWIxZDgtNDZkMDMwY2I0ZjAzIiwibGFzdExvZ2luQXQiOiIyMDIyLTEwLTI3VDE3OjM2OjI0LjAwMFoiLCJpYXQiOjE2NjY4OTIxODQsImV4cCI6MTY5ODQyODE4NH0.xsMlrENDOrSI2nzhU-CsrEhuf8tgb1gDNufV7Ns0PkM")
    suspend fun getAllOffers() : Response<AllOffersResponse>
}