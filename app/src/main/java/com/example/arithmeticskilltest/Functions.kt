package com.example.arithmeticskilltest

import kotlin.random.Random

class Functions {

    fun randomExpression(): ArrayList<String>{
        val operators = listOf("+", "-", "/", "*")
        fun randomNumber(): Int = Random.nextInt(1, 20)
        val numberOfExps = Random.nextInt(1, 4)
        val arithmeticExp = arrayListOf("${randomNumber()}")

        repeat(numberOfExps) {
            arithmeticExp.add(operators.random())
            arithmeticExp.add("${randomNumber()}")
        }
        return arithmeticExp
    }

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
}