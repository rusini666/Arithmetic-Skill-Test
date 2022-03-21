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
    var currentTime : Long = 0 // current time in countdowntimer when running
    private lateinit var count_down_timer : CountDownTimer // countdowntimer
    var formattedLeft = ""
    var formattedRight = ""
    private lateinit var leftExp : TextView // displays left expression
    private lateinit var rightExp : TextView
    lateinit var timer : TextView // displays timer
    lateinit var finish : Intent
    var correctTimerScore = 0
    private lateinit var left: ArrayList<String>
    private lateinit var right: ArrayList<String>
    private lateinit var leftStringExp : String
    private lateinit var rightStringExp : String
    var leftAnswer = 0
    var rightAnswer = 0
    private lateinit var resultDisplay : TextView // displays result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        timer = findViewById(R.id.timer)
        val greaterBtn = findViewById<Button>(R.id.greaterBtn)
        val equalsBtn = findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        leftExp = findViewById(R.id.leftExp)
        rightExp = findViewById(R.id.rightExp)
        resultDisplay = findViewById(R.id.resultDisplay)
        finish = Intent(this, GameOver::class.java) // intent starting a new activity
        val f1 = Functions() // create an instance of functions

        /**
         *
         * This function adds bonus time by cancelling the running countdowntimer.
         *
         */
        fun bonusTime() {
            if (correctTimerScore % 5 == 0 && correctTimerScore != 0) {
                correctTimerScore = 0
                count_down_timer.cancel()
                if(currentTime > 40000)
                    timerFunc(50000)
                else
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

            left = f1.randomExpression()
            right = f1.randomExpression()

            leftStringExp = left.joinToString(separator = "")
            rightStringExp = right.joinToString(separator = "")

            println("leftString: $leftStringExp")
            println("rightString: $rightStringExp")

            bonusTime()

            formattedLeft = f1.getFormattedExpression(left)
            formattedRight = f1.getFormattedExpression(right)

            leftExp.text = formattedLeft
            rightExp.text = formattedRight

            leftAnswer = f1.answer(leftStringExp)
            rightAnswer = f1.answer(rightStringExp)

            println("leftAnswer: $leftAnswer")
            println("rightAnswer: $rightAnswer")
            println("----------------------------------------------------")

        }

            greaterBtn.setOnClickListener {
                if (leftAnswer > rightAnswer) {
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
                if (leftAnswer == rightAnswer) {
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
                if (leftAnswer < rightAnswer) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    correctCount++
                    correctTimerScore++
                    mainFunc()
                } else {
                    resultDisplay.text= "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    wrongCount++
                    mainFunc()
                }
            }
        mainFunc()
    }


    /**
     *
     * This function starts the timer.
     *
     */
    private fun timerFunc(milliseconds: Long){

        count_down_timer = object: CountDownTimer(milliseconds, 1000){
            override fun onTick(millisUntilFinished: Long) {
                currentTime = millisUntilFinished
                timer.text = "" + currentTime / 1000
            }

            override fun onFinish() {
                if(timer.text=="0")
                    goToScore()
            }
        }.start()
    }

    /**
     *
     * This function passes score data to GameOver activity.
     *
     */
    private fun goToScore() {
        finish.putExtra("correct", correctCount.toString())
        finish.putExtra("wrong", wrongCount.toString())
        startActivity(finish)
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
        outState.putString("LEFT_STRING_EXP",leftStringExp)
        outState.putString("RIGHT_STRING_EXP", rightStringExp)
        outState.putStringArrayList("RIGHT", right)
        outState.putStringArrayList("LEFT", left)
        outState.putInt("LEFT_ANS",leftAnswer)
        outState.putInt("RIGHT_ANS",rightAnswer)
        count_down_timer.cancel()
     }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        formattedLeft = savedInstanceState.getString("LEFT_RESULT", "")
        leftExp.text = formattedLeft
        formattedRight = savedInstanceState.getString("RIGHT_RESULT", "")
        rightExp.text = formattedRight

        currentTime = savedInstanceState.getLong("TIMER_RESULT", 0)
        count_down_timer.cancel()
        timerFunc(currentTime)

        correctCount = savedInstanceState.getInt("CORRECT_RESULT", 0)
        wrongCount = savedInstanceState.getInt("WRONG_RESULT", 0)

        correctTimerScore = savedInstanceState.getInt("TIMER_CORRECT", 0)
        left = savedInstanceState.getStringArrayList("LEFT") as ArrayList<String>
        right = savedInstanceState.getStringArrayList("RIGHT") as ArrayList<String>
        leftStringExp = savedInstanceState.getString("LEFT_STRING_EXP", "")
        rightStringExp = savedInstanceState.getString("RIGHT_STRING_EXP","")
        leftAnswer = savedInstanceState.getInt("LEFT_ANS",0)
        rightAnswer = savedInstanceState.getInt("RIGHT_ANS",0)
    }

}


