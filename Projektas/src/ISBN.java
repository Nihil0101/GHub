import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class ISBN {
    public static void Reader() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 9 single digit integer numbers: ");
        int n1 = reader.nextInt();
        int n2 = reader.nextInt();
        int n3 = reader.nextInt();
        int n4 = reader.nextInt();
        int n5 = reader.nextInt();
        int n6 = reader.nextInt();
        int n7 = reader.nextInt();
        int n8 = reader.nextInt();
        int n9 = reader.nextInt();
        reader.close();
        System.out.println("You entered following numbers: " + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9);
    }

    public static void Generated() {
        int[] n = new int[9];
        for (int i = 0; i < 9; i++) {
            n[i] = ThreadLocalRandom.current().nextInt(0, 10);
            //System.out.println("Generated number: " + n[i]);
        }
        int sum = n[0] + n[1] * 2 + n[2] * 3 + n[3] * 4 + n[4] * 5 + n[5] * 6 + n[6] * 7 + n[7] * 8 + n[8] * 9;
        int check = sum % 11;
        System.out.println("Number after checking: " + check + " & " + "Sum of numbers: " + sum);
        System.out.println("Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        if (check < 10)
            System.out.println("Generated ISBN: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + check);
        else
            System.out.println("Generated ISBN: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + 'X');

        int sum2 = n[0] * 10 + n[1] * 9 + n[2] * 8 + n[3] * 7 + n[4] * 6 + n[5] * 5 + n[6] * 4 + n[7] * 3 + n[8] * 2;
        int check2 = (11 - (sum2 % 11))%11;
        System.out.println("Advanced Number after checking: " + check2 + " & " + "Sum of numbers: " + sum2);
        System.out.println("Advanced Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        if (check2 < 10)
            System.out.println("Advanced Generated ISBN: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + check2);
        else
            System.out.println(" AdvancedGenerated ISBN: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + 'X');
    }

    public static void Call1() {
        for (int i = 0; i < 101; i++) {
            System.out.println("-------------------------------------");
            Generated();
            System.out.println("Trial number: " + i);
            System.out.println("-------------------------------------");
        }
    }
    //might need to return to this one
    public static int FormInt() {
        int[] n = new int[9];
        n[0] = ThreadLocalRandom.current().nextInt(1, 10);
        //n[0] = 0;
        for (int i = 1; i < 9; i++) {
            n[i] = ThreadLocalRandom.current().nextInt(0, 10);
        }
        //System.out.println("Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        String step1 = String.valueOf(n[0]) + String.valueOf(n[1]) + String.valueOf(n[2]) + String.valueOf(n[3]) + String.valueOf(n[4]) + String.valueOf(n[5]) + String.valueOf(n[6]) + String.valueOf(n[7]) + String.valueOf(n[8]);
        //System.out.println("In string form - " + step1);
        int step2 = Integer.parseInt(step1);
        //System.out.println("In single integer form - " +step2);
        return step2;


    }

    public static int[] BrokenApartLtoR() {
        int myLongInteger = FormInt();
        int ISBNlenght = 9;
        int[] n = new int[ISBNlenght];
        //System.out.println(myLongInteger);
        for (int i = 0; i < ISBNlenght; i++) {
            n[i] = myLongInteger / (int) Math.pow(10.00, (double) (ISBNlenght - i - 1));
            myLongInteger = myLongInteger % (int) Math.pow(10.00, (double) (ISBNlenght - i - 1));
        }
        //System.out.println("Generated numbers LR: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        return n;
    }

    public static int[] BrokenApartRtoL() {
        int myLongInteger = FormInt();
        int ISBNlenght = 9;
        int[] n = new int[ISBNlenght];
        System.out.println(myLongInteger);
        for (int i = ISBNlenght - 1; i > -1; i--) {
            //System.out.println("Array:  " + n[i]);
            n[i] = myLongInteger % 10;
            if (myLongInteger != 0) {
                myLongInteger = (int) ((myLongInteger - n[i]) * 0.1);
            }
        }
        //System.out.println("Generated numbers RL: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        return n;
    }

    public static int SumCheck(int[] n) {
        int sum = abs(n[0]) + abs(n[1]) * 2 + abs(n[2]) * 3 + abs(n[3]) * 4 + abs(n[4]) * 5 + abs(n[5]) * 6 + abs(n[6]) * 7 + abs(n[7]) * 8 + abs(n[8]) * 9;
        int check = sum % 11;
        return check;
    }

    public static void Call2() {
        for (int i = 0; i < 51; i++) {
            System.out.println("-------------------------------------" + i);
            int[] m = BrokenApartLtoR();
            int me = SumCheck(m);
            if (me < 10) {
                System.out.println("Sumcheck : " + me + " LR Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + me);
            } else
                System.out.println("Sumcheck : " + me + " LR Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + 'X');

            int[] n = BrokenApartRtoL();
            int ne = SumCheck(n);
            if (ne < 10) {
                System.out.println("Sumcheck: " + ne + " RL Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + ne);
            } else
                System.out.println("Sumcheck: " + ne + " RL Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8] + 'X');
            System.out.println("-------------------------------------" + i);
        }
    }

    public static double FormDouble() {
        int remainder = FormInt();
        int nn = ThreadLocalRandom.current().nextInt(0, 8000);
        String formingdouble = nn + "." + remainder;
        double myDouble = Double.parseDouble(formingdouble);
        //System.out.println("Mein double :) " + myDouble);
        return myDouble;
    }

    public static int[] BrokenApartD() {
        double myDouble = FormDouble();
        DecimalFormat numberFormat = new DecimalFormat("0.000000000");
        //System.out.println("generated number: " + myDouble);
        String workingNumberS = numberFormat.format(myDouble % (int) myDouble);
        double workingNumber = Double.parseDouble(workingNumberS);
        //System.out.println("remainder of the number: " + workingNumber);
        int ISBNlenght = 9;
        int[] n = new int[ISBNlenght];
        for (int i = 0; i < ISBNlenght; i++) {

            BigDecimal bd = new BigDecimal(((workingNumber * 10) - ((int) workingNumber * 10)) - Math.floor(((workingNumber * 10) - ((int) workingNumber * 10)))).setScale(9, RoundingMode.HALF_DOWN);
            String vaziuojam = bd.toString();
            n[i] = (int) (workingNumber * 10);
            //System.out.println("ni: "+n[i]);
            workingNumber = Double.parseDouble(vaziuojam);
            //(workingNumber * 10) - ((int) workingNumber * 10);
            //System.out.println("wn: "+workingNumber);
        }
        //System.out.println("Integered : " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        return n;
    }

    public static void Call3() {
        for (int i = 0; i < 51; i++) {
            System.out.println("-------------------------------------" + i);
            int[] m = BrokenApartD();
            int me = SumCheck(m);
            if (me < 10) {
                System.out.println("Sumcheck : " + me + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + me);
            } else
                System.out.println("Sumcheck : " + me + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + 'X');
        }
    }

    public static String FormString() {
        int[] n = new int[9];
        n[0] = ThreadLocalRandom.current().nextInt(0, 10);
        ;
        for (int i = 1; i < 9; i++) {
            n[i] = ThreadLocalRandom.current().nextInt(-9, 10);
        }
        //System.out.println("Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
        String myString = String.valueOf(n[0]) + String.valueOf(n[1]) + String.valueOf(n[2]) + String.valueOf(n[3]) + String.valueOf(n[4]) + String.valueOf(n[5]) + String.valueOf(n[6]) + String.valueOf(n[7]) + String.valueOf(n[8]);
        //System.out.println("My string: " + myString);
        //System.out.println("In single integer form - " +step2);
        return myString;
    }

    public static int[] FormIntArray() {
        String apdoroti = FormString();
        //System.out.println("My String: " + apdoroti);
        int[] intArray = new int[9];


        int prad = apdoroti.length() - 1;
        int pab = apdoroti.length();
        //System.out.println("-1: " + prad + " null " + pab);

        intArray[0] = Integer.parseInt(apdoroti.substring(0, 1));
        if (apdoroti.substring(prad, pab).compareTo("-") == 0) {
            intArray[8] = Integer.parseInt(apdoroti.substring(prad));
        } else intArray[8] = Integer.parseInt(apdoroti.substring(prad));
        int x = 1;
//
//
        for (int i = 1; i < pab; i++) {
            if (apdoroti.substring(i, i + 1).equals("-") && apdoroti.substring(i + 2) != null) {
                intArray[x] = Integer.parseInt(apdoroti.substring(i, i + 2));
                i++;
                x++;
            } else {
                intArray[x] = Integer.parseInt(apdoroti.substring(i, i + 1));
                x++;
            }
        }
        //System.out.println("String array: " + intArray[0] + intArray[1] + intArray[2] + intArray[3] + intArray[4] + intArray[5] + intArray[6] + intArray[7] + intArray[8]);
        return intArray;
    }

    public static void Call4() {
        for (int i = 0; i < 51; i++) {
            System.out.println("-------------------------------------" + i);
            int[] m = FormIntArray();
            int me = SumCheck(m);
            if (me < 10) {
                System.out.println("Sumcheck : " + me + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + "--" + me);
            } else
                System.out.println("Sumcheck : " + me + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + "--X");
        }
    }
    public static void Call5(){
        for (int i = 0; i < 51; i++) {
            System.out.println("-------------------------------------" + i);
            int[] m = BrokenApartD();
            int me = RecursiveCheck(m,0, 0);
            int answer = me % 11;
            if (answer < 10) {
                System.out.println("Sumcheck : " + answer + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + answer);
            } else
                System.out.println("Sumcheck : " + answer + " Generated numbers: " + m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8] + 'X');
        }
    }

    public static int RecursiveCheck(int [] n, int i, int sum){
        if (i == 9){
            return sum;
        }
        sum = n[i] * i+1;
        return sum + RecursiveCheck(n,i+1, sum);
    }

    public static void main(String[] args) {
        //Reader();
        Call1();
        //Call2();
        //Call3();
        //Call4();
        //Call5();



        //System.out.println("Main's Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
    }
}
