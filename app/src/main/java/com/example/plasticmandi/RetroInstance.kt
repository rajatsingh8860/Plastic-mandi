package com.example.plasticmandi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
        private const val BASE_URL = "https://api.plastic-mandi-services.squareboat.info/api/v1/"

        fun getRetroInstance(): Retrofit {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
        }
    }
