package com.example.foodapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.adapter.CategoryAdapter
import com.example.foodapp.adapter.MostPopularAdapter
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.model.PopularItemMeal
import com.example.foodapp.model.Meal
import com.example.foodapp.view.activity.CategoryMealActivity
import com.example.foodapp.view.activity.MealActivity
import com.example.foodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var homeBinding:FragmentHomeBinding
    private  lateinit var homeVm:HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemAdapter: MostPopularAdapter
    private lateinit var categoryAdapter: CategoryAdapter
 companion object {
     const val MEAL_ID="com.example.foodapp.view.fragment.idMeal"
     const val MEAL_NAME="com.example.foodapp.view.fragment.nameMeal"
     const val MEAL_THUMB="com.example.foodapp.view.fragment.thumbMeal"
     const val CATEGORY_NAME="com.example.foodapp.view.fragment.categoryMeal"
 }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeVm= ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemAdapter=MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding= FragmentHomeBinding.inflate(inflater,container,false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularRecyclerViewItem()

        homeVm.getRandomMeal();
        observeRandomMeal()
        onRandomMealClick()
        homeVm.getPopularItemMeal()
        observerPopularItemMeal()
        popularItemOnClick()

        prepareCategoryRecyclerView()
        homeVm.getCategoryMeal()
        observerCategoryMealData()
        oncategoryItemClick()

    }

    private fun oncategoryItemClick() {
        categoryAdapter.onItemClick={
            categoryMealList->
            val intent = Intent(activity,CategoryMealActivity::class.java)
            intent.putExtra(CATEGORY_NAME,categoryMealList.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoryRecyclerView() {
        categoryAdapter= CategoryAdapter()
        homeBinding.categoryRecView.apply {
            layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter=categoryAdapter
        }

    }

    private fun observerCategoryMealData() {
        homeVm.observerCategoryMealData().observe(viewLifecycleOwner , Observer {
            categoryList->
            categoryAdapter.setCategoryList(categoryList)
        })
    }

    private fun popularItemOnClick() {
      popularItemAdapter.onItemClick= {
          meal->

          val intent = Intent(activity,MealActivity::class.java)
          intent.putExtra(MEAL_ID, meal.idMeal)
          intent.putExtra(MEAL_NAME,meal.strMeal)
          intent.putExtra(MEAL_THUMB,meal.strMealThumb)
          startActivity(intent)
      }
    }

    private fun preparePopularRecyclerViewItem() {
       homeBinding.recyclerViewOfPopularMeal.apply {
           layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
           adapter=popularItemAdapter
       }
    }

    private fun observerPopularItemMeal() {
     homeVm.observerPopularItemMealData().observe(
         viewLifecycleOwner
     ) { mealList->
         popularItemAdapter.setMeals(
             mealList = mealList as ArrayList<PopularItemMeal>
         )


     }


    }

    private fun onRandomMealClick() {
       homeBinding.cardView.setOnClickListener {
           val intent= Intent(activity, MealActivity::class.java)
           intent.putExtra(MEAL_ID,randomMeal.idMeal)
           intent.putExtra(MEAL_NAME,randomMeal.strMeal)
           intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
           startActivity(intent)
       }
    }

    private fun observeRandomMeal() {
        homeVm.observerRandomMealLiveData().observe(viewLifecycleOwner) { meal ->

            Glide.with(
                this@HomeFragment
            ).load(meal.strMealThumb).into(homeBinding.imageRandomMeal)
            this.randomMeal = meal;


        }
    }


}