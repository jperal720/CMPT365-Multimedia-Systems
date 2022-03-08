package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.io.*;
import java.awt.*;
import java.util.*;

import com.company.WavFile.MyPanel;
import com.company.WavFile.WavFile;



public class WavConverterGUI extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JButton selectButton;
    private JFileChooser openFileChooser;
    private AudioInputStream file;
    private WavFile wavFile;
    private MyPanel panel;
    private JButton button;

//    @Override
//    protected void paintComponent(Graphics g){
//
//    }

    public WavConverterGUI(String appName){
        super(appName);

        button = new JButton();
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(this);
//        button.setBounds(0, 900, 100, 50);

//        panel = new MyPanel();
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("/Users/jonathanperalgort/Documents"));

        this.setPreferredSize(new Dimension(30000, 1000));
//        this.add(panel);
        this.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainPanel.setPreferredSize(new Dimension(500, 500));
//        this.setContentPane(mainPanel);
//        this.add(panel);
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        int returnValue = openFileChooser.showOpenDialog(mainPanel);

        if (returnValue == openFileChooser.APPROVE_OPTION){
            try{
//                        file = AudioSystem.getAudioInputStream(openFileChooser.getSelectedFile());
                button.setVisible(false);
                wavFile = WavFile.openWavFile(openFileChooser.getSelectedFile());
                wavFile.display();
                panel = new MyPanel(wavFile);
                this.add(panel);
            }catch(Exception ioe){
                System.out.println("Error: could not load file");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new WavConverterGUI("WAV Plot");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public WavFile getWavFile() {
        return wavFile;
    }
}