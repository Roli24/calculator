package com.example.calculator;

import com.example.calculator.interfaces.ICalculator;

import java.util.Stack;


public class Utility {
    public static double evaluateExpression(String expression) {
        // Initialize operators and operands stacks
        expression = expression.trim();
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        // 2 + 3 * 4
        // Split the expression into tokens (numbers, operators, parentheses)
        //String[] tokens = expression.split(" ");

        for (Character input : expression.toCharArray()) {
            String token = input.toString();
            // If token is a number, push it to the values stack
            if (token.matches("\\d+(\\.\\d+)?")) {
                values.push(Double.parseDouble(token));
            }
            else if (token.length() == 1 && "+-*/^".contains(token)) {
                char operator = token.charAt(0);
                // Pop and calculate while operators have higher precedence
                while (!operators.isEmpty() && hasPrecedence(operator, operators.peek())) {
                    double operand2 = values.pop();
                    double operand1 = values.pop();
                    ICalculator calculator = CalculatorFactory.createCalculator(operators.pop());
                    values.push(calculator.calculate(operand1, operand2));
                }
                // operators -> +
                operators.push(operator);
            }
            else if (token.equals("(")) {
                operators.push('(');
            }
            else if (token.equals(")")) {
                // Pop and calculate until the matching '(' is encountered
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double operand2 = values.pop();
                    double operand1 = values.pop();
                    ICalculator calculator = CalculatorFactory.createCalculator(operators.pop());
                    values.push(calculator.calculate(operand1, operand2));
                }
                operators.pop(); // Pop the '('
            }
        }

        // Pop and calculate any remaining operators
        while (!operators.isEmpty()) {
            double operand2 = values.pop();
            double operand1 = values.pop();
            ICalculator calculator = CalculatorFactory.createCalculator(operators.pop());
            values.push(calculator.calculate(operand1, operand2));
        }

        // The result should be at the top of the values stack
        return values.pop();
    }

    private static boolean hasPrecedence(char operator1, char operator2) {
        // Check if operator1 has higher or equal precedence than operator2
        int precedence1 = getPrecedence(operator1);
        int precedence2 = getPrecedence(operator2);
        return precedence1 >= precedence2;
    }

    private static int getPrecedence(char operator) {
        // Define the precedence of operators
        final int DIVISON_PRECEDENCE = 1;
        final int MULTIPLY_PRECEDENCE = 2;
        final int SUBTRACTION_PRECEDENCE = 3;
        final int PLUS_PRECEDENCE = 4;


        switch (operator) {
            case '+':
                return PLUS_PRECEDENCE;
            case '-':
                return SUBTRACTION_PRECEDENCE;
            case '*':
                return MULTIPLY_PRECEDENCE;
            case '/':
                return DIVISON_PRECEDENCE;
            default:
                return 0; // Default precedence (lowest)
        }
    }
}
