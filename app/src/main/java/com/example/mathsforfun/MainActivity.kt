package com.example.mathsforfun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    var count:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img=findViewById<ImageView>(R.id.imageView)
        val bar=findViewById<ProgressBar>(R.id.progressBar2)
        val t = Timer()

        //https://www.youtube.com/watch?v=K5bFv_WDjVY&t=158s
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                count++
                bar.progress = count
                if (count == 50){
                    t.cancel()
                    val intent = Intent(this@MainActivity,HomePage::class.java)
                    startActivity(intent)
                }
            }
        }
        t.schedule(tt, 0, 50)

        img.setImageResource(R.drawable.maths)
    }
}