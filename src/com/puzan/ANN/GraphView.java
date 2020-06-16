package com.puzan.ANN;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class GraphView extends JPanel {

    Point[] points = new Point[500];
    Perceptron perceptron;

    public GraphView() {
        super.setBackground(Color.BLACK);
        super.setPreferredSize(new Dimension(560, 560));
        initialize();
    }

    private void initialize() {
        perceptron = new Perceptron();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.YELLOW);
//        g.drawLine(0, 0, Utility.WIDTH, Utility.HEIGHT);
        g.drawRect(0, 0, Utility.WIDTH, Utility.HEIGHT);

        Point p = new Point();

        g.setColor(Color.BLUE);
        Point p1 = new Point(-1, p.function(-1));
        Point p2 = new Point(1, p.function(1));
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

        g.setColor(Color.RED);
        p1 = new Point(-1, perceptron.guessY(-1));
        p2 = new Point(1, perceptron.guessY(1));
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

        for (int i = 0; i < points.length; i++) {
            if (points[i].label == 1)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.CYAN);
            g.drawRect((int) (points[i].getX()), (int) points[i].getY(), 3, 3);

            if (perceptron.guess(new float[]{points[i].x, points[i].y, points[i].bias}) != points[i].label) {
                g.setColor(Color.RED);
                g.drawRect((int) points[i].getX(), (int) points[i].getY(), 10, 10);
            }
        }

        //Training perceptron
        for (int i = 0; i < points.length; i++) {
            perceptron.train(new float[]{points[i].x, points[i].y, points[i].bias}, points[i].label);
        }

        CompletableFuture.delayedExecutor(500, TimeUnit.MILLISECONDS).execute(() -> {
            repaint();
        });

    }
}
