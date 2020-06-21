package com.puzan.ANN.NeuralNetwork;

import com.puzan.ANN.MatrixDimensionException;

public interface MatrixOperations {
    void multiply(float n);

    Matrix multiply(Matrix m) throws MatrixDimensionException;

    void add(float n);

    Matrix add(Matrix m) throws MatrixDimensionException;

    void randomize();

    Matrix transpose();

    void computeSigmoid();

    void setData(float data[][]);

    void print();
}
