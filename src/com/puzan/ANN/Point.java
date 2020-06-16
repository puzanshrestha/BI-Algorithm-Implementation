package com.puzan.ANN;

public class Point {
    public float x;
    public float y;
    public float bias = 1;
    public int label;


    public float function(float x) {
        return 0.2f * x + 0.01f;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = Utility.random(-1, 1);
        this.y = Utility.random(-1, 1);

        float lineY = function(x);
        if (y >= lineY) {
            label = 1;
        } else {
            label = -1;
        }
    }

    public int getX() {
        float pixelFactor = x <= 0 ? 1 + x : x;
        return (int) (x <= 0 ? pixelFactor * Utility.WIDTH / 2 : Utility.WIDTH / 2 + pixelFactor * Utility.WIDTH / 2);
    }

    public int getY() {
        float pixelFactor = y <= 0 ? 1 + y : y;
        return (int) (y <= 0 ? pixelFactor * Utility.HEIGHT / 2 : Utility.HEIGHT / 2 + pixelFactor * Utility.HEIGHT / 2);
    }
}
