import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TheQueue {

    private int[] queueArray;
    private int queueSize;
    private int front, rear, numberOfItems = 0;

    TheQueue(int size){
        queueSize = size;
        queueArray = new int[size];
        //Arrays.fill(queueArray, -1);
        for(int i = 0; i<size; i++){
            insert(ThreadLocalRandom.current().nextInt(0, 10));
        }

    }
    TheQueue(int size, int amount){
        queueSize = size;
        queueArray = new int[size];
        Arrays.fill(queueArray, amount);

    }
    public void  insert(int input){
        if(numberOfItems + 1 <= queueSize){
            queueArray[rear] = input;
            rear++;
            numberOfItems++;
            //System.out.println("INSERT " + input + " was added to queue\n" );
        }else {
            System.out.println("Queue is full");
        }
    }
    public void remove(){
        if(numberOfItems > 0){
            //System.out.println("REMOVE " + queueArray[front] + " was removed");
            queueArray[front] = -1;
            front++;
            numberOfItems--;
        } else {
            System.out.println("Queue is Empty");
        }
    }
    public void peek(){
        System.out.println("The first Element is " + queueArray[front]);
    }

    public void displayTheQueue() {
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < queueSize; n++) {
            System.out.format("| %2s " + " ", n);
        }
        System.out.println("|");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < queueSize; n++) {
            if (queueArray[n] == -1) System.out.print("|     ");
            else System.out.print(String.format("| %2s " + " ", queueArray[n]));
        }
        System.out.println("|");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        // Number of spaces to put before the F
        int spacesBeforeFront = 3 * (2 * (front + 1) - 1);
        for (int k = 1; k < spacesBeforeFront; k++) System.out.print(" ");
        System.out.print("F");
        // Number of spaces to put before the R
        int spacesBeforeRear = (2 * (3 * rear) - 1) - (spacesBeforeFront);
        for (int l = 0; l < spacesBeforeRear; l++) System.out.print(" ");
        System.out.print("R");
        System.out.println("\n");
    }

    public void displayTheQueueFinal(int ats) {
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < queueSize; n++) {
            if (queueArray[n] == -1) System.out.print("|     ");
            else System.out.print(String.format("| %2s " + " ", queueArray[n]));
        }
        if(ats < 10)
        System.out.println("| " + ats + " | ");
        else System.out.println("| X |");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
    }




    //paskutinis bus paskutinis o pirmas pirmas
    public static void main(String[] args){
        TheQueue theQueue = new TheQueue(9);
        TheQueue faQueue = new TheQueue(9,-1);
        TheQueue answerQueue = new TheQueue(9,-1);


        System.out.println("Original: \n");
        theQueue.displayTheQueue();
        answerQueue.insert(theQueue.queueArray[theQueue.front]);
        faQueue.insert(theQueue.queueArray[theQueue.front]);
        theQueue.remove();
        int sum = answerQueue.queueArray[answerQueue.front];
        for(int i = 1; i < answerQueue.queueSize; i++){
            answerQueue.insert(theQueue.queueArray[theQueue.front] + answerQueue.queueArray[answerQueue.rear-1]);
            faQueue.insert(theQueue.queueArray[theQueue.front]);
            sum += answerQueue.queueArray[answerQueue.rear-1];
            theQueue.remove();
        }
        System.out.println("Empty: \n");
        theQueue.displayTheQueue();
        System.out.println("Duplicate: \n");
        faQueue.displayTheQueue();
        int ats = sum % 11;
        System.out.println("Sums: " + sum + " div " + ats + "\n");
        answerQueue.displayTheQueueFinal(ats);

    }
}


