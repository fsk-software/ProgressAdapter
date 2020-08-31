package com.fsk.progressadapter.app.emptyitemdecoration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsk.progressadapter.app.R

internal class SimpleGroceriesAdapter :
    RecyclerView.Adapter<SimpleGroceriesAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
    }

    var items: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_grocery_item, parent, false)
        )


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.nameView.text = items[position]
    }


    override fun getItemCount(): Int = items.size
}