package com.example.foodapp.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.CategoryAdapter
import com.example.foodapp.adapter.FilterCategoryAdapter
import com.example.foodapp.databinding.ActivityCategoryMealBinding
import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.view.fragment.HomeFragment
import com.example.foodapp.viewmodel.CategoryMealViewModel

class CategoryMealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryMealBinding
    private lateinit var categoryViewModel: CategoryMealViewModel
   private lateinit var filterCategoryAdapter: FilterCategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityCategoryMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryViewModel=ViewModelProvider(this)[CategoryMealViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prepareRecyclerView()
        getMealInformationFromIntent()
        observerLiveData()
    }

    private fun prepareRecyclerView() {
        filterCategoryAdapter= FilterCategoryAdapter()
        binding.rvCategoryIdImg.apply {
          layoutManager= GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=filterCategoryAdapter
        }

    }



    private fun getMealInformationFromIntent() {
        val intent =intent
        val  mealName:String=intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!
        categoryViewModel.getFilterMealByCategory(mealName);


        Log.d("ERROR", mealName);

    }
    private fun observerLiveData() {
        categoryViewModel.observerCategoryByLiveData().observe(this) { mealListvalue ->
            if (mealListvalue != null) {
                mealListvalue.forEach { meal ->
                    Log.d("ERROR", meal.strMeal)
                    filterCategoryAdapter.setFilterCategoryList(mealListvalue)
                }
            } else {
                Log.e("ERROR", "Meal list is null")
            }
        }
    }


}