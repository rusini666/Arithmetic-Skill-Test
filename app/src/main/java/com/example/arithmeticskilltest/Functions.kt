package com.example.arithmeticskilltest

import kotlin.random.Random

class Functions {

    fun randomExpression(): ArrayList<String>{
        val operators = listOf("+", "-", "/", "*") // list of operators
        fun randomNumber(): Int = Random.nextInt(1, 20)
        val numberOfTerms = (2..4).random()
        val arithmeticExp = arrayListOf("${randomNumber()}")
        var currentValue = arithmeticExp[0].toInt()

        for(i in 1 until numberOfTerms){

            val opt = operators.random() // generating random operators
            var rightOperand = randomNumber()

            if(opt=="*" || opt=="+"){

                var p = when(opt){
                    "*"->currentValue * rightOperand
                    else->currentValue + rightOperand
                }

                while(p>100){
                    rightOperand = randomNumber()
                    p = when(opt){
                        "*"->currentValue * rightOperand
                        else->currentValue + rightOperand
                    }
                }
            }else if(opt=="/" && currentValue % rightOperand != 0){
                val factors = mutableListOf(1, currentValue)

                for(k in 2 until currentValue){
                    if(currentValue % k == 0){
                        factors.add(k)
                    }
                }

                rightOperand = factors.random()
            }
            currentValue = when(opt){
                "*" -> currentValue * rightOperand
                "+" -> currentValue + rightOperand
                "-" -> currentValue - rightOperand
                else -> currentValue / rightOperand
            }
            arithmeticExp.add(opt)
            arithmeticExp.add(rightOperand.toString())
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