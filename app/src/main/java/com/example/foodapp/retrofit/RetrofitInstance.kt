package com.example.foodapp.retrofit

import com.example.foodapp.util.AppString
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val  api:MealApi by lazy {
        Retrofit.Builder().baseUrl(AppString.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealApi::class.java)

   }
}