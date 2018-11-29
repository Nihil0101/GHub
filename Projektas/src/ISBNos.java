import groovy.transform.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class ISBNos {
        private String unfISBN;
        private String isbn;

    ISBNos(){

    }
    ISBNos(String unfisbn, String last){
            unfISBN = unfisbn;
            isbn = unfisbn + last;
        }
    ISBNos(String first, String unfisbn, String last){

        unfISBN = unfisbn;
        isbn = first + unfisbn.substring(1) + last;
    }

        public String readMyDigits(){
            Scanner reader = new Scanner(System.in);
            StringBuilder builder = new StringBuilder();
            System.out.println("Enter 9 single digit integer numbers: ");
            for(int i = 0; i < 9;i++) {
                builder.append(reader.nextInt());
        }
            reader.close();
            return builder.toString();
        }
        public String IntArray(){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 9;i++){
                builder.append(ThreadLocalRandom.current().nextInt(0, 10));
            }
            return builder.toString();
        }
        public int convertToInt(String convert){
        StringBuilder builder = new StringBuilder();
        if(convert.substring(0,1).equals("0"))
        {
            //Changed(true);
            builder.append(ThreadLocalRandom.current().nextInt(1, 10));
            System.out.println("First digit was a 0, so it was changed to " + builder);
            builder.append(convert.substring(1));

            return Integer.parseInt(builder.toString());
        }
            //Changed(false);
            return Integer.parseInt(convert);
        }
        public int[] BrokenApartLtoR(int myLongInteger) {
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
        public int[] BrokenApartRtoL(int myLongInteger) {

            int ISBNlenght = 9;
            int[] n = new int[ISBNlenght];
            //System.out.println(myLongInteger);
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
        public double FormDouble(String remainder){
            int nn = ThreadLocalRandom.current().nextInt(0, 8000);
            String formingDouble = nn + "." + remainder;
            double myDouble = Double.parseDouble(formingDouble);
            //System.out.println("Mein double :) " + myDouble);
            return myDouble;
        }
        public int[] BrokenApartD(double myDouble) {
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
        public String FormString() {
            int[] n = new int[9];
            n[0] = ThreadLocalRandom.current().nextInt(0, 10);

            for (int i = 1; i < 9; i++) {
                n[i] = ThreadLocalRandom.current().nextInt(-9, 10);
            }
            //System.out.println("Generated numbers: " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
            String myString = String.valueOf(n[0]) + String.valueOf(n[1]) + String.valueOf(n[2]) + String.valueOf(n[3]) + String.valueOf(n[4]) + String.valueOf(n[5]) + String.valueOf(n[6]) + String.valueOf(n[7]) + String.valueOf(n[8]);
            //System.out.println("My string: " + myString);
            //System.out.println("In single integer form - " +step2);
            return myString;
    }
        public int[] FormIntArray(String apdoroti) {
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
        public int Sum(int [] n){
            int sum = abs(n[0]) + abs(n[1]) * 2 + abs(n[2]) * 3 + abs(n[3]) * 4 + abs(n[4]) * 5 + abs(n[5]) * 6 + abs(n[6]) * 7 + abs(n[7]) * 8 + abs(n[8]) * 9;
            return sum;
        }
        public int RecursiveSum(int [] n, int i, int sum){
            if (i == 9){
                return sum;
            }
            sum = n[i] * i+1;
            return sum + RecursiveSum(n,i+1, sum);
        }
        public int Remainder(int sum){
        return sum % 11;
        }
        public boolean CheckSum(int remainder){
        if(remainder < 10){
            return true;
        }
        return false;
        }
        public String last(int remainder, boolean last){
        if(last == true)
            return "--" + String.valueOf(remainder);
        return "--X";
        }
        public int AlternativeSum(int [] n){
            int sum = abs(n[0]) * 10 + abs(n[1]) * 9 + abs(n[2]) * 8 + abs(n[3]) * 7 + abs(n[4]) * 6 + abs(n[5]) * 5 + abs(n[6]) * 4 + abs(n[7]) * 3 + abs(n[8]) * 2;
            return sum;
        }
        public int AlternativeRemainder(int sum){
            return (11 - (sum % 11))%11;
        }
        public String showISBN(){
        return isbn;
        }
        public boolean isISBN(String myISBN){

        if(myISBN.length() == 12 && myISBN.substring(9,11).equals("--")&&(myISBN.endsWith("X") || myISBN.substring(11).matches("\\d+")) && myISBN.substring(0,9).matches("\\d+"))
            return true;
        int minusPointer = myISBN.length() - 3;
        int end = myISBN.length() - 1;
        String isGood = myISBN.substring(0,minusPointer).replaceAll("(-?[0-9]+)","");
        //System.out.println("My lenght: " + myISBN.length() + "\n" + "My strings END " + myISBN.substring(minusPointer, end) + "\n" + "My ends:" + myISBN.substring(end) + "\n" + "My else: " + myISBN.substring(0, minusPointer) + "\n");
        //if(myISBN.length() >11 && myISBN.length() < 20 && myISBN.substring(minusPointer,end).equals("--") && (myISBN.endsWith("X") || myISBN.substring(end).matches("\\d+")) && myISBN.substring(0,minusPointer).matches("(-?[0-9]+)"))
            if(myISBN.length() >11 && myISBN.length() < 20)
                if(myISBN.substring(minusPointer,end).equals("--"))
                    if((myISBN.endsWith("X") || myISBN.substring(end).matches("\\d+")) )
                    {
                        //System.out.println(myISBN.substring(0,minusPointer).replaceAll("(-?[0-9]+)","0"));
                        if(isGood.isEmpty())
                            return true;}
        return false;
        }
        public boolean isInputISBN(String myISBN){
            String isGood = myISBN.substring(0).replaceAll("(-?[0-9]+)","");
            if(!isGood.isEmpty())
                return false;
            return true;
        }
        public String First(Boolean changed, int isbnc){
        if(changed ==true)
            return String.valueOf(isbnc / (int)Math.pow(10,8));
        return "Nothing was changed";
        }
        public Boolean Changed(String original, int modified){
        if(!original.contentEquals(String.valueOf(modified)))
            return true;
        return false;
        }

        @Override
        public String toString(){
        return "ISBN digits: " + unfISBN + "\n" + "ISBN: " + isbn + "\n";

    }



}
