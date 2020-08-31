package com.fsk.progressadapter.app.emptyitemdecoration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fsk.progressadapter.EmptyItemDecoration
import com.fsk.progressadapter.app.R
import kotlinx.android.synthetic.main.fragment_adapter.recyclerview
import kotlinx.android.synthetic.main.fragment_adapter.toolbar

class EmptyItemDecorationFragment : Fragment(R.layout.fragment_adapter) {

    private val groceryAdapter = SimpleGroceriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.empty_item_decoration_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.refresh -> reload()
                R.id.clear -> groceryAdapter.items = emptyList()
            }

            true
        }

        recyclerview.apply {
            adapter = groceryAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            addItemDecoration(EmptyItemDecoration().apply {
                this.view = LayoutInflater.from(requireContext())
                    .inflate(R.layout.view_empty_groceries, recyclerview, false)
            })
        }
    }

    private fun reload() {
        groceryAdapter.items =
            listOf(
                "Apples",
                "Bananas",
                "Carrots",
                "Onions",
                "Lettuce",
                "Oranges",
                "Cabbage",
                "Bread",
                "Garlic",
                "Tomatoes"
            )
    }

}