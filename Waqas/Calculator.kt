import java.util.*
import kotlin.text.toInt
fun evaluate(expression: String): Double {
    val operands = Stack<Double>()
    val operators = Stack<Char>()
    val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)
    var i = 0
    while (i < expression.length) {
        val ch = expression[i]
        if (ch.isDigit()) {
            var num = 0
            while (i < expression.length && expression[i].isDigit()) {
                num = num * 10 + (expression[i] - '0')
                i++
            }
            operands.push(num.toDouble())
            continue
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            while (!operators.isEmpty() && precedence[operators.peek()] ?: 0 >= precedence[ch] ?: 0) {
                val operator1 = operators.pop()
                val operand2 = operands.pop()
                val operand1 = operands.pop()
                operands.push(applyOperator(operator1, operand1, operand2))
            }
            operators.push(ch)
        }
        i++
    }
    while (!operators.isEmpty()) {
        val operator1 = operators.pop()
        val operand2 = operands.pop()
        val operand1 = operands.pop()
        operands.push(applyOperator(operator1, operand1, operand2))
    }
    return operands.pop()
}
fun applyOperator(operator1: Char, operand1: Double, operand2: Double): Double {
    return when (operator1) {
        '+' -> operand1 + operand2
        '-' -> operand1 - operand2
        '*' -> operand1 * operand2
        '/' -> operand1 / operand2
        else -> throw IllegalArgumentException("Unknown operator: $operator1")
    }
}
fun choiceInput():Int{
  println("1) Use two operands and 1 operator only")
  println("2) Modulus of two operands")
  println("3) Use an Expression")
  println("4) Exit")
  print("Enter your chooce: ")
  val reader = Scanner(System.`in`)
  var choice = reader.nextInt()
  return choice
}
fun main() {
    do {
      var choice = choiceInput()
      if(choice == 1){
        println("1) Add two numbers")
        println("2) Subtract two numbers")
        println("3) Divide two numbers")
        println("4) Multiply two numbers")
        print("Enter your choice: ")
         val reader = Scanner(System.`in`)
        var choice1 = reader.nextInt()
        print("Enter first number: ")
        var num1 = reader.nextDouble()
        print("Enter 2nd number: ")
        var num2 = reader.nextDouble()
        //val function = mapOf(1 to '+', 2 to '-', 3 to '/', 4 to '*', 5 to '%')
        when(choice1){
          1 -> println("Result of $num1 + $num2 is ${num1+num2}")
          2 -> println("Result of $num1 - $num2 is ${num1-num2}")
          3 -> println("Result of $num1 / $num2 is ${num1/num2}")
          4 -> println("Result of $num1 * $num2 is ${num1*num2}")
          else -> println("Unknown Operator")
        }
      }else if(choice == 2){
        val reader = Scanner(System.`in`)
        print("Enter first number: ")
        var num1 = reader.nextInt()
        print("Enter 2nd number: ")
        var num2 = reader.nextInt()
        println("Result of $num1 % $num2 is ${num1%num2}")
      }else if(choice == 3){
        print("Enter your expression in this format(1+2-3/2): ")
        val expression = readLine()
        if(expression!=null){
          val result = evaluate(expression)
          println("Result of '$expression' = $result")
        }
        else{
          println("Expression can not be null")
        }
      }
      else if(choice == 4){
        println("Exiting the program!")
      }
      else{
        println("Wrong Choice!")
      }
    } while(choice != 4)
}
