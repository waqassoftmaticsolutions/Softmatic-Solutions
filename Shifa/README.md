# Basic Calculator in Kotlin

## Overview

This project is a basic calculator implemented in Kotlin. It can perform the following operations:
- Addition
- Subtraction
- Multiplication
- Division
- Modulo

Users can input a mathematical expression as a string, and the calculator will evaluate and return the result.

## Features

- **Precedence Handling:** The calculator respects operator precedence.
- **Operator Application:** Handles the application of operations between operands.
- **Evaluation:** Uses stacks to manage operators and operands, ensuring correct evaluation order.
- **Interactive Loop:** Allows users to repeatedly enter expressions to be evaluated.

## Code Structure

The project contains four main functions:

1. **checkPrecedence(op: Char): Int**
   - Checks and returns the precedence of the given operator.

2. **applyOperator(op: Char, op1: Double, op2: Double): Double**
   - Applies the given operator to the two operands and returns the result.

3. **evaluate(expression: String)**
   - Evaluates the given mathematical expression using two stacks: one for operators and one for operands.

4. **main()**
   - Handles user interaction, running the calculator in a loop based on user input.

## Usage

1. Clone the repository.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Android Studio).
3. Run the `main` function.
4. Enter a mathematical expression when prompted and see the result.
5. Choose whether to continue or exit the calculator.
