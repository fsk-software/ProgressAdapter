package com.fsk.progressadapter.app.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsk.progressadapter.SimpleProgressAdapter
import com.fsk.progressadapter.app.R

internal class GroceriesAdapter : SimpleProgressAdapter<String>() {

    private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
    }

    private class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress)
    }

    private class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateActualViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        ItemViewHolder(inflater.inflate(R.layout.item_grocery_item, parent, false))

    override fun onCreateErrorViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder =
        SimpleViewHolder(inflater.inflate(R.layout.item_error, parent, false))


    override fun onCreateProgressViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder =
        ProgressViewHolder(inflater.inflate(R.layout.item_progress, parent, false))


    override fun onBindActualViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).apply {
            nameView.text = items[position]
        }
    }

    override fun onBindProgressViewHolder(holder: RecyclerView.ViewHolder, progress: Int) {
        (holder as ProgressViewHolder).apply {
            progressBar.progress = progress
        }
    }
}