package ir.sinadalvand.fobapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ir.sinadalvand.floatoptionbutton.FloatOptionButtonListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        floatOptionButton.setFloatOptionListener(object :FloatOptionButtonListener{
            override fun mainButtonClicked(view: View) {
                floatOptionButton.toggle()
            }

            override fun leftButtonClicked(view: View) {

            }

            override fun rightButtonClicked(view: View) {

            }

        })
    }
}