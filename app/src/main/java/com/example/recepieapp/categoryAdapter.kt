package com.example.recepieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recepieapp.databinding.CategoryRvBinding

class categoryAdapter(var datalist:ArrayList<Recipe>,var context: Context):
    RecyclerView.Adapter<categoryAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:CategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(datalist.get(position).img).into(holder.binding.image)
        holder.binding.foodtitle.text=datalist.get(position).tittle
        var temp=datalist.get(position).ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.time.text=temp[0]
        holder.binding.next.setOnClickListener {
            var intent= Intent(context,recipeActivity::class.java)
            intent.putExtra("img",datalist.get(position).img)
            intent.putExtra("title",datalist.get(position).tittle)
            intent.putExtra("des",datalist.get(position).des)
            intent.putExtra("ing",datalist.get(position).ing)
            context.startActivity(intent)
        }
    }
}