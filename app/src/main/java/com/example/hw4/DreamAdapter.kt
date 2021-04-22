package com.example.hw4

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DreamAdapter(private val data: Array<Dream>) :
    RecyclerView.Adapter<DreamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dream_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dream : Dream

        holder.id = data[position].id.toString()
        holder.title = data[position].title

    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val id: TextView

        init {
            title = view.findViewById(R.id.title_text)
            id = view.findViewById(R.id.id_text)
        }
    }

}