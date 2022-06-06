package com.example.mathsforfun

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Summary : AppCompatActivity() {
    var questions:Int=0
    var correct:Int=0
    var wrong:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val noQuestion = findViewById<TextView>(R.id.textView7)
        val noCorrect = findViewById<TextView>(R.id.textView10)
        val noWrong = findViewById<TextView>(R.id.textView11)


        val intent = intent

        val a:Int=intent.getIntExtra("questions",questions)
        val b:Int=intent.getIntExtra("correct",correct)
        val c:Int=intent.getIntExtra("wrong",wrong)


        noQuestion.text=""
        noQuestion.setTextColor(Color.MAGENTA)
        noQuestion.append(a.toString())
        noCorrect.text=""
        noCorrect.setTextColor(Color.GREEN)
        noCorrect.append(b.toString())
        noWrong.text=""
        noWrong.setTextColor(Color.RED)
        noWrong.append(c.toString())
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val noQuestion = findViewById<TextView>(R.id.textView7)
        val noCorrect = findViewById<TextView>(R.id.textView10)
        val noWrong = findViewById<TextView>(R.id.textView11)



        val writtenData:CharSequence=noQuestion.text
        val writtenData2:CharSequence=noCorrect.text
        val writtenData3:CharSequence=noWrong.text
        val writtenData4: ColorStateList? =noWrong.textColors;

        outState.putCharSequence("equ1",writtenData)
        outState.putCharSequence("equ2",writtenData2)
        outState.putCharSequence("ans",writtenData3)



    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val noQuestion = findViewById<TextView>(R.id.textView7)
        val noCorrect = findViewById<TextView>(R.id.textView10)
        val noWrong = findViewById<TextView>(R.id.textView11)

        val storedData:CharSequence= savedInstanceState.getCharSequence("equ1").toString()
        val storedData2:CharSequence= savedInstanceState.getCharSequence("equ2").toString()
        val storedData3:CharSequence= savedInstanceState.getCharSequence("ans").toString()

        noQuestion.text = storedData
        noCorrect.text = storedData2
        noWrong.text = storedData3

    }
    //set a action to back button
    override fun onBackPressed() {
        finish()
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
    }
}