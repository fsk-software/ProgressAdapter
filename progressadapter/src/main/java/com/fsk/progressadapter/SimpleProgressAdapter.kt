package com.fsk.progressadapter

/**
 * A [ProgressAdapter] that manages actual data of a single type.
 *
 * @
 */
abstract class SimpleProgressAdapter<T> : ProgressAdapter() {

    companion object {
        /**
         * View type for the actual view
         */
        const val ACTUAL_VIEW_TYPE = 3
    }

    /**
     * the items for the adapter.  It calls [notifyDataSetChanged] as part of the set.
     */
    open var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun clearActualItems() {
        items = emptyList()
    }

    override fun getActualItemCount(): Int = items.size

    override fun getActualItemViewType(position: Int): Int = ACTUAL_VIEW_TYPE

}