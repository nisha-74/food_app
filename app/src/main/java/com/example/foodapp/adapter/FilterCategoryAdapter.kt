package com.example.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.FiltercatgoryitemBinding
import com.example.foodapp.model.FilterMeal

class FilterCategoryAdapter():RecyclerView.Adapter<FilterCategoryAdapter.FilterCategoryViewHolder>() {
    private var filterCategoryList: List<FilterMeal> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterCategoryList(filterCategoryList:List<FilterMeal>){
        this.filterCategoryList=filterCategoryList as ArrayList<FilterMeal>
        notifyDataSetChanged()

    }
    inner  class  FilterCategoryViewHolder(val binding: FiltercatgoryitemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCategoryViewHolder {
        return  FilterCategoryViewHolder(FiltercatgoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return  filterCategoryList.size
    }

    override fun onBindViewHolder(holder: FilterCategoryViewHolder, position: Int) {
       Glide.with(holder.itemView).load(filterCategoryList[position].strMealThumb).into(holder.binding.filterIdImg)
        holder.binding.tvFilterTxName.text= filterCategoryList[position].strMeal
    }

}