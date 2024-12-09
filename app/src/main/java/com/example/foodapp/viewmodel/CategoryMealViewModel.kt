package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.model.CategoryMealList
import com.example.foodapp.model.FilterCategoryMealList
import com.example.foodapp.model.FilterMeal
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealViewModel() :ViewModel() {
    val mealLiveData = MutableLiveData<List<FilterMeal>>()
    fun getFilterMealByCategory(categoryName:String){
        RetrofitInstance.api.getMealByCategoryMeal(categoryName).enqueue(object : Callback<FilterCategoryMealList>{
            override fun onResponse(
                call: Call<FilterCategoryMealList>,
                response: Response<FilterCategoryMealList>
            ) {
                response.body().let { mealList ->

                            mealLiveData.postValue(mealList!!.meals)
                        }
            }

            override fun onFailure(call: Call<FilterCategoryMealList>, t: Throwable) {
                Log.d("Category By Id ", t.toString())
            }

        })
    }




    fun observerCategoryByLiveData(): LiveData<List<FilterMeal>> {
        return mealLiveData

    }




}

