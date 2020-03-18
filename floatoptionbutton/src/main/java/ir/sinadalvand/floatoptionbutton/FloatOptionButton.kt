package ir.sinadalvand.floatoptionbutton

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.setPadding
import com.github.clans.fab.FloatingActionButton


class FloatOptionButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private val button: FloatingActionButton = FloatingActionButton(context, attrs, defStyleAttr)
    private val leftButton: ImageView = ImageView(context, attrs, defStyleAttr)
    private val rightButton: ImageView = ImageView(context, attrs, defStyleAttr)
    private lateinit var backgroundRoundDrawable: RoundOptionDrawable;
    private var fabColor = Color.WHITE
    private var sideIconCOlor = Color.parseColor("#f64b3c")
    private var bgColor = Color.parseColor("#f64b3c")
    private var fabIcon = R.drawable.ic_lock
    private var leftIcon = R.drawable.ic_airplay
    private var rightIcon = R.drawable.ic_image
    private var isOpen = false
    private var scale = 4.0f
    private var duration = 200
    private var maxWidth = -1
    private var listener: FloatOptionButtonListener? = null
    private var animator: ValueAnimator = ValueAnimator.ofFloat()
    private var isExpanded = false;

    init {
        button.setOnClickListener(this)
        leftButton.setOnClickListener(this)
        rightButton.setOnClickListener(this)


        addView(button)
        addView(leftButton)
        addView(rightButton)


        val a = context.obtainStyledAttributes(attrs, R.styleable.FloatOptionButton)
        fabColor = a.getColor(R.styleable.FloatOptionButton_fob_buttonColor, fabColor)
        sideIconCOlor = a.getColor(R.styleable.FloatOptionButton_fob_mainIconColor, sideIconCOlor)
        bgColor = a.getColor(R.styleable.FloatOptionButton_fob_backgroundColor, bgColor)
        fabIcon = a.getResourceId(R.styleable.FloatOptionButton_fob_mainIcon, fabIcon)
        leftIcon = a.getResourceId(R.styleable.FloatOptionButton_fob_leftIcon, leftIcon)
        rightIcon = a.getResourceId(R.styleable.FloatOptionButton_fob_rightIcon, rightIcon)
        isOpen = a.getBoolean(R.styleable.FloatOptionButton_fob_open, isOpen)
        scale = a.getFloat(R.styleable.FloatOptionButton_fob_scale, scale)
        duration = a.getInt(R.styleable.FloatOptionButton_fob_animDuration, duration)
        maxWidth = a.getInt(R.styleable.FloatOptionButton_fob_scaleWidth, maxWidth)
        a.recycle()



        animator.duration = duration.toLong()
        animator.addUpdateListener {
            val morph = it.animatedValue as Float
            val p: ViewGroup.LayoutParams = layoutParams as ViewGroup.LayoutParams
            p.width = morph.toInt()
            this.layoutParams = p
            this.requestLayout()
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        initDetails()
    }

    fun initDetails() {

        button.colorNormal = fabColor
        button.colorRipple = Color.GRAY
        button.colorPressed = fabColor
        backgroundRoundDrawable = RoundOptionDrawable(bgColor)

        button.setImageResource(fabIcon)
        leftButton.setImageResource(leftIcon)
        rightButton.setImageResource(rightIcon)

        leftButton.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        rightButton.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)

        // set elevation and background
        background = backgroundRoundDrawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.elevation = 5.px.toFloat()
        }

        // set ripple to left and right button
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            leftButton.foreground = resources.getDrawable(R.drawable.ripple_effect_for_buttons)
            rightButton.foreground = resources.getDrawable(R.drawable.ripple_effect_for_buttons)
        }

        if (maxWidth > 0) {
            scale = maxWidth / resources.getDimension(R.dimen.defaultSize)
        }

        if (scale < 1) throw IllegalStateException("${if (maxWidth > 0) "fob_scaleWidth" else "fob_scale"} is smaller that default size! increase it ..")

        if (isOpen) {
            val params = layoutParams.apply {
                width = (resources.getDimension(R.dimen.defaultSize) * scale).toInt()
            }
            layoutParams = params
            isExpanded = true
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = resources.getDimension(R.dimen.defaultSize).toInt()
        val desiredHeight = resources.getDimension(R.dimen.defaultSize).toInt()

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        //Measure Width

        //Measure Width
        width = if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            desiredWidth
        }

        //Measure Height

        //Measure Height
        height = if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            Math.min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            desiredHeight
        }

        //MUST CALL THIS


        val backgroundHeight = button.height * 4 / 5
        backgroundRoundDrawable.setHeight(backgroundHeight)

        val fabParams = button.layoutParams as LayoutParams
        fabParams.gravity = Gravity.CENTER
        button.layoutParams = fabParams


        val leftParams = leftButton.layoutParams as LayoutParams
        leftParams.width = backgroundHeight
        leftParams.height = backgroundHeight
        leftParams.gravity = Gravity.CENTER_VERTICAL
        leftButton.layoutParams = leftParams

        val rightParams = rightButton.layoutParams as LayoutParams
        rightParams.width = backgroundHeight
        rightParams.height = backgroundHeight
        rightParams.gravity = Gravity.CENTER_VERTICAL
        rightButton.layoutParams = rightParams


        //MUST CALL THIS
        if (isInEditMode) {
            setMeasuredDimension(desiredWidth, desiredHeight)
        } else {
            setMeasuredDimension(width, height)
        }

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val prefDestination = width / 10;

        leftButton.setPadding((leftButton.width - 25.px) / 2)
        rightButton.setPadding((rightButton.width - 25.px) / 2)

        leftButton.layout(
            prefDestination,
            leftButton.top,
            prefDestination + leftButton.width,
            leftButton.bottom
        )


        rightButton.layout(
            (width - prefDestination) - rightButton.width,
            rightButton.top,
            width - prefDestination,
            rightButton.bottom
        )

    }


    override fun onClick(v: View?) {
        when (v) {
            button -> {
                listener?.mainButtonClicked(button)
            }
            leftButton -> {
                listener?.leftButtonClicked(leftButton)
            }
            rightButton -> {
                listener?.rightButtonClicked(rightButton)
            }
        }
    }


    fun toggle() {
        if (animator.isRunning) return
        if (width.toFloat() == resources.getDimension(R.dimen.defaultSize)) {
            expand()
        } else {
            collapse()
        }
    }

    fun expand() {
        if (animator.isRunning || width > resources.getDimension(R.dimen.defaultSize)) return
        animator.setFloatValues(width.toFloat(), width * scale)
        animator.start()
        isExpanded = true
    }

    fun collapse() {
        if (animator.isRunning || width != (resources.getDimension(R.dimen.defaultSize) * scale).toInt()) return
        animator.setFloatValues(width.toFloat(), resources.getDimension(R.dimen.defaultSize))
        animator.start()
        isExpanded = false
    }

    fun setFloatOptionListener(listener: FloatOptionButtonListener) {
        this.listener = listener
    }

    fun setMainButtonColor(color: Int) {
        this.fabColor = color
        initDetails()
        invalidate()
    }

    fun setSidesButtonColor(color: Int) {
        this.sideIconCOlor = color
        initDetails()
        invalidate()
    }

    fun setFobBackgroundColor(color: Int) {
        this.bgColor = color
        initDetails()
        invalidate()
    }


    fun setMainButtonIcon(res: Int) {
        this.fabIcon = res
        initDetails()
        invalidate()
    }

    fun setLeftButtonIcon(res: Int) {
        this.leftIcon = res
        initDetails()
        invalidate()
    }


    fun setRightButtonIcon(res: Int) {
        this.rightIcon = res
        initDetails()
        invalidate()
    }

    fun isOpen(boolean: Boolean) {
        this.isOpen = true
        initDetails()
        requestLayout()
    }

    fun setScale(scale: Float) {
        this.scale = scale
        initDetails()
        requestLayout()
    }

    fun setAnimationDuration(duration: Int) {
        this.duration = duration
        initDetails()
    }

    fun setMaxWidth(width: Int) {
        maxWidth = width
        initDetails()
        requestLayout()
    }

    fun getMainButton(): FloatingActionButton {
        return button
    }

    fun getRightButton(): ImageView {
        return rightButton
    }

    fun getLeftButton(): ImageView {
        return leftButton
    }


    /* save state details */
    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val myState = SavedState(superState)
        myState.isExpanded = this.isExpanded
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        this.isExpanded = savedState.isExpanded
        requestLayout()
    }

    private class SavedState : BaseSavedState {
        var isExpanded: Boolean = false

        internal constructor(superState: Parcelable?) : super(superState) {}
        private constructor(parcel: Parcel) : super(parcel) {
            isExpanded = parcel.readSuperBoolean()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeSuperBoolean(isExpanded)
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState?> =
                object : Parcelable.Creator<SavedState?> {
                    override fun createFromParcel(parcel: Parcel?): SavedState {
                        return SavedState(parcel!!)
                    }

                    override fun newArray(size: Int): Array<SavedState?> {
                        return arrayOfNulls(size)
                    }
                }
        }
    }

}