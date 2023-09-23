package com.example.calculator;

import com.example.calculator.interfaces.ICalculator;

public class CalculatorFactory {

    public static ICalculator createCalculator(Character operator)
    {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
