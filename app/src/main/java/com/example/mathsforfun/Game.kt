package com.example.mathsforfun

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.ArrayList

class Game : AppCompatActivity() {
    var questions:Int=0
    var correct:Int=0
    var wrong:Int=0
    private var time = mutableListOf<Long>(50000)
    private var timer = mutableListOf<CountDownTimer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val leftEquation = findViewById<TextView>(R.id.textView)
        val rightEquation = findViewById<TextView>(R.id.textView4)
        val answer = findViewById<TextView>(R.id.textView2)
        val countdown = findViewById<TextView>(R.id.textView3)

        val buttonLesser = findViewById<Button>(R.id.button4)
        val buttonEqual = findViewById<Button>(R.id.button3)
        val buttonGreater = findViewById<Button>(R.id.button)

        if(savedInstanceState==null){ //if there is no save data it run as a new game
            timer.add(countDownTimer(countdown, time))
        }

        createEquation(leftEquation, rightEquation, answer, countdown, buttonEqual, buttonGreater, buttonLesser)
    }

    //count down timer
    private fun countDownTimer(countdown: TextView, currentTime: MutableList<Long>): CountDownTimer {
        val timer = object : CountDownTimer(currentTime[0], 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("00")
                //converting millis to secs
                val sec = millisUntilFinished / 1000 % 60
                //setting the time to text view
                countdown.text = f.format(sec)

                //start time from 1 sec behind
                currentTime[0] = currentTime[0] - 1000
            }
            //after timer=0 directing to summary
            override fun onFinish() {
                val intent = Intent(this@Game, Summary::class.java)
                intent.putExtra("questions",questions)
                intent.putExtra("correct",correct)
                intent.putExtra("wrong",wrong)
                startActivity(intent)
            }
        }.start()
        return timer
    }
    //Saving the UI State
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val leftEquation = findViewById<TextView>(R.id.textView)
        val rightEquation = findViewById<TextView>(R.id.textView4)
        val answer = findViewById<TextView>(R.id.textView2)

        val writtenData: CharSequence = leftEquation.text
        val writtenData2: CharSequence = rightEquation.text
        val writtenData3: CharSequence = answer.text
        val writtenData4: Long = time[0]

        outState.putCharSequence("equ1", writtenData)
        outState.putCharSequence("equ2", writtenData2)
        outState.putCharSequence("ans", writtenData3)
        outState.putLong("timer",writtenData4)
    }
    //Restoring the UI State
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val leftEquation = findViewById<TextView>(R.id.textView)
        val rightEquation = findViewById<TextView>(R.id.textView4)
        val answer = findViewById<TextView>(R.id.textView2)
        val countdown = findViewById<TextView>(R.id.textView3)

        val storedData: CharSequence = savedInstanceState.getCharSequence("equ1").toString()
        val storedData2: CharSequence = savedInstanceState.getCharSequence("equ2").toString()
        val storedData3: CharSequence = savedInstanceState.getCharSequence("ans").toString()

        time[0]=savedInstanceState.getLong("timer")
        timer.add(countDownTimer(countdown, time))


        leftEquation.text = storedData
        rightEquation.text = storedData2
        answer.text = storedData3

    }
    //creating the equation
    private fun createEquation(
        leftEquation: TextView,
        rightEquation: TextView,
        answer: TextView,
        countdown: TextView,
        buttonGreater: Button,
        buttonEqual: Button,
        buttonLesser: Button
    ) {
        val arrayList = ArrayList<Int>()

        val generate1=GenerateEquation()
        val equation1:String=generate1.createEquation()
        leftEquation.text = ""
        leftEquation.setTextColor(Color.BLACK)
        leftEquation.append(equation1)//passing the equation to text view


        val generate2=GenerateEquation()
        val equation2:String=generate2.createEquation()
        rightEquation.text = ""
        rightEquation.setTextColor(Color.BLACK)
        rightEquation.append(equation2)

      /**  var i=0
        while (i<1) {
            if (equation1.length != equation2.length) {
                //passing the equation to text view
            } else {
                continue
            }
            i++
        }**/

        //less button
        buttonLesser.setOnClickListener {
            if (generate1.total < generate2.total) {
                answer.text = ""
                answer.setTextColor(Color.GREEN)//set text colour
                answer.append("Correct")
                correct++//incrementing the count of correct
                if(correct%5==0) {
                    time[0] = time[0] + 10000//incrementing the time
                    timer[0].cancel()//canceling the current time
                    timer.clear()
                    timer.add(countDownTimer(countdown, time))//setting the updated time to text view
                }

            } else {
                answer.text = ""
                answer.setTextColor(Color.RED)//set text colour
                answer.append("Wrong")
                wrong++//incrementing the count of wrong
            }
            questions++//incrementing the count of questions
            createEquation(leftEquation, rightEquation, answer, countdown, buttonEqual, buttonGreater, buttonLesser)//after display answer generate equation again
        }

        buttonGreater.setOnClickListener {
            if (generate1.total > generate2.total) {
                answer.text = ""
                answer.setTextColor(Color.GREEN)//set text colour
                answer.append("Correct")
                correct++ //incrementing the count of correct
                if(correct%5==0) {
                    time[0] = time[0] + 10000 //incrementing the time
                    timer[0].cancel()//canceling the current time
                    timer.clear()
                    timer.add(countDownTimer(countdown, time))//setting the updated time to text view
                }
            } else {
                answer.text = ""
                answer.setTextColor(Color.RED)//set text colour
                answer.append("Wrong")
                wrong++ //incrementing the count of wrong
            }
            questions++ //incrementing the count of questions
            createEquation(leftEquation, rightEquation, answer, countdown, buttonEqual, buttonGreater, buttonLesser)//after display answer generate equation again
        }

        buttonEqual.setOnClickListener {
            if (generate1.total == generate2.total) {
                answer.text = ""
                answer.setTextColor(Color.GREEN)//set text colour
                answer.append("Correct")
                correct++
                if(correct%5==0) {
                    time[0] = time[0] + 10000 //incrementing the time
                    timer[0].cancel()//canceling the current time
                    timer.clear()
                    timer.add(countDownTimer(countdown, time))//setting the updated time to text view
                }

            } else {
                answer.text = ""
                answer.setTextColor(Color.RED)//set text colour
                answer.append("Wrong")
                wrong++ //incrementing the count of wrong
            }
            questions++ //incrementing the count of questions
            createEquation(leftEquation, rightEquation, answer, countdown, buttonEqual, buttonGreater, buttonLesser)//after display answer generate equation again
        }

    }
}

















































