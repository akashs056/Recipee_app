package com.example.recepieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recepieapp.databinding.ActivityCategoryBinding

class categoryActivity : AppCompatActivity() {
    private  lateinit var rvAdapter:categoryAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var binding:ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categorytitle.text=intent.getStringExtra("tittle")
        setUpRecyclerView()
        binding.back.setOnClickListener {
            finish()
        }
    }
    private fun setUpRecyclerView() {
        dataList= ArrayList()

        binding.rvCategory.layoutManager=
            LinearLayoutManager(this)
        var db= Room.databaseBuilder(this@categoryActivity,AppDatabse::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for (i in recipes.indices){
            if (recipes[i].category.contains(intent.getStringExtra("category")!!))
            {
                dataList.add(recipes[i])
            }
            rvAdapter=categoryAdapter(dataList,this)
            binding.rvCategory.adapter=rvAdapter
        }
    }
}