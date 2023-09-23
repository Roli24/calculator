package com.example.calculator;


import java.util.Scanner;

import static com.example.calculator.Utility.evaluateExpression;

public class Calculator {


    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter your input to calculate i.e 2+3*4");
            String value = sc.nextLine();
            double result = evaluateExpression(value);
            System.out.println("Result: " + result);
            System.out.println("Do you want to save result");
        }
        catch (Exception ex) {
            System.out.println("Can not perform operation " + ex.getMessage());
        }


    }
}
