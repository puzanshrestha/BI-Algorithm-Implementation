package com.puzan.ANN.NeuralNetwork;

import com.puzan.ANN.MatrixDimensionException;

public class NNMain {
    public static void main(String[] args) throws MatrixDimensionException {
        NeuralNetwork nn = new NeuralNetwork(2, 2, 1);

        float input[] = new float[]{1, 0};
        Matrix output = nn.feedForward(input);
        output.print();
    }
}
