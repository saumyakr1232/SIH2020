package com.example.sih

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(val context: Context, val categories:List<Category>, val onItemClick:(Int)->Unit): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        val myViewHolder=MyViewHolder(view,onItemClick)
        return myViewHolder
    }

    override fun getItemCount(): Int {

        return categories.count()

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(categories[position])
    }

    // Class to create recycler view
    inner class MyViewHolder(itemView:View,val onItemClick: (Int) -> Unit):RecyclerView.ViewHolder(itemView){

        val categoryImage= itemView.findViewById<ImageView>(R.id.imageViewCategoryImage)
        val categoryName =itemView.findViewById<TextView>(R.id.textViewCategoryName)

        fun bindData(category: Category){
            categoryImage.setImageResource(category.resourceId)
            categoryName.text= category.name
            //   itemView.setOnClickListener {
            //  Toast.makeText(context,"You Clicked on ${category.name}",Toast.LENGTH_LONG).show()

            //     val intent=Intent(context,QuoteDetailsActivity::class.java)
            //      context.startActivity(intent)
            // }
            itemView.setOnClickListener {
                onItemClick(category.id)
            }

        }
    }
}