package com.example.recepieapp

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recepieapp.databinding.ActivityHomeBinding
import com.example.recepieapp.databinding.ActivitySearchBinding
import java.util.Locale.filter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private  lateinit var rvAdapter:SearchAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var recipes:List<Recipe?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecipe.requestFocus()
        var db= Room.databaseBuilder(this@SearchActivity,AppDatabse::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        recipes = daoObject.getAll()
        setUpRecyclerView()
        binding.goBackHome.setOnClickListener {
            finish()
        }
        binding.searchRecipe.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString()!=""){
                    filter(p0.toString())
                }else{
                    setUpRecyclerView()
                }
            }

            private fun filter(filterText: String) {
                var filterData=ArrayList<Recipe>()
                for (i in recipes.indices){
                    if (recipes[i]!!.tittle.lowercase().contains(filterText.lowercase())){
                        filterData.add(recipes[i]!!)
                    }
                    rvAdapter.filterList(filterData)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvSearch.layoutManager =
            LinearLayoutManager(this)
        var db = Room.databaseBuilder(this@SearchActivity, AppDatabse::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains("Popular")) {
                dataList.add(recipes[i]!!)
            }
        }
        rvAdapter = SearchAdapter(dataList, this)
        binding.rvSearch.adapter = rvAdapter
    }
}