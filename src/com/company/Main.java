package com.company;

import java.lang.Integer;

public class Main {

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        double N = 1000000;
//        int[] YUV = {77, 103, 10};
//        int[] YUV = {167, 100, -80};
        int[] YUV = {40, 70, 30};
        int[] RGB = YUVtoRGB(YUV);
        int[] YCoCg = new int[3];
        for(int i = 0; i < N; i++){
            YCoCg = RGBtoYCoCg(RGB);
        }

        System.out.print("YCoCg Array: ");
        for (int i = 0; i < YUV.length; i++) {
            System.out.print(YCoCg[i]+ " ");
        }
        System.out.print("\n");

        double end = System.currentTimeMillis();

        double seconds = (end - start) / 1000;
        double average = seconds / N;
        System.out.println("Average time (in seconds) that application takes: " + average);
    }

    public static int[] YUVtoRGB(int[] YUV){
        int[] RGB = new int[3];

        //Converting to R
        RGB[0] = (int) (1.03 * (float) YUV[0] + 0.03 * (float) YUV[1] + (float) YUV[2]);
        //Converting to G
        RGB[1] = (int) (0.98471 * (float) YUV[0] - 0.20948 * (float) YUV[1]	- 0.50936 * (float) YUV[2]);
        //Converting to B
        RGB[2] = (int) ((float) YUV[0] + (float) YUV[1]);

        for(int i = 0; i < RGB.length; i++){
            if(RGB[i] > 255)
                RGB[i] = 255;
            else if(RGB[i] < 0)
                RGB[i] = 0;
        }

        return RGB;
    }

    public static int[] RGBtoYCoCg(int[] RGB){
        int[] YCoCg = new int[3];

        //Converting to Y
        YCoCg[0] = (int) (0.25 * (float) RGB[0] + 0.5 * (float) RGB[1] + 0.25 * (float) RGB[2]);
        //Converting to Co
        YCoCg[1] = (int) (0.5 * (float) RGB[0] - 0.5 * (float) RGB[2]);
        //Converting to Cg
        YCoCg[2] = (int) (-0.25 * (float) RGB[0] + 0.5 * (float) RGB[1] - 0.25 * (float) RGB[2]);

        if(YCoCg[0] > 255){
            YCoCg[0] = 255;
        }
        else if (YCoCg[0] < 0){
            YCoCg[0] = 0;
        }

        for(int i = 1; i < RGB.length; i++){
            if(YCoCg[i] > 127)
                YCoCg[i] = 127;
            else if(YCoCg[i] < -128){
                YCoCg[i] = -128;
            }
        }
            return YCoCg;
    }
}
