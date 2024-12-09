package com.example.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.CategoryitemBinding
import com.example.foodapp.model.CategoryMeal

class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
 var onItemClick:((CategoryMeal)-> Unit)? =null
var categoryListMeal:List<CategoryMeal> = ArrayList()
          @SuppressLint("NotifyDataSetChanged")
          fun setCategoryList(categoriesList: List<CategoryMeal>) {
             this.categoryListMeal=categoriesList as ArrayList<CategoryMeal>
              notifyDataSetChanged()
          }
    inner  class CategoryViewHolder(val binding: CategoryitemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      return  CategoryViewHolder(CategoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return  categoryListMeal.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
      Glide.with(holder.itemView).load(categoryListMeal[position].strCategoryThumb).into(holder.binding.categoryItemImg)
        holder.binding.tvCategoryItemName.text=categoryListMeal[position].strCategory
        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(categoryListMeal[position])
        }

    }


}