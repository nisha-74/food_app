package com.example.foodapp.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.model.Meal
import com.example.foodapp.model.RandomMealModel
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(

):ViewModel() {

    private var  mealDetailLiveData= MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getRandomMealDetail(id).enqueue(object : Callback<RandomMealModel> {
            override fun onResponse(
                call: Call<RandomMealModel>,
                response: Response<RandomMealModel>
            ) {
                if(response.body()!=null){
                    mealDetailLiveData.value=response.body()!!.meals[0]
                }
                else {
                    return
                }

            }

            override fun onFailure(call: Call<RandomMealModel>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())
            }

        })

    }
    fun observeMealDetailLiveData():LiveData<Meal>{
        return  mealDetailLiveData;
    }


}