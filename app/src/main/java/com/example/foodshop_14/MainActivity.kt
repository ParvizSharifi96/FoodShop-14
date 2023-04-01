package com.example.foodshop_14

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop_14.databinding.ActivityMainBinding
import com.example.foodshop_14.databinding.DialogAddNewItemBinding
import com.example.foodshop_14.databinding.DialogDeleteItemBinding
import com.example.foodshop_14.room.Food
import com.example.foodshop_14.room.FoodDao
import com.example.foodshop_14.room.MyDatabase



const val BASE_URL_IMAGE = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food"

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        Toast.makeText(this, "Create by : Parviz Sharifi", Toast.LENGTH_LONG).show()
        foodDao = MyDatabase.getDatabase(this).foodDao
        val sharedPreferences = getSharedPreferences("duniFood", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run", true)) {
            firstRun()
            sharedPreferences.edit().putBoolean("first_run", false).apply()
        }

        showAllData()
        binding.btnRemoveAllFood.setOnClickListener {
           removeAllData()
        }

        binding.btnAddNewFood.setOnClickListener {

            addNewFood()
        }

    }


    private fun addNewFood(){

            val dialog = AlertDialog.Builder(this).create()

           val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
           dialog.setView(dialogBinding.root)
           dialog.setCancelable(true)
           dialog.show()
        dialogBinding.dialogBtnDone.setOnClickListener {

            if (
                dialogBinding.dialogEdtNameFood.length() > 0 &&
                dialogBinding.dialogEdtFoodCity.length() > 0 &&
                dialogBinding.dialogEdtFoodPrice.length() > 0 &&
                dialogBinding.dialogEdtFoodDistance.length() > 0
            ) {

                val txtName = dialogBinding.dialogEdtNameFood.text.toString()
                val txtPrice = dialogBinding.dialogEdtFoodPrice.text.toString()
                val txtDistance = dialogBinding.dialogEdtFoodDistance.text.toString()
                val txtCity = dialogBinding.dialogEdtFoodCity.text.toString()
                val txtRatingNumber: Int = (1..150).random()
                val ratingBarStar: Float = (1..5).random().toFloat()

                val randomForUrl = (0 until 12).random()
                val urlpic = "$BASE_URL_IMAGE$randomForUrl.jpg"

                val newFood = Food(
                    txtSubject = txtName,
                    txtPrice = txtPrice,
                    txtDistance = txtDistance,
                    txtCity = txtCity,
                    urlImage = urlpic,
                    numOfRating = txtRatingNumber,
                    rating = ratingBarStar
                )
                myAdapter.addFood(newFood)
                foodDao.insertFood(newFood)

                dialog.dismiss()
                binding.recyclerMain.scrollToPosition(0)


            } else {

                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()

            }





    }

    }



    private fun removeAllData() {
        foodDao.deleteAllFoods()
        showAllData()
    }


    private fun firstRun() {

        val foodlist = listOf(
            Food(
                txtSubject = "Hamburger",
                txtPrice = "15",
                txtDistance = "3",
                txtCity = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                numOfRating = 20,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Grilled fish",
                txtPrice = "20",
                txtDistance = "2.1",
                txtCity = "Tehran, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                numOfRating = 10,
                rating = 4f
            ),
            Food(
                txtSubject = "Lasania",
                txtPrice = "40",
                txtDistance = "1.4",
                txtCity = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                numOfRating = 30,
                rating = 2f
            ),
            Food(
                txtSubject = "pizza",
                txtPrice = "10",
                txtDistance = "2.5",
                txtCity = "Zahedan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                numOfRating = 80,
                rating = 1.5f
            ),
            Food(
                txtSubject = "Sushi",
                txtPrice = "20",
                txtDistance = "3.2",
                txtCity = "Mashhad, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                numOfRating = 200,
                rating = 3f
            ),
            Food(
                txtSubject = "Roasted Fish",
                txtPrice = "40",
                txtDistance = "3.7",
                txtCity = "Jolfa, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                numOfRating = 50,
                rating = 3.5f
            ),
            Food(
                txtSubject = "Fried chicken",
                txtPrice = "70",
                txtDistance = "3.5",
                txtCity = "NewYork, USA",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                numOfRating = 70,
                rating = 2.5f
            ),
            Food(
                txtSubject = "Vegetable salad",
                txtPrice = "12",
                txtDistance = "3.6",
                txtCity = "Berlin, Germany",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                numOfRating = 40,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Grilled chicken",
                txtPrice = "10",
                txtDistance = "3.7",
                txtCity = "Beijing, China",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                numOfRating = 15,
                rating = 5f
            ),
            Food(
                txtSubject = "Baryooni",
                txtPrice = "16",
                txtDistance = "10",
                txtCity = "Ilam, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                numOfRating = 28,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Ghorme Sabzi",
                txtPrice = "11.5",
                txtDistance = "7.5",
                txtCity = "Karaj, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                numOfRating = 27,
                rating = 5f
            ),
            Food(
                txtSubject = "Rice",
                txtPrice = "12.5",
                txtDistance = "2.4",
                txtCity = "Shiraz, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                numOfRating = 35,
                rating = 2.5f
            ),
            Food(
                txtSubject = "pizza",
                txtPrice = "10",
                txtDistance = "2.5",
                txtCity = "Zahedan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                numOfRating = 80,
                rating = 1.5f
            ),
            Food(
                txtSubject = "Sushi",
                txtPrice = "20",
                txtDistance = "3.2",
                txtCity = "Mashhad, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                numOfRating = 200,
                rating = 3f
            ),
            Food(
                txtSubject = "Roasted Fish",
                txtPrice = "40",
                txtDistance = "3.7",
                txtCity = "Jolfa, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                numOfRating = 50,
                rating = 3.5f
            ),
            Food(
                txtSubject = "Fried chicken",
                txtPrice = "70",
                txtDistance = "3.5",
                txtCity = "NewYork, USA",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                numOfRating = 70,
                rating = 2.5f
            ),
            Food(
                txtSubject = "Vegetable salad",
                txtPrice = "12",
                txtDistance = "3.6",
                txtCity = "Berlin, Germany",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                numOfRating = 40,
                rating = 4.5f
            ),
            Food(
                txtSubject = "Grilled chicken",
                txtPrice = "10",
                txtDistance = "3.7",
                txtCity = "Beijing, China",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                numOfRating = 15,
                rating = 5f
            ),
        )
        foodDao.insertAllFood(foodlist)

    }

    fun showAllData() {
        val foodData = foodDao.getAllFood()
        myAdapter = FoodAdapter(ArrayList(foodData), this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    override fun onFoodClicked(food: Food, position: Int) {  }

    override fun onFoodLONGClicked(food: Food, oldPosition: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.dialogBtnDeleteSure.setOnClickListener {

            dialog.dismiss()
            myAdapter.removeFood(food, oldPosition)
            foodDao.deleteFood(food)



        }


    }
}


