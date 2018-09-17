package Algorithms;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pujan
 */
public class Knn extends JPanel {

    private Double[][] coordinates;

    ArrayList<Color> colors = new ArrayList<>();

    public Knn(Double[][] coordinates) {
        this.coordinates = coordinates;
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

    private Double k = 4.0;

    public void paintComponent(Graphics g) {

        for (int i = 0; i <= 20; i++) {
            g.setColor(Color.blue);
            g.drawLine(0, i * 25, 500, i * 25);
            g.drawLine(i * 25, 0, i * 25, 500);

        }

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
        list.add(0);
        int clusterSize = 0;

        for (int i = 0; i < coordinates.length; i++) {
            Double d = k;
            int index = 0;
            boolean addCluster=true;
            for (int j = 0; j < i; j++) {
                if (d >=this.calculateDistance(coordinates[i], coordinates[j])) {
                    d = this.calculateDistance(coordinates[i], coordinates[j]);
                    index = j;
                    addCluster=false;
                }
            }
            if (addCluster) {
                //new Cluster
                ArrayList<Integer> newCluster = new ArrayList<>();
                newCluster.add(i);
                clusters.add(newCluster);
            } else {
                //add to existing cluster
                if (index < clusters.size()) {
                    clusters.get(index).add(i);
                } else {
                    //find out the actual index in belonging to the cluster;
                    int actualIndex = 0;
                    for (int p = 0; p < clusters.size(); p++) {
                        if (clusters.get(p).contains(index)) {
                            actualIndex = p;
                        }
                    }
                    clusters.get(actualIndex).add(i);
                }
            }
        }
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("cluster" + i);
            g.setColor(colors.get(i));

            for (int j = 0; j < clusters.get(i).size(); j++) {
                System.out.print(clusters.get(i).get(j) + ",");
                pointDraw(coordinates[clusters.get(i).get(j)], g);
            }
            System.out.println("");
        }

    }

    public void pointDraw(Double[] coordinate, Graphics g) {
        g.fillOval((int) Math.round(coordinate[0]) * 25 - 10, 500 - (int) Math.round(coordinate[1]) * 25 - 10, 20, 20);

    }

    public Double calculateDistance(Double[] xy1, Double[] xy2) {
        System.out.println(xy1[0]+","+xy1[1]);
//        System.out.println(xy2[0]+","+xy2[1]);

        Double result = Math.sqrt(((xy2[1] - xy1[1]) * (xy2[1] - xy1[1])) + ((xy2[0] - xy1[0]) * (xy2[0] - xy1[0])));
        return result;
    }

//    public static void main(String[] args) {
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame frame = new JFrame("Knn");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setBackground(Color.white);
//        frame.setSize(200, 200);
//
//        Knn panel = new Knn();
//
//        frame.add(panel);
//
//        frame.setVisible(true);
//
//    }
}
