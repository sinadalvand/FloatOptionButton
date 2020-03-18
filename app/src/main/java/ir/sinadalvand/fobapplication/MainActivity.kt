package ir.sinadalvand.fobapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ir.sinadalvand.floatoptionbutton.FloatOptionButtonListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatOptionButton.setFloatOptionListener(object :FloatOptionButtonListener{
            override fun mainButtonClicked(view: View) {
                floatOptionButton.toggle()
                Toast.makeText(this@MainActivity,"Main Button Clicked!",Toast.LENGTH_SHORT).show()
            }

            override fun leftButtonClicked(view: View) {
                Toast.makeText(this@MainActivity,"Left Button Clicked!",Toast.LENGTH_SHORT).show()
            }

            override fun rightButtonClicked(view: View) {
                Toast.makeText(this@MainActivity,"Right Button Clicked!",Toast.LENGTH_SHORT).show()
            }

        })

        floatOptionButton.expand()
    }
}