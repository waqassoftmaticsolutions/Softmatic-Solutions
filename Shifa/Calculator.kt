import java.util.ArrayDeque

fun checkPrecedence(op: Char): Int {
    return if (op == '+' || op == '-') {
        1
    } else if (op == '*' || op == '/' || op == '%') {
        2
    } 
    else {
        0 // Default precedence for unrecognized operators
    }
}

fun applyOperator(op: Char, op1: Double, op2: Double): Double {
    if (op=='+'){
        return op1 + op2
    }
    else if(op=='-'){
        return op1 - op2
    }else if(op=='*'){
        return op1*op2
    }else if(op=='/'){
        return op1 / op2
    }else if(op=='%'){
        return op1 % op2.toDouble()
    }
    else{
        return 0.0
    }
}
fun evaluate(expression : String){
    val stackOperand = ArrayDeque<Double>()
    val stackOperator = ArrayDeque<Char>()

    var i = 0

    while (i < expression.length) {
        val ch = expression[i]
        // If it's an Digit
        if (ch.isDigit()) {
            var num = 0.0
            while (i < expression.length && expression[i].isDigit()) {
                num = num * 10 + (expression[i] - '0')
                i++
            }
            stackOperand.push(num)
            continue
        }

        // If it's an operator
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch=='%') {
            while (stackOperator.isNotEmpty() && checkPrecedence(ch) <= checkPrecedence(stackOperator.peek())) {
                val op2 = stackOperand.pop()
                val op1 = stackOperand.pop()
                val op = stackOperator.pop()
                val result = applyOperator(op, op1, op2)
                stackOperand.push(result)
            }
            stackOperator.push(ch)
        }
        i++
    }


    while (stackOperator.isNotEmpty()) {
        val op2 = stackOperand.pop()
        val op1 = stackOperand.pop()
        val op = stackOperator.pop()
        val result = applyOperator(op, op1, op2)
        stackOperand.push(result)
    }

    println("Result: ${stackOperand.pop()}")
}

fun main() {
    var flag: Boolean;
    do{
        println("         ********************")
        println("");
        print("Enter a mathematical expression: ")
        val expression = readLine()!!.trim()
        evaluate(expression)
        println("")
        println("          ********************")
        println("")
        print("Do you wanna calculate again : ")
        val input=readLine()
        if(input=="yes" || input=="YES"){
            flag=true
        }else{
            flag=false
        }
    }while(flag)
}
