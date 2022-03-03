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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        val greaterBtn =  findViewById<Button>(R.id.greaterBtn)
        val equalsBtn =  findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        val leftExp = findViewById<TextView>(R.id.leftExp)
        val rightExp = findViewById<TextView>(R.id.rightExp)
        val resultDisplay = findViewById<TextView>(R.id.resultDisplay)
        val timer: TextView = findViewById(R.id.timer)
        val finish = Intent(this, GameOver::class.java)


        //var timeCount : Long = 50000

        object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "Seconds remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                finish.putExtra("correct", correctCount.toString())
                finish.putExtra("wrong", wrongCount.toString())
                startActivity(finish)
            }
        }.start()


        fun mainFunc(){
            val f1 = Functions()

            val left = f1.randomExpression()
            val right = f1.randomExpression()

            val leftStringExp = left.joinToString(separator = "")
            val rightStringExp = right.joinToString(separator = "")

            greaterBtn.setOnClickListener {
                if(f1.answer(leftStringExp) > f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                }else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            equalsBtn.setOnClickListener {
                if(f1.answer(leftStringExp) == f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                }else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            lessBtn.setOnClickListener {
                if(f1.answer(leftStringExp) < f1.answer(rightStringExp)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    mainFunc()
                }else {
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


