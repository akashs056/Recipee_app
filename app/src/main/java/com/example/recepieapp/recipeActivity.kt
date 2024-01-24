package com.example.recepieapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.recepieapp.databinding.ActivityRecipeBinding

class recipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    private var imgCrop=true
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityRecipeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.fullimage)
        binding.maintitle.text=intent.getStringExtra("title")
        binding.stepsText.text=intent.getStringExtra("des")
        var ing=intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text=ing?.get(0)
        binding.backbtn.setOnClickListener {
            finish()
        }
        var ingredientText = ""

        for (i in 1 until ing!!.size) {
            ingredientText += "🟢 ${ing[i]}\n"
        }
        binding.ingText.text = ingredientText

        binding.steps.background=null
        binding.steps.setTextColor(Color.BLACK)
        binding.ing.setTextColor(Color.WHITE)

        binding.steps.setOnClickListener {
            binding.ingScroll.visibility=View.GONE
            binding.stepsScroll.visibility=View.VISIBLE
            binding.ing.setTextColor(Color.BLACK)
            binding.steps.setTextColor(Color.WHITE)
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.background=null
        }
        binding.ing.setOnClickListener {
            binding.stepsScroll.visibility=View.GONE
            binding.ingScroll.visibility=View.VISIBLE
            binding.ing.setTextColor(Color.WHITE)
            binding.steps.setTextColor(Color.BLACK)
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.background=null
        }

        binding.fullscreen.setOnClickListener {
            if (imgCrop){
                binding.fullimage.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.fullimage)
                binding.shade.visibility=View.GONE
                imgCrop=false
            }else{
                binding.fullimage.scaleType=ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.fullimage)
                binding.shade.visibility=View.GONE
                imgCrop=true
            }
        }
    }
}