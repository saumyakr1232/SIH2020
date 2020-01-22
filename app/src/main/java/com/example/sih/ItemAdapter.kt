package com.example.sih

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class ItemAdapter(val context: Context, val lists:List<String>, val onItemLongClick:(String)->Unit):RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        val myViewHolder=MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return lists.count()
    }


    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val ItemTextView = itemView.findViewById<TextView>(R.id.textViewLists)
        fun bind(list:String)
        {
            ItemTextView.text=list
            itemView.setOnLongClickListener {
                onItemLongClick(list)
                true
            }

        }
    }
}