package com.example.recepieapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recepieapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeActivity : AppCompatActivity() {
    private  lateinit var rvAdapter:PopularAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.more.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet)
            bottomSheetDialog.show()
            bottomSheetDialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            bottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            bottomSheetDialog.window!!.setGravity(Gravity.BOTTOM)
        }

        setUpRecyclerView()
        binding.search.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.salad.setOnClickListener {
            var myIntent=Intent(this,categoryActivity::class.java)
            myIntent.putExtra("tittle","Salad")
            myIntent.putExtra("category","Salad")
            startActivity(myIntent)
        }
        binding.Maindish.setOnClickListener {
            var myIntent=Intent(this,categoryActivity::class.java)
            myIntent.putExtra("tittle","Main Dish")
            myIntent.putExtra("category","Dish")
            startActivity(myIntent)
        }
        binding.drinks.setOnClickListener {
            var myIntent=Intent(this,categoryActivity::class.java)
            myIntent.putExtra("tittle","Drinks")
            myIntent.putExtra("category","Drinks")
            startActivity(myIntent)
        }
        binding.deserts.setOnClickListener {
            var myIntent=Intent(this,categoryActivity::class.java)
            myIntent.putExtra("tittle","Deserts")
            myIntent.putExtra("category","Desserts")
            startActivity(myIntent)
        }
    }
    private fun setUpRecyclerView() {
        dataList= ArrayList()

    binding.rvPopular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    var db=Room.databaseBuilder(this@HomeActivity,AppDatabse::class.java,"db_name")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .createFromAsset("recipe.db")
        .build()

        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for (i in recipes!!.indices){
            if (recipes[i]!!.category.contains("Popular"))
            {
                dataList.add(recipes[i]!!)
            }
            rvAdapter=PopularAdapter(dataList,this)
            binding.rvPopular.adapter=rvAdapter
        }
    }
}