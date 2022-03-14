package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GamePage : AppCompatActivity() {
    var correctCount = 0
    var wrongCount = 0
    var currentTime : Long = 0
    lateinit var count_down_timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val timer: TextView = findViewById(R.id.timer)
        val greaterBtn = findViewById<Button>(R.id.greaterBtn)
        val equalsBtn = findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        val leftExp = findViewById<TextView>(R.id.leftExp)
        val rightExp = findViewById<TextView>(R.id.rightExp)
        val resultDisplay = findViewById<TextView>(R.id.resultDisplay)
        val finish = Intent(this, GameOver::class.java)


        fun timerFunc(millisec: Long){
            count_down_timer = object: CountDownTimer(50000, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    currentTime = millisUntilFinished
                    timer.text = "" + currentTime / 1000
                }

                override fun onFinish() {
                    finish.putExtra("correct", correctCount.toString())
                    finish.putExtra("wrong", wrongCount.toString())
                    startActivity(finish)
                }
            }.start()
        }

        fun bonusTime() {
            if(correctCount % 5 == 0 && correctCount != 0) {
                count_down_timer.cancel()
                timerFunc(currentTime+5000)
            }
        }

        timerFunc(50000)

        fun mainFunc() {
            val f1 = Functions()

            val left = f1.randomExpression()
            val right = f1.randomExpression()

            val leftStringExp = left.joinToString(separator = "")
            val rightStringExp = right.joinToString(separator = "")

            bonusTime()

            greaterBtn.setOnClickListener {
                if (f1.answer(leftStringExp) > f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                } else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            equalsBtn.setOnClickListener {
                if (f1.answer(leftStringExp) == f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                } else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            lessBtn.setOnClickListener {
                if (f1.answer(leftStringExp) < f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                } else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            leftExp.text = f1.getFormattedExpression(left)
            rightExp.text = f1.getFormattedExpression(right)
        }
        mainFunc()

    }

}


