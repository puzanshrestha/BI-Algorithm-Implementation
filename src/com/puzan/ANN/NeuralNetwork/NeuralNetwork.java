package com.puzan.ANN.NeuralNetwork;

import com.puzan.ANN.MatrixDimensionException;

public class NeuralNetwork {

    private int input_nodes;
    private int hidden_nodes;
    private int output_nodes;

    private Matrix weights_ih;
    private Matrix weights_ho;

    private Matrix bias_h;
    private Matrix bias_o;


    public NeuralNetwork(int input_nodes, int hidden_nodes, int output_nodes) {
        this.input_nodes = input_nodes;
        this.hidden_nodes = hidden_nodes;
        this.output_nodes = output_nodes;

        this.weights_ih = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weights_ho = new Matrix(this.output_nodes, this.hidden_nodes);

        this.bias_h = new Matrix(this.hidden_nodes, 1);
        this.bias_o = new Matrix(this.output_nodes, 1);

        this.weights_ih.randomize();
        this.weights_ho.randomize();
        this.bias_h.randomize();
        this.bias_o.randomize();

    }

    public Matrix feedForward(float[] inputArray) throws MatrixDimensionException {
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(this.weights_ih, inputs);
        hidden.add(bias_h);
        hidden.computeSigmoid();

        Matrix outputs = Matrix.multiply(this.weights_ho, hidden);
        outputs.add(bias_o);
        outputs.computeSigmoid();

        return outputs;

    }
}
