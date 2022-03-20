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
    private lateinit var count_down_timer : CountDownTimer
    var formattedLeft = ""
    var formattedRight = ""
    private lateinit var leftExp : TextView
    private lateinit var rightExp : TextView
    lateinit var timer : TextView
    lateinit var finish : Intent
    var correctTimerScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        timer = findViewById(R.id.timer)
        val greaterBtn = findViewById<Button>(R.id.greaterBtn)
        val equalsBtn = findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        leftExp = findViewById(R.id.leftExp)
        rightExp = findViewById(R.id.rightExp)
        val resultDisplay = findViewById<TextView>(R.id.resultDisplay)
        finish = Intent(this, GameOver::class.java) // intent starting a new activity


        /**
         *
         * This function adds bonus time by cancelling the running countdowntimer.
         *
         */
        fun bonusTime() {
            if (correctTimerScore % 5 == 0 && correctTimerScore != 0) {
                correctTimerScore = 0
                count_down_timer.cancel()
                timerFunc(currentTime + 10000)
            }
        }

        timerFunc(50000)

        /**
         *
         * This function gets called whenever onCreate() is called and
         * whenever the function is required to run.
         *
         */
        fun mainFunc() {
            val f1 = Functions() // create an instance of functions

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
                    correctTimerScore++
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
                    correctTimerScore++
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
                    correctTimerScore++
                    mainFunc()
                } else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
            formattedLeft = f1.getFormattedExpression(left)
            formattedRight = f1.getFormattedExpression(right)
            leftExp.text = formattedLeft
            rightExp.text = formattedRight
        }
        mainFunc()
    }


    /**
     *
     * This function starts the timer.
     *
     */
    fun timerFunc(milliseconds: Long){
        count_down_timer = object: CountDownTimer(milliseconds, 1000){
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

    /**
     *
     * This inbuilt function saves data on orientation change.
     *
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("LEFT_RESULT", formattedLeft)
        outState.putString("RIGHT_RESULT", formattedRight)
        outState.putInt("CORRECT_RESULT", correctCount)
        outState.putInt("WRONG_RESULT", wrongCount)
        outState.putLong("TIMER_RESULT",currentTime)
        outState.putInt("TIMER_CORRECT", correctTimerScore)
     }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        formattedLeft = savedInstanceState.getString("LEFT_RESULT", "")
        leftExp.text = formattedLeft
        formattedRight = savedInstanceState.getString("RIGHT_RESULT", "")
        rightExp.text = formattedRight
        currentTime = savedInstanceState.getLong("TIMER_RESULT", 0)
        timerFunc(currentTime)
        correctCount = savedInstanceState.getInt("CORRECT_RESULT", 0)
        wrongCount = savedInstanceState.getInt("WRONG_RESULT", 0)
        correctTimerScore

    }

}


