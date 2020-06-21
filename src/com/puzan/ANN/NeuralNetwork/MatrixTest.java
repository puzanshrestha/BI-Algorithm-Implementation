package com.puzan.ANN.NeuralNetwork;


import com.puzan.ANN.MatrixDimensionException;

public class MatrixTest {

    public static void main(String[] args) throws MatrixDimensionException {
        Matrix m1 = new Matrix(1, 3);

        float data[][] = new float[m1.rows][m1.cols];
//        m1.setData(data);
        m1.randomize();
        m1.print();

        m1.transpose().print();


        Matrix m2 = new Matrix(3, 2);
        float data2[][] = new float[m2.rows][m2.cols];
//        m2.setData(data2);
        m2.randomize();
        m2.print();
        System.out.println();
        Matrix result = m1.multiply(m2);

        result.print();

    }

}