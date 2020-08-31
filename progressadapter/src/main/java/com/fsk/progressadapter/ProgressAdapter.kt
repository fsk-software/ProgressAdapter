package com.fsk.progressadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * An adapter that will show a user-defined progress view when no data is available and no errors are present.
 * It will also show an error view when the [showError] field is true.
 *
 * The actual items are the datums managed by this adapter, excluding the error and progress views.
 */
abstract class ProgressAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        /**
         * View type for the progress view
         */
        const val PROGRESS_VIEW_TYPE = 1

        /**
         *  View type for the error view
         */
        const val ERROR_VIEW_TYPE = 2
    }

    /**
     * The current progress amount for the progress view.  It calls [notifyItemChanged] as part of the set.
     */
    open var progress: Int = 0
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    /**
     * True to show the error instead of the data.  It calls [notifyItemChanged] as part of the set.
     */
    open var showError: Boolean = false
        set(value) {
            if (value) {
                //clear any data since there is an error.
                clear()
            }

            field = value
            notifyItemChanged(0)
        }

    /**
     * Clear all data in the adapter.  This includes any progress and error states.
     */
    open fun clear() {
        progress = 0
        showError = false
        clearActualItems()
    }

    /**
     * Clear the actual items.
     */
    protected abstract fun clearActualItems()

    /**
     * Get the actual item count.
     *
     * @return the number of actual items managed by the adapter.
     */
    abstract fun getActualItemCount(): Int

    /**
     * Get the view type for the actual item at the specified position
     *
     * @param position the position of the actual item
     *
     * @return the view type for the actual item at the position
     */
    protected abstract fun getActualItemViewType(position: Int): Int

    /**
     * Create the view holder an actual item.
     *
     * @param inflater the inflater for inflating a view for the holder
     * @param parent the view group that will be the parent for the view.
     * @param viewType the view type for the actual item
     *
     * @return the view holder for the actual item
     */
    protected abstract fun onCreateActualViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder


    /**
     * Create the view holder for the error state.
     *
     * @param inflater the inflater for inflating a view for the holder
     * @param parent the view group that will be the parent for the view.
     *
     * @return the view holder for the error state
     */
    protected abstract fun onCreateErrorViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder

    /**
     * Create the view holder for the progress state.
     *
     * @param inflater the inflater for inflating a view for the holder
     * @param parent the view group that will be the parent for the view.
     *
     * @return the view holder for the progress state
     */
    protected abstract fun onCreateProgressViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder


    /**
     * Bind the actual item at the position to the view holder
     *
     * @param holder the holder to update with the actual item at the position
     * @param position the position of the actual item to bind to the view holder
     */
    protected abstract fun onBindActualViewHolder(holder: RecyclerView.ViewHolder, position: Int)


    /**
     * Bind the error to its view holder
     *
     * @param holder the holder to update
     */
    protected open fun onBindErrorViewHolder(holder: RecyclerView.ViewHolder) {}


    /**
     * Bind the progress to its view holder
     *
     * @param holder the holder to update
     * @param progress the progress for the holder.  Ignore this value if the progress is indeterminate.
     */
    protected open fun onBindProgressViewHolder(holder: RecyclerView.ViewHolder, progress: Int) {}

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> when {
                showError -> ERROR_VIEW_TYPE

                getActualItemCount() == 0 -> PROGRESS_VIEW_TYPE

                else -> getActualItemViewType(position)
            }

            else -> getActualItemViewType(position)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LayoutInflater.from(parent.context).let {
            when (viewType) {
                ERROR_VIEW_TYPE -> onCreateErrorViewHolder(it, parent)
                PROGRESS_VIEW_TYPE -> onCreateProgressViewHolder(it, parent)
                else -> onCreateActualViewHolder(it, parent, viewType)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ERROR_VIEW_TYPE -> onBindErrorViewHolder(holder)
            PROGRESS_VIEW_TYPE -> onBindProgressViewHolder(holder, progress)
            else -> onBindActualViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int =
        when {
            showError -> 1
            getActualItemCount() == 0 -> 1
            else -> getActualItemCount()
        }
}