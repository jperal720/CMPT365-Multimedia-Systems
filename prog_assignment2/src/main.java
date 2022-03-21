import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;
import java.math.*;
import java.util.ArrayList;

public class main {

    public static void main(String args[]){
        ArrayList<String> dictionary = new ArrayList<String>();
        dictionary.add("A"); dictionary.add("B"); dictionary.add("C"); dictionary.add("AB"); dictionary.add("BA");
        String input = args[0];

        char selector;
        int[] count = new int[3];
        for (int i = 0; i < input.length(); i++) { //Counting the number of repetitions per character
            selector = input.charAt(i);
            switch (selector) {
                case 'A':
                    count[0]++;
                    break;
                case 'B':
                    count[1]++;
                    break;
                case 'C':
                    count[2]++;
                    break;
            }
        }

        //Entropy calculation
        float entropy = (float) (Math.log((float) count[0] / input.length()) / Math.log(2) * (float) count[0] / input.length() * (float) count[0])
                + (float) (Math.log((float) count[1] / input.length()) / Math.log(2) * (float) count[1] / input.length() * (float) count[1])
                + (float) (Math.log((float) count[2] / input.length()) / Math.log(2) * (float) count[2] / input.length() * (float) count[2]);
        entropy *= -1;

        System.out.println("Entropy: " + entropy);

        dictionary = LZWAlgorithm(input, dictionary);
    }

    public static ArrayList<String> LZWAlgorithm(String input, ArrayList<String> dictionary){
        String s;
        String w;
        String sw;
        ArrayList<Integer> output = new ArrayList<Integer>();

        int i = 0;
        if (String.valueOf((input.charAt(i))) != null) {
            s = String.valueOf(input.charAt(i));

            while (i < input.length()) {

                if (i == input.length() - 1) {
                    sw = s;
                    for (int x = 0; x < dictionary.size(); x++) {
                        if (sw.equalsIgnoreCase(dictionary.get(x))) {
                            output.add(x + 1);
                            break;
                        }
                    }

                    /*
                    Printing Output
                     */

                    System.out.println("Output: ");
                    for (int x = 0; x < output.size(); x++) {
                        System.out.println(output.get(x));
                    }

                    /*
                    Printing Dictionary
                     */
                    System.out.println("Dictionary: ");
                    for (int x = 0; x < dictionary.size(); x++) {
                        System.out.println(dictionary.get(x));
                    }

                    return dictionary;
                } else {
                    w = String.valueOf(input.charAt(i + 1));
                    sw = s + w;
                }

                boolean exists = false;
                for (int x = 0; x < dictionary.size(); x++) {
                    if (sw.equalsIgnoreCase(dictionary.get(x))) {
                        exists = true;
                        s = sw;
                        i++;
                        break;
                    }
                }

                if (!exists) {
                    for(int x = 0; x < dictionary.size(); x++){
                        if (s.equalsIgnoreCase(dictionary.get(x))) {
                            output.add(x+1);
                            break;
                        }
                    }

                    dictionary.add(sw);
                    s = w;
                    i++;
                }

            }
        }

        return dictionary;

    }
}