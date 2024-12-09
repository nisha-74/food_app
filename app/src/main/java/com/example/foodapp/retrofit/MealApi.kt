package com.example.foodapp.retrofit

import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.model.CategoryMealList
import com.example.foodapp.model.FilterCategoryMealList
import com.example.foodapp.model.PopularItemMealList
import com.example.foodapp.model.RandomMealModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<RandomMealModel>
    @GET("lookup.php?")
    fun getRandomMealDetail(@Query("i")id:String):Call<RandomMealModel>

    @GET("filter.php?")
    fun getPopularItemMeal(@Query("c")categoryName:String):Call<PopularItemMealList>

    @GET("categories.php")
    fun  getCategoryMeal():Call<CategoryMealList>

    @GET("filter.php")
    fun getMealByCategoryMeal(@Query("c")categoryName:String):Call<FilterCategoryMealList>

}