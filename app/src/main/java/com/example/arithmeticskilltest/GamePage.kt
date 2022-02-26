package com.example.arithmeticskilltest

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GamePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        val greaterBtn =  findViewById<Button>(R.id.greaterBtn)
        val equalsBtn =  findViewById<Button>(R.id.equalsBtn)
        val lessBtn = findViewById<Button>(R.id.lessBtn)
        val leftExp= findViewById<TextView>(R.id.leftExp)
        val rightExp = findViewById<TextView>(R.id.rightExp)

        var num1 = (0..20).random()
        var num2 = (0..20).random()
        var num3 = (0..20).random()
        var num4 = (0..20).random()


        var numberOfExpressions = 10
        var minExpressionElements = 2
        var maxExpressionElements = 4
        var minRange = 1
        var maxRange = 20
        val list = ArrayList<Int?>()
        val operators = arrayOf("+","-","/","*")
        var correctAns = 0
        val index: Int = (0..3).random()
        when (index) {
            0 -> correctAns = num1 + num2
            1 -> correctAns = num1 - num2
            2 -> correctAns = num1 / num2
            3 -> correctAns = num1 * num2
        }

        when (index) {
            0 -> correctAns = num1 + num2
            1 -> correctAns = num1 - num2
            2 -> correctAns = num1 / num2
            3 -> correctAns = num1 * num2
        }

        leftExp.text = "$num1" + " " + operators.get(index) + " " + "$num2"





//        fun twoExpressions(){
//            for (i in 0..numberOfExpressions)
//                list.add("$num1 $operator.get((0..3).random()) $num2")
//        }
//        fun threeExpressions(left: Int, right: Int)
//        fun fourExpressions(left: Int, right: Int)

        greaterBtn.setOnClickListener {

        }
        equalsBtn.setOnClickListener {

        }
        lessBtn.setOnClickListener {

        }
    }
}

private fun <E> ArrayList<E>.add(element: String) {

}


