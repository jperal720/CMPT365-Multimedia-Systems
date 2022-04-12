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
            String[] strArr = st.split(" ");    //Adding the space-delimited values into the LinkedList<Float>
            for(String a : strArr)
                input.addLast(Float.valueOf(a).floatValue());
        }

        /**
         * Displaying Input Values
         */
        System.out.println("Input values:");
        LinkedList<LinkedList<Float>> matrix = convertInputToList(input, N);
        displayMatrix(matrix, N);

        /**
         * Creating transformed and transposed matrices
         */
        LinkedList<LinkedList<Float>> transformedMatrix = transform(N); //Transform Matrix
        LinkedList<LinkedList<Float>> transposedMatrix = transpose(transformedMatrix, N);  //Transposing Matrix

        /**
         * Row Transform first!
         */
        LinkedList<LinkedList<Float>> finalMatrix = matrixMult(matrix, transposedMatrix, N);
        finalMatrix = matrixMult(transformedMatrix, finalMatrix, N);
        System.out.println("Row Transform First:");

        /**
         * Displaying finalMatrix
         */
        displayMatrix(finalMatrix, N);

        /**
         * Column Transform first!
         */
        finalMatrix = matrixMult(transformedMatrix, matrix, N);
        finalMatrix = matrixMult(transposedMatrix, finalMatrix, N);
        System.out.println("Column Transform First: ");

        /**
         * Displaying finalMatrix
         */

        displayMatrix(finalMatrix, N);

    }

    public static void displayMatrix(LinkedList<LinkedList<Float>> matrix, int N){
        for(int i = 0; i < matrix.size(); i++){
            for(int x = 0; x < matrix.size(); x++) {
                System.out.print(Math.round((matrix.get(i)).get(x)) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static LinkedList<LinkedList<Float>> matrixMult(LinkedList<LinkedList<Float>> a, LinkedList<LinkedList<Float>> b, int N) {
        LinkedList<LinkedList<Float>> output = new LinkedList<LinkedList<Float>>();

        for (int i = 0; i < N; i++) {
            output.addLast(new LinkedList<Float>());
            for (int x = 0; x < N; x++) {
                float total = 0;
                for (int j = 0; j < N; j++)
                    total+= (float)a.get(i).get(j) * (float)b.get(j).get(x);
                if(total > -0.5 && total < 0) total *= -1;
                output.get(i).addLast(total);
            }
        }

        return output;
    }

    public static LinkedList<LinkedList<Float>> convertInputToList(LinkedList<Float> input, int N) {
        LinkedList<LinkedList<Float>> output = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            output.addLast(new LinkedList<Float>());
            for (int x = 0; x < N; x++) {
                output.get(i).addLast(input.get(x * N + i));
            }
        }

        return output;
    }

    public static LinkedList<LinkedList<Float>> transpose(LinkedList<LinkedList<Float>> transformedList, int N){
        LinkedList<LinkedList<Float>> transposeInput = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            transposeInput.addLast(new LinkedList<Float>());

            for (int x = 0; x < N; x++) {
                transposeInput.get(i).addLast((transformedList.get(x)).get(i));    //Adding [x][i] unto transposeInput List
            }

        }

        return transposeInput;
    }


    public static LinkedList<LinkedList<Float>> transform(int N){
        LinkedList<LinkedList<Float>> transformInput = new LinkedList<LinkedList<Float>>();

        for(int i = 0; i < N; i++){
            float a = i == 0 ? (float) (1 / Math.sqrt(N)) : (float) (2 / Math.sqrt(N));
            transformInput.addLast(new LinkedList<Float>());

            for(int x = 0; x < N; x++){
                float calcValue = (float)(x*2 + 1); calcValue = (float) (Math.PI * i * calcValue);
                calcValue = (float)(calcValue/(2*N)); calcValue = (float) Math.cos(calcValue);
                calcValue = (float) (x * calcValue);
                transformInput.get(i).addLast(calcValue);
            }
        }

        return transformInput;
    }
}
