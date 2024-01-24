package com.example.recepieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recepieapp.databinding.RvPopularItemBinding

class PopularAdapter(var datalist:ArrayList<Recipe>,var context: Context):
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: RvPopularItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=RvPopularItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.popularImage
        Glide.with(context).load(datalist.get(position).img).into(holder.binding.popularImage)
        holder.binding.popularText.text=datalist.get(position).tittle
        var time=datalist.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.popularTime.text=time.get(0)
        holder.itemView.setOnClickListener {
            var intent= Intent(context,recipeActivity::class.java)
            intent.putExtra("img",datalist.get(position).img)
            intent.putExtra("title",datalist.get(position).tittle)
            intent.putExtra("des",datalist.get(position).des)
            intent.putExtra("ing",datalist.get(position).ing)
            context.startActivity(intent)
        }
    }
}