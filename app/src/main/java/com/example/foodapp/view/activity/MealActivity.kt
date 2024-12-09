package com.example.foodapp.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealBinding


import com.example.foodapp.model.Meal
import com.example.foodapp.view.fragment.HomeFragment
import com.example.foodapp.viewmodel.MealViewModel


class MealActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMealBinding
   private lateinit var mealId:String
   private  lateinit var mealName:String
   private  lateinit var mealThumb:String
    private lateinit var mealMvvm:MealViewModel
    private lateinit var youtubeLink:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm= ViewModelProvider(this)[MealViewModel::class.java]

//        val mealDatabase= MealDatabase.getInstance(this)
//        val  viewModelFactory= MealViewModelFactory(mealDatabase)
//        mealMvvm= ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]


        getMealInformationFromIntent()
        setInformationInView()
        mealMvvm.getMealDetail(mealId)
        observeMealDetailLiveData()
        onYoutubeClickFun()

    }

   private var mealToSave:Meal?=null
    private fun onYoutubeClickFun() {
       binding.youtubeImgId.setOnClickListener {
           val  intent =Intent(Intent.ACTION_VIEW,Uri.parse(youtubeLink))
           startActivity(intent)
       }
    }

    private fun observeMealDetailLiveData() {
    mealMvvm.observeMealDetailLiveData().observe(this, object :Observer<Meal>{
        override fun onChanged(value: Meal) {
           val meal=value
            mealToSave=meal;

            binding.tvCategory.text="Category:${meal.strCategory}"
            binding.tvArea.text="Area:${meal.strArea}"
            binding.tvInstructionDetail.text=meal.strInstructions
            youtubeLink= meal.strYoutube.toString()
        }

    })
    }

    private fun setInformationInView() {
        Glide.with(applicationContext).load(mealThumb).into(binding.imgMealDetail)
        binding.appBarCollaps.title=mealName
        binding.appBarCollaps.title

    }

    private fun getMealInformationFromIntent() {
      val intent =intent
        mealId= intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName=intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb=intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}