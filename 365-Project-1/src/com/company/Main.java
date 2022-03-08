package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Main extends JPanel{

    private List<Double> scores;
    private int padding = 20;
    private int labelPadding = 12;
    private int numberYDivisions = 13;
    private int pointWidth = 10 ;
    private Color gridColor = new Color(200, 200, 200);

    public Main(List<Double> scores) {
        this.scores = scores;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - (2 * padding) - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoint = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i) * yScale) + padding);

            graphPoint.add(new Point(x1, y1));

        }

        g2.setColor(Color.white);
        g2.fillRect(padding + labelPadding, padding,
                getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.blue);

        for(int i = 0; i < numberYDivisions; i++){
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);

            int y1 = y0;
            if(scores.size() > 0){
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.black);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore())) *
                        ((i * 8.0) / numberYDivisions)) * 100) / 100.0 + "";

                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
//                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        Random rand = new Random();
        g2.setColor(Color.blue);
        for(int i = 0; i < 63536; i++) {
            g2.drawLine(padding + labelPadding + (i * 10), getHeight() / 2 - 1,
                    padding + labelPadding + (i * 10), (int) rand.nextInt() * 400 * -400);
        }

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
