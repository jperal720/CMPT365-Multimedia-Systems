package com.company.WavFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class MyPanel extends JPanel {
    private WavFile wavFile;

    public MyPanel(WavFile wavFile){
        this.wavFile = wavFile;
        this.setPreferredSize(new Dimension(500, 500));
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int lineStroke = 1;
        double[] buffer = new double[65536];
        int i = 1;

        wavFile.display();
        if(wavFile.getNumFrames() > 65536)
            for(int x = 0; x < 1; x--){
                i++;
                if(wavFile.getNumFrames() / i <= 65536){
                    break;
                }
            }

        int framesRead = 0;


        System.out.println("buffer size= " + wavFile.getNumFrames() / i);
        try {
            framesRead = wavFile.readFrames(buffer, 30000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WavFileException e) {
            e.printStackTrace();
        }

        System.out.println(i + " frames read: " + framesRead);

        g2.setStroke(new BasicStroke(lineStroke));
        int freq = 0;
        for(int z = 0; z < buffer.length; z++){
            freq+=3;
            if (buffer[z]== 0.0)
                break;
            if ((int) (buffer[z] * 100000) <= 0) {
                g2.fillRect(freq, getHeight() / 2, 2, Math.abs((int) (buffer[z] * 10000)));
            }
            else{
                g2.fillRect(freq, (getHeight() / 2) - (int) (buffer[z] * 10000), 2, (int) (buffer[z] * 10000));
            }
        }
    }
}
