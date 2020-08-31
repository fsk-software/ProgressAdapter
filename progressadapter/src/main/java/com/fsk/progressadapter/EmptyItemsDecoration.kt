package com.fsk.progressadapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Decoration that displays a static view to the user when there are no items in the recycler view.
 */
class EmptyItemDecoration : RecyclerView.ItemDecoration() {

    /**
     * The view to draw when there are no items in the adapter
     */
    var view: View? = null

    private var lastParentHeight: Int = -1
    private var lastParentWidth: Int = -1

    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null

    private val paint = Paint()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (parent.adapter?.itemCount == 0) {
            drawEmptyView(c, parent)
        }
    }

    private fun drawEmptyView(parentCanvas: Canvas, parent: RecyclerView) {
        if (bitmap == null || parent.height != lastParentHeight || parent.width != lastParentWidth) {
            bitmap = Bitmap.createBitmap(parent.width, parent.height, Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap!!)

            val widthSpec =
                View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
            val heightSpec =
                View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.EXACTLY)
            view?.apply {
                measure(widthSpec, heightSpec)
                layout(0, 0, parent.width, parent.height)
            }
            lastParentHeight = parent.height
            lastParentWidth = parent.width
        }

        view?.draw(canvas)
        parentCanvas.drawBitmap(bitmap!!, 0.0f, 0.0f, paint)
    }
}