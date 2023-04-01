package com.example.foodshop_14

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodshop_14.databinding.ItemFoodBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvents: FoodEvents ) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(private val binding: ItemFoodBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {


            binding.itemTxtSubject.text = data[position].txtSubject
            binding.itemTxtCity.text = data[position].txtCity
            binding. itemTxtPrice.text = "$" + data[position].txtPrice + " vip"
            binding.  itemTxtDistance.text = data[position].txtDistance + " miles from you"
            binding.itemRatingMain.rating = data[position].rating
            binding. itemTxtRating.text = "(" + data[position].numOfRating.toString() + " Ratings)"


            Glide
                .with(binding.root.context)
                .load(data[position].urlImage)
                .transform(RoundedCornersTransformation(16, 4))
                .into(binding.itemImgMain)

            itemView.setOnClickListener {
                foodEvents.onFoodClicked(data[adapterPosition] , adapterPosition)

            }
            itemView.setOnLongClickListener {

                foodEvents.onFoodLONGClicked(data[adapterPosition] , adapterPosition)
                true

            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return FoodViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindData(position)

    }
    override fun getItemCount(): Int {
        return data.size
    }




    fun addFood (newFood: Food){
        data.add(0, newFood)
        notifyItemInserted(0)
    }
    fun removeFood(oldFood: Food, oldPosition: Int){
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)

    }
    fun updateFood(newFood: Food, position: Int){

        data.set(position , newFood)
        notifyItemChanged(position)


    }
    fun setData(newList:ArrayList<Food>){

        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()

    }



    interface  FoodEvents{

        fun onFoodClicked(food: Food, position: Int)
        fun  onFoodLONGClicked(food: Food, oldPosition: Int)

    }

}
