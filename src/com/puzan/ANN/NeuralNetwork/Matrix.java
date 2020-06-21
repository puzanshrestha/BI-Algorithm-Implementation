package com.puzan.ANN.NeuralNetwork;

import com.puzan.ANN.MatrixDimensionException;
import com.puzan.ANN.Utility;

public class Matrix implements MatrixOperations {

    public int rows;
    public int cols;
    private float data[][];

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new float[rows][cols];
    }

    public static Matrix fromArray(float[] inputArray) {
        Matrix input = new Matrix(inputArray.length, 1);
        for (int i = 0; i < input.rows; i++) {
            input.data[i][0] = inputArray[i];
        }
        return input;
    }


    @Override
    public void randomize() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = Utility.random(0, 10);
            }
        }
    }


    static public Matrix multiply(Matrix a, Matrix b) throws MatrixDimensionException {
        if (a.cols != b.rows)
            throw new MatrixDimensionException();

        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                float sum = 0f;
                for (int k = 0; k < result.rows; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    /**
     * @param n = Scalar value that multiplies all the elements of matrix
     */
    @Override
    public void multiply(float n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] *= n;
            }
        }
    }

    /**
     * @param m = Matrix B
     * @return Matrix result  = A * B (A=this matrix, B = m)
     * @throws MatrixDimensionException Cols of First Matrix A must be equal to Rows of Second Matrix B
     */
    @Override
    public Matrix multiply(Matrix m) throws MatrixDimensionException {

        if (this.cols != m.rows)
            throw new MatrixDimensionException();

        Matrix result = new Matrix(this.rows, m.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                float sum = 0f;
                for (int k = 0; k < result.rows; k++) {
                    sum += this.data[i][k] * m.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    /**
     * @param n= Scalar value that adds to all the elements this Matrix;
     */
    @Override
    public void add(float n) {

    }

    /**
     * Add Two matrices A and B as Result R = Matrix A + Matrix B (Matrix A = this Matrix)
     * HADAMARD Matrix Operation
     *
     * @param m = Matrix B
     * @return
     * @throws MatrixDimensionException For Addition,
     *                                  Rows of Matrix A must be equal to Rows of Matrix B
     *                                  Cols of Matrix A must be equal to Cols of Matrix B
     */
    @Override
    public Matrix add(Matrix m) throws MatrixDimensionException {
        if (this.rows != m.rows & this.cols != m.cols)
            throw new MatrixDimensionException();

        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.data[i][j] += m.data[i][j];
            }
        }
        return result;
    }

    @Override
    public Matrix transpose() {
        Matrix result = new Matrix(this.cols, this.rows);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }


    @Override
    public void computeSigmoid() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = sigmoid(this.data[i][j]);
            }
        }
    }

    private float sigmoid(float x) {
        return (float) (1 / (1 + Math.exp(-x)));
    }

    @Override
    public void setData(float[][] data) {
        this.data = data;
    }


    @Override
    public void print() {
        System.out.println("--------------------------");
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
