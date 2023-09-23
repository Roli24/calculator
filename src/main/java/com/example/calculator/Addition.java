package com.example.calculator;

import com.example.calculator.interfaces.ICalculator;


public class Addition implements ICalculator {
    @Override
    public double calculate(Double a, Double b) {
        return a+b;
    }
}
