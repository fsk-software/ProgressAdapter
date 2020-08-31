package com.fsk.progressadapter.app.progress

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fsk.progressadapter.app.R
import kotlinx.android.synthetic.main.fragment_adapter.recyclerview
import kotlinx.android.synthetic.main.fragment_adapter.toolbar

class ProgressAdapterFragment : Fragment(R.layout.fragment_adapter) {

    private val groceryAdapter = GroceriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.progress_adapter_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.refresh -> reload()
                R.id.toggleError -> {
                    groceryAdapter.clear()

                    it.isChecked = !it.isChecked
                    groceryAdapter.showError = it.isChecked
                    if (!it.isChecked) {
                        reload()
                    }
                }
            }

            true
        }

        recyclerview.apply {
            adapter = groceryAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        reload()
    }

    private fun reload() {
        groceryAdapter.clear()
        showMenuItems(false)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(LoadingRunnable(handler), 1000L)
    }

    private fun showMenuItems(visible: Boolean) {

        toolbar.apply {
            findViewById<View>(R.id.refresh)?.isVisible = visible
            findViewById<View>(R.id.toggleError)?.isVisible = visible
        }
    }

    inner class LoadingRunnable(
        private val handler: Handler
    ) : Runnable {
        private var count = 0

        override fun run() {
            if (++count < 5) {
                groceryAdapter.progress = count * 20
                handler.postDelayed(this, 1000L)
            } else {
                showMenuItems(true)

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
    }
}