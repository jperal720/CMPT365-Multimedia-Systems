package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Main extends JPanel{

    private List<Double> scores;
    private int padding = 20;
    private double labelPadding = 12;

    public Main(List<Double> scores) {
        this.scores = scores;
    }

    private void painComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - (2 * padding) - labelPadding) / (getMaxScore() - getMinScore());

    }

    private double getMaxScore(){
        double maxScore = Double.MIN_VALUE;

        for(Double score: scores){
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    private double getMinScore(){
        double minScore = Double.MAX_VALUE;

        for(Double score: scores){
            minScore = Math.min(minScore, score);
        }

        return minScore;
    }

    private static void createAndShowGui(){

        List<Double> scores = new ArrayList();
        Random rand = new Random();

        int max = 20;
        int min = 8;

        for(int i = 0; i < max; i++){
             scores.add((double)rand.nextDouble() * max);

        }

        Main mainPanel = new Main(scores);
        mainPanel.setPreferredSize(new Dimension(700,600));
        JFrame frame = new JFrame("GRAPH");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGui();
            }
        });

    }
}
