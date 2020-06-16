package com.puzan.ANN;

public class Perceptron {
    final float lr = 0.001f;
    float[] weights = new float[3];

    public Perceptron() {
        for (int i = 0; i < weights.length; i++)
            weights[i] = Utility.random(-1, 1);
    }

    int guess(float[] inputs) {
        float sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sign(sum);
    }

    //Activation Function
    private int sign(float sum) {
        return sum >= 0 ? 1 : -1;
    }


    public float guessY(float x) {
        return -(weights[2] / weights[1]) - ((weights[0] / weights[1] * x));
    }

    public void train(float[] inputs, int target) {
        int guess = guess(inputs);
        int error = target - guess;

        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * lr;
        }
    }

}
