package changhwan.experiment.sopthomework

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(private val height: Float,private val padding: Float, @ColorInt private val color: Int,private val margin : Int):RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init{
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = ( child.bottom.toFloat() + margin)
            val bottom = child.bottom.toFloat() + height + margin

            c.drawRect(left, top, right, bottom, paint)

        }
    }
}