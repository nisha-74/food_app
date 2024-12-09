package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.model.CategoryMealList
import com.example.foodapp.model.PopularItemMealList
import com.example.foodapp.model.PopularItemMeal
import com.example.foodapp.model.Meal
import com.example.foodapp.model.RandomMealModel
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private  var randomMealLiveData=MutableLiveData<Meal>();
    private  val  popularPopularItemMealLiveData=MutableLiveData<List<PopularItemMeal>>()
    private  val categoryMealLiveData= MutableLiveData<List<CategoryMeal>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<RandomMealModel> {
            override fun onResponse(
                call: Call<RandomMealModel>,
                response: Response<RandomMealModel>
            ) {
                if(response.body()!=null){
                    val randomMealModel: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value= randomMealModel

                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<RandomMealModel>, t: Throwable) {
                Log.d("TEST", t.toString())
            }

        })
    }

    fun getPopularItemMeal(){
        RetrofitInstance.api.getPopularItemMeal("Seafood").enqueue(object : Callback<PopularItemMealList> {
            override fun onResponse(call: Call<PopularItemMealList>, response: Response<PopularItemMealList>) {
                if(response.body()!=null){

                    popularPopularItemMealLiveData.value=response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<PopularItemMealList>, t: Throwable) {
                Log.d("PopularMeal Error", t.toString())
            }

        })
    }


    fun  getCategoryMeal(){
        RetrofitInstance.api.getCategoryMeal().enqueue(object :Callback<CategoryMealList>{
            override fun onResponse(
                call: Call<CategoryMealList>,
                response: Response<CategoryMealList>
            ) {
                response.body()?.let { categoryList ->
                    categoryMealLiveData.postValue(categoryList.categories)
                }
            }
            override fun onFailure(call: Call<CategoryMealList>, t: Throwable) {
               Log.d("CategoryMeal ", t.toString())
            }

        })
    }




    fun observerRandomMealLiveData():LiveData<Meal>
    {
        return  randomMealLiveData
    }

    fun observerPopularItemMealData():LiveData<List<PopularItemMeal>>{
        return popularPopularItemMealLiveData
    }
    fun  observerCategoryMealData():LiveData<List<CategoryMeal>>{
        return  categoryMealLiveData
    }

}