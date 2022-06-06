package com.example.mathsforfun

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.PopupWindow

class HomePage : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val game=findViewById<View>(R.id.button1)
        val about = findViewById<View>(R.id.button2)



        game.setOnClickListener {
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }

        //http://android-er.blogspot.com/2012/03/example-of-using-popupwindow.html
        about.setOnClickListener {
            val layoutInflater = baseContext
                .getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView: View = layoutInflater.inflate(R.layout.activity_about, null)
            val popupWindow = PopupWindow(
                popupView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            val dismiss =
                popupView.findViewById<View>(R.id.dismiss) as Button
            dismiss.setOnClickListener { // TODO Auto-generated method stub
                popupWindow.dismiss()
            }
            popupWindow.showAsDropDown(about, 50, -1400)
        }
    }
}