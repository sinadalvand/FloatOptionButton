package ir.sinadalvand.floatoptionbutton

import android.content.res.Resources
import android.os.Build
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

/** begins delayed transition. */
@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun ViewGroup.beginDelayedTransition(duration: Long) {
    TransitionManager.beginDelayedTransition(this, ChangeBounds().setDuration(duration))
}

/** updates [FrameLayout] params. */
internal fun ViewGroup.updateLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
    layoutParams?.let {
        val params: ViewGroup.LayoutParams = (layoutParams as ViewGroup.LayoutParams).apply { block(this) }
        layoutParams = params
    }
}

/** makes visible or invisible a View align the value parameter. */
internal fun View.visible(value: Boolean) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

/** translates view's x position to destination. */
internal fun View.translateX(duration: Long, destination: Float): ViewPropertyAnimator {
    return this.animate()
            .setDuration(duration)
            .translationX(destination)
            .withLayer()
}

/** translates view's y position to destination. */
internal fun View.translateY(duration: Long, destination: Float): ViewPropertyAnimator {
    return this.animate()
            .setDuration(duration)
            .translationY(destination)
            .withLayer()
}

/** animates view's x and y scale size. */
internal fun View.animateScale(toX: Float, toY: Float, duration: Long): ViewPropertyAnimator {
    return this.animate()
            .scaleX(toX)
            .scaleY(toY)
            .setDuration(duration)
            .withLayer()
}

/** animates view's alpha value. */
internal fun View.animateFade(alpha: Float, duration: Long): ViewPropertyAnimator {
    return this.animate()
            .alpha(alpha)
            .setDuration(duration)
            .withLayer()
}


val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()