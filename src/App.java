import java.util.Random;
import java.util.Scanner;

public class App {
    public static void runGameOfLife() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Please , enter array dimensions such as width and height");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        String[][] twoDimArray = new String[width][height];
        arrayPrintInit(twoDimArray);      //  array initialization by symbols 'o'


        System.out.println("Enter number of points you want to dispose");
        int pointsNumber = scanner.nextInt();

        for(int i = 0; i< pointsNumber;i++){
            int rand1 = random.nextInt(width);
            int rand2 = random.nextInt(width);
            pointDisposing(twoDimArray,rand1,rand2);
        }
        String[][] generation = neighborsSeeking(twoDimArray);
        System.out.println("The array is compounded !");
        System.out.println("Tap - 's'  to see ");
        System.out.println("Tap - 'n'  to create and watch a new generation ");
        System.out.println("Tap - 'q'  to exit the program  ");

        boolean flag = true;
        while (flag){
            String command = scanner.nextLine();
            switch (command){
                case("s"):
                    arrayPrint(twoDimArray);
                    break;
                case("n"):
                    String[][]tempArray = generation;
                    generation = neighborsSeeking(generation);
                    arrayPrint(tempArray);
                    break;
                case("q"):
                    System.out.println("Bye");
                    flag = false;
                    break;

            }
        }


    }

    static void arrayPrintInit(String[][] array) {    // serves for the first initialization
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j]="o";
                ;
            }
        }
    }
    static void arrayPrint(String[][] array) {    // accomplishes printing on a console
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void pointDisposing(String[][] array, int k, int l) { // fulfills allocating of elements in the array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[k][l] = "X";
            }
        }
    }
    static String[][] cloneSourceArray(String[][] array){  // plays a role of a clone of the main array

        String[][] result = new String[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                result[i][j]= array[i][j];
            }
        }return result;
    }
    static String[][]  neighborsSeeking(String[][] array) {  // performs a searching of neighbors
        String[][] future = cloneSourceArray(array);
        int M = array.length;// length of columns
        int N = array[0].length;// length of rows

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int cellCount = 0; // alive

                if(i>=1 && j>=1){
                    if(array[i-1][j-1]=="X") { cellCount +=1;}
                }

                if(i>=1 ){
                    if(array[i-1][j]=="X") { cellCount +=1;} // 0 0
                    if(j<N-1){if(array[i-1][j+1]=="X") { cellCount +=1;}}
                }
                if(j >=1){
                    if(array[i][j-1]=="X") { cellCount +=1;}
                    if(i< M-1) {if(array[i+1][j-1]=="X") { cellCount +=1;}}
                }

                if(j<N-1){
                    if(i<M-1){ if(array[i+1][j+1]=="X") { cellCount +=1;}}
                    if(array[i][j+1]=="X") { cellCount +=1;}
                }
                if(i<M-1){
                    if(array[i+1][j]=="X") { cellCount +=1;}
                }
                if(cellCount ==3){
                    if(array[i][j]=="o"){
                        future[i][j]="X";

                    }
                }
                else if(cellCount==1 || cellCount>3){
                    if(array[i][j]=="X"){
                        future[i][j] = "o";
                    }
                }
            }
        }return future;
    }
}

