package com.example.flow_practice.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flow_practice.R
import com.example.flow_practice.network.Blogs

class ListAdapter constructor(var blogData: List<Blogs>?): RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    private var TAG: String = "List Adapter"

    constructor() : this(null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view_holder, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        var siz = blogData?.size
        if(siz == null){
            siz = 1
        }

        return siz
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.textView.text = blogData?.get(position)?.name
        Log.d(TAG, "onBindViewHolder: " + blogData?.get(position)?.name)
    }

    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_view)
    }
}