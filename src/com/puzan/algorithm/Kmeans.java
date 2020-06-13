package com.puzan.algorithm;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class Kmeans extends JPanel {

    private Double[][] coordinates;

    private int k = 4;

    private boolean loop = true;
    private ArrayList<Double[]> seeds = new ArrayList<>();
    private ArrayList<int[]> clusters = new ArrayList<>();
    private ArrayList<int[]> clusterCheck = new ArrayList<>();

    private ArrayList<Color> colors = new ArrayList<>();
    Graphics g;
    private int factor = 25;

    public Kmeans(Double coordinates[][]) {
        this.coordinates = coordinates;
        for (int i = 0; i < k; i++) {
            clusters.add(new int[]{});
            seeds.add(coordinates[i]);
        }

        super.setPreferredSize(new Dimension(550, 550));

        loadColors();

    }

    public void loadColors() {
        colors.add(new Color(0, 0, 255));
        colors.add(new Color(0, 255, 0));
        colors.add(new Color(0, 255, 255));
        colors.add(new Color(255, 0, 0));
        colors.add(new Color(255, 0, 255));
        colors.add(new Color(255, 255, 0));
        colors.add(new Color(0, 0, 0));
        colors.add(new Color(255, 255, 255));
        
        
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i <= 12; i++) {
            g.setColor(Color.blue);
            g.drawLine(0, i * factor, 300, i * factor);
            g.drawLine(i * factor, 0, i * factor, 300);

        }

        for (int p = 0; p < 15; p++) {
            startClustering();

            for (int i = 0; i < clusters.size(); i++) {
                System.out.println("cluster" + i);
                g.setColor(colors.get(i));
                for (int j = 0; j < clusters.get(i).length; j++) {
                    System.out.print(clusters.get(i)[j] + ",");
                    pointDraw(coordinates[clusters.get(i)[j]], g);
                }
                System.out.println("");
            }

        }

    }

    public Double calculateDistance(Double[] xy1, Double[] xy2) {
//        System.out.println(xy1[0]+","+xy1[1]);
//        System.out.println(xy2[0]+","+xy2[1]);

        Double result = Math.sqrt(((xy2[1] - xy1[1]) * (xy2[1] - xy1[1])) + ((xy2[0] - xy1[0]) * (xy2[0] - xy1[0])));
        return result;
    }

    public void pointDraw(Double[] coordinate, Graphics g) {
        g.fillOval((int) Math.round(coordinate[0]) * factor - 10, 300 - (int) Math.round(coordinate[1]) * factor - 10, 20, 20);

    }

//    public static void main(String[] args) {
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame frame = new JFrame("Draw Line");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setBackground(Color.white);
//        frame.setSize(200, 200);
//
//        DrawLine panel = new DrawLine();
//
//        frame.add(panel);
//        
//        frame.setVisible(true);
//
//    }
    public void startClustering() {

        clusters.clear();
        for (int i = 0; i < k; i++) {
            clusters.add(new int[]{});
        }

        for (int i = 0; i < coordinates.length; i++) {
//            Double d1 = calculateDistance(seeds.get(0), coordinates[i]);
//            Double d2 = calculateDistance(seeds.get(1), coordinates[i]);
//            Double d3 = calculateDistance(seeds.get(2), coordinates[i]);

            int index = 0;
            Double d = 100.0;
            for (int m = 0; m < seeds.size(); m++) {
                if (d >= calculateDistance(seeds.get(m), coordinates[i])) {
                    d = calculateDistance(seeds.get(m), coordinates[i]);
                    index = m;
                }
            }

            int[] s = clusters.get(index);
            int[] newS = new int[s.length + 1];

            for (int j = 0; j < s.length; j++) {
                newS[j] = s[j];
            }
            newS[s.length] = i;
            clusters.set(index, newS);
        }

        try {
            compareClusters();
        } catch (Exception ex) {
            loop = true;
        }

        clusterCheck = clusters;

        seeds.clear();
        for (int q = 0;
                q < clusters.size();
                q++) {
            Double sumX = 0.0, sumY = 0.0;
            for (int j = 0; j < clusters.get(q).length; j++) {
                sumX = sumX + coordinates[clusters.get(q)[j]][0];
                sumY = sumY + coordinates[clusters.get(q)[j]][1];

            }
            seeds.add(new Double[]{sumX / clusters.get(q).length, sumY / clusters.get(q).length});
//            System.out.println(sumX / k + "," + sumY / k);

        }
    }

    public void compareClusters() {
        loop = true;
        for (int i = 0; i < clusters.size(); i++) {
            if (!Arrays.equals(clusters.get(i), clusterCheck.get(i))) {
                loop = true;
                break;
            }
        }

    }
}

