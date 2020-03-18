package ir.sinadalvand.floatoptionbutton

import android.graphics.*
import android.graphics.drawable.Drawable

internal class RoundOptionDrawable(color: Int = Color.RED) : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var heightBase: Int = bounds.height()
    private var widthBase: Int = bounds.height()

    init {
        paint.color = color
    }

    fun setHeight(height: Int) {
        this.heightBase = height
        this.widthBase = height;
        invalidateSelf()
    }

    override fun draw(canvas: Canvas) {
        val height = bounds.height()
        val width = bounds.width()
        val offset = height - heightBase

        canvas.drawCircle(heightBase / 2f, height / 2f, heightBase / 2f, paint)
        canvas.drawCircle(width - (heightBase / 2f), height / 2f, heightBase / 2f, paint)
        canvas.drawRect(heightBase / 2f, offset / 2f, width - (heightBase / 2f), height - (offset / 2f), paint)
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

}