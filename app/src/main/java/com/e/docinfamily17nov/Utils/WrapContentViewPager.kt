package com.e.docinfamily17nov.Utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class WrapContentViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    private var mCurrentPagePosition = 0



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        try {
            val wrapHeight = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST
            if (wrapHeight) {
                val child: View? = getChildAt(mCurrentPagePosition)
                if (child != null) {
                    child.measure(
                        widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    )
                    val h: Int = child.getMeasuredHeight()
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun reMeasureCurrentPage(position: Int) {
        mCurrentPagePosition = position
        requestLayout()
    }
}