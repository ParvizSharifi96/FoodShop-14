package com.example.foodshop_14.room

import androidx.room.*


@Dao
interface FoodDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun  insertOrUpdate(food: Food)

//    @Insert
//  fun insertFood(food: Food)

  @Insert
  fun insertAllFood(data : List<Food>)
//    @Update
//    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query( "DELETE FROM table_food " )
    fun deleteAllFoods()

    @Query( "SELECT * FROM table_food" )
    fun getAllFood(): List<Food>

    @Query( "SELECT * FROM table_food WHERE txtSubject LIKE '%' ||  :searching || '%' " )
    fun searchFood( searching: String):List<Food>


}