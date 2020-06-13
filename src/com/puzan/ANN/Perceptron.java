package com.puzan.ANN;

import java.util.Random;

public class Perceptron {
    float[] weights = new float[2];

    public Perceptron() {
        for (int i = 0; i < weights.length; i++)
            weights[i] = random(-1, 1);
    }


    int guess(float[] inputs) {
        int sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }

        return sign(sum);
    }

    //Activation Function
    private int sign(int sum) {
        return sum >= 0 ? 1 : -1;
    }

    public float random(float min, float max) {
        return (float) (min + Math.random() * (max - min));
    }
}
