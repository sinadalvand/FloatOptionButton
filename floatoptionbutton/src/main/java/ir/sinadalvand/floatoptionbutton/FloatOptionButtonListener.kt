package ir.sinadalvand.floatoptionbutton

import android.view.View

interface FloatOptionButtonListener {
    fun mainButtonClicked(view:View)
    fun leftButtonClicked(view:View)
    fun rightButtonClicked(view:View)
}