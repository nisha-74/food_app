package com.example.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PopularItemBinding
import com.example.foodapp.model.PopularItemMeal

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
   lateinit var onItemClick:((PopularItemMeal)-> Unit)
    private var mealList=ArrayList<PopularItemMeal>()
    @SuppressLint("NotifyDataSetChanged")
    fun setMeals(mealList: ArrayList<PopularItemMeal>){
        this.mealList=mealList
        notifyDataSetChanged()
    }
    class  PopularMealViewHolder( val binding: PopularItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
       return  PopularMealViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false
       ))
    }

    override fun getItemCount(): Int {
        return  mealList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.binding.poularItemImg)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(
                mealList[position]
            )
        }
    }

}