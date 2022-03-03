package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WavConverterGUI extends JFrame{
    private JPanel mainPanel;
    private JButton selectButton;
    private JFileChooser openFileChooser;
    private AudioInputStream wavFile;


    public WavConverterGUI(String appName){
        super(appName);

        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("/Users/jonathanperalgort/Documents"));

        this.setPreferredSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = openFileChooser.showOpenDialog(mainPanel);

                if (returnValue == openFileChooser.APPROVE_OPTION){
                    try{
                        wavFile = AudioSystem.getAudioInputStream(openFileChooser.getSelectedFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(wavFile);
                        clip.start();
                    }catch(Exception ioe){
                        System.out.println("Error: could not load file");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new WavConverterGUI("WAV Plot");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
