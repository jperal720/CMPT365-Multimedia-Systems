import java.io.*;
import java.util.LinkedList;
import java.lang.Math;

public class main {

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(args[0]);
        LinkedList<Float> input = new LinkedList<Float>();

        File file = new File("input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        while((st = br.readLine()) != null){
            System.out.println(st);
        }




        //Test Case for transform functions
//        LinkedList<LinkedList<Float>> arr = transform(N);
//        for(LinkedList<Float> arr1 : arr)
//            for(Float fl : arr1)
//                System.out.println(fl);
    }

    public static LinkedList<LinkedList<Float>> matrixMult(LinkedList<LinkedList<Float>> a, LinkedList<LinkedList<Float>> b, int N) {
        LinkedList<LinkedList<Float>> output = new LinkedList<LinkedList<Float>>();

        for (int i = 0; i < N; i++) {
            output.push(new LinkedList<Float>());
            for (int x = 0; x < N; x++) {
                float total = 0;
                for (int j = 0; j < N; j++)
                    total+= (float)a.get(i).get(j) * (float)b.get(j).get(x);
                if(total > -0.5 && total < 0) total *= -1;
                output.get(i).push(total);
            }
        }

        return output;
    }

    public static LinkedList<LinkedList<Float>> convertInputToList(LinkedList<Float> input, int N) {
        LinkedList<LinkedList<Float>> output = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            output.push(new LinkedList<Float>());
            for (int x = 0; x < N; x++) {
                int indx = N * i + x;
                output.get(i).push(input.get(indx));
            }
        }

        return output;
    }

    public static LinkedList<LinkedList<Float>> transpose(LinkedList<LinkedList<Float>> transformedList, int N){
        LinkedList<LinkedList<Float>> transposeInput = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            transposeInput.push(new LinkedList<Float>());

            for (int x = 0; x < N; x++) {
                transposeInput.get(i).push((transformedList.get(x)).get(i));    //Pusing [x][i] unto transposeInput List
            }

        }

        return transposeInput;
    }


    public static LinkedList<LinkedList<Float>> transform(int N){
        LinkedList<LinkedList<Float>> transformInput = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            float a = i == 0 ? (float) (1 / Math.sqrt(N)) : (float) (2 / Math.sqrt(N));
            transformInput.push(new LinkedList<Float>());

            for(int x = 0; x < N; x++){
                float calcValue = (float)(x*2 + 1); calcValue = (float) (Math.PI * i * calcValue);
                calcValue = (float)(calcValue/(2*N)); calcValue = (float) Math.cos(calcValue);
                calcValue = (float) (x * calcValue);
                transformInput.get(i).push(calcValue);
            }
        }

        return transformInput;
    }
}
