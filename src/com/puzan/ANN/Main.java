package com.puzan.ANN;

import javax.swing.*;
import java.awt.*;

public class Main extends javax.swing.JFrame {


    private static JPanel jPanel1;

    public Main() {
        initComponents();
        GraphView drawLine = new GraphView();
        jPanel1.setBackground(Color.BLACK);
        jPanel1.add(drawLine);
        getContentPane().add(jPanel1);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new com.puzan.ANN.Main().setVisible(true);
            }
        });

    }

    private static void initComponents() {
        jPanel1 = new javax.swing.JPanel();
    }
}
