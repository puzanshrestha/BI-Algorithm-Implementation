package com.puzan.ANN;

public class Main {

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron();
        System.out.println(perceptron.guess(new float[]{-1f, 0.5f}));
    }
}
