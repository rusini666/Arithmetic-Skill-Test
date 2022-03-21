package com.example.arithmeticskilltest

import kotlin.random.Random

/**
 *
 * This class is used to keep functions with majority of the program logic.
 *
 */
class Functions {

    /**
     *
     * This function generates random mathematical expressions according to the spec.
     *
     */
    fun randomExpression(): ArrayList<String>{
        val operators = listOf("+", "-", "/", "*") // list of operators
        fun randomNumber(): Int = Random.nextInt(1, 20) // range of the terms
        val numberOfTerms = (1..4).random() // min. and max. number of terms
        val arithmeticExp = arrayListOf("${randomNumber()}") // random expression stored as strings in an ArrayList
        var currentValue = arithmeticExp[0].toInt()

        for(i in 1 until numberOfTerms){

            val randomOperator = operators.random() // generating random operators
            var rightOperand = randomNumber()

            if(randomOperator=="*" || randomOperator=="+"){

                var p = when(randomOperator){
                    "*" -> currentValue * rightOperand
                    else -> currentValue + rightOperand
                }

                while(p>100){
                    rightOperand = randomNumber()
                    p = when(randomOperator){
                        "*"->currentValue * rightOperand
                        else->currentValue + rightOperand
                    }
                }
            }else if(randomOperator=="/" && currentValue % rightOperand != 0){
                val factors = mutableListOf(1, currentValue)

                for(k in 2 until currentValue){
                    if(currentValue % k == 0){
                        factors.add(k)
                    }
                }

                rightOperand = factors.random()
            }
            currentValue = when(randomOperator){
                "*" -> currentValue * rightOperand
                "+" -> currentValue + rightOperand
                "-" -> currentValue - rightOperand
                else -> currentValue / rightOperand
            }
            arithmeticExp.add(randomOperator)
            arithmeticExp.add(rightOperand.toString())
        }
        return arithmeticExp // returns an ArrayList with the expression
    }

    /**
     *
     * This function accepts the and formats the expression to have brackets.
     *
     */
    fun getFormattedExpression(arithmeticExp: MutableList<String>): String{
        if(arithmeticExp.size>3){
            val formattedExpression = mutableListOf("(")
            if(arithmeticExp.size>5){
                formattedExpression.add("(")
            }
            for(i in 0 until arithmeticExp.size){
                if(i<3){
                    formattedExpression.add(arithmeticExp[i])
                }else{
                    if(i%2==1){
                        formattedExpression.add(")")
                    }
                    formattedExpression.add(arithmeticExp[i])
                }
            }
            return formattedExpression.joinToString(separator = "")
        }else{
            return arithmeticExp.joinToString(separator = "")
        }
    }

    /**
     *
     * This function accepts the String arithmetic expression,
     * evaluates the expression from left to right and returns the answers.
     *
     */
    fun answer(arithmeticExp: String): Int {
        var num = ""
        var symbol = '+'
        var finalAns = 0

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