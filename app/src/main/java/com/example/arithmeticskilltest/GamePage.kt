package com.example.arithmeticskilltest

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class GamePage : AppCompatActivity() {
    val correctCount = 0
    val wrongCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        val greaterBtn =  findViewById<Button>(R.id.greaterBtn)
        val equalsBtn =  findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        val leftExp = findViewById<TextView>(R.id.leftExp)
        val rightExp = findViewById<TextView>(R.id.rightExp)
        val resultDisplay = findViewById<TextView>(R.id.resultDisplay)
        val timer = findViewById<TextView>(R.id.timer)



        val finish = Intent(this, GameOver::class.java)
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
//            resultDisplay.text = ""


            val left = randomExpression()
            val right = randomExpression()
            leftExp.text = left
            rightExp.text = right

            greaterBtn.setOnClickListener {
                if(answer(left) > answer(right)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    mainFunc()
                }else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    mainFunc()
                }
            }
            equalsBtn.setOnClickListener {
                if(answer(left) == answer(right)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    mainFunc()
                }else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    mainFunc()
                }
            }
            lessBtn.setOnClickListener {
                if(answer(left) < answer(right)) {
                    resultDisplay.text = "CORRECT!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.green))
                    mainFunc()
                }else {
                    resultDisplay.text = "WRONG!"
                    resultDisplay.setTextColor(ContextCompat.getColor(this, R.color.red))
                    mainFunc()
                }
            }
        }
        mainFunc()

    }

    private val operators = listOf("+", "-", "/", "*")

    private fun randomNumber(): Int = Random.nextInt(1, 20)

    private fun randomExpression(): String {
        val numberOfExps = Random.nextInt(1, 4)
        val arithmeticExp = mutableListOf("${randomNumber()}")

        repeat(numberOfExps) {
            arithmeticExp.add(operators.random())
            arithmeticExp.add("${randomNumber()}")
        }

        return arithmeticExp.joinToString(separator = "")
    }

    private fun answer(arithmeticExp: String): Int {
        var num: String = ""
        var symbol: Char = '+'
        var finalAns: Int = 0

        for(i in arithmeticExp) {
            if (i in '0'..'9')
                num += i
            else {
                when(symbol){
                    '+' -> finalAns += Integer.parseInt(num)
                    '-' -> finalAns -= Integer.parseInt(num)
                    '*' -> finalAns *= Integer.parseInt(num)
                    '/' -> finalAns /= Integer.parseInt(num)
                }
                num = ""
                symbol = i
            }
        }
        when(symbol){
            '+' -> finalAns += Integer.parseInt(num)
            '-' -> finalAns -= Integer.parseInt(num)
            '*' -> finalAns *= Integer.parseInt(num)
            '/' -> finalAns /= Integer.parseInt(num)
        }
        return finalAns
    }

}


