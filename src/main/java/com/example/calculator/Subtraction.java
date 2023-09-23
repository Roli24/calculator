package com.example.calculator;

import com.example.calculator.interfaces.ICalculator;

import java.util.List;

public class Subtraction implements ICalculator {

   @Override
    public double calculate(Double a, Double b) {
        return a-b;
    }
}
