package com.example.app;

public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int sum = calc.add(5, 3);
        System.out.println("5 + 3 = " + sum);
    }
}
