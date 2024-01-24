package com.example.recepieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recepieapp.databinding.SearchRvBinding

class SearchAdapter(var datalist:ArrayList<Recipe>, var context: Context):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
    fun filterList(filterList:ArrayList<Recipe>){
        datalist=filterList
        notifyDataSetChanged()

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      Glide.with(context).load(datalist.get(position).img).into(holder.binding.searchImage)
        holder.binding.searchtxt.text=datalist.get(position).tittle
        holder.itemView.setOnClickListener {
            var intent=Intent(context,recipeActivity::class.java)
            intent.putExtra("img",datalist.get(position).img)
            intent.putExtra("title",datalist.get(position).tittle)
            intent.putExtra("des",datalist.get(position).des)
            intent.putExtra("ing",datalist.get(position).ing)
            context.startActivity(intent)
        }
    }
}