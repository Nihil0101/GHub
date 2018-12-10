import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TheStack {

    private int[] stackArray;
    private int stackSize;
    private int topOfStack = -1;

    TheStack(int size) {
        stackSize = size;
        stackArray = new int[size];

        Arrays.fill(stackArray, -1);

        for(int i = 0; i<size;i++){
            push(ThreadLocalRandom.current().nextInt(0, 10));
        //stackArray[i] = ThreadLocalRandom.current().nextInt(0, 10);;
        }

    }
    TheStack(int size, int digit){
        stackSize = size;
        stackArray = new int[size];

        Arrays.fill(stackArray, digit);
    }

    public void push(int input) {
        if (topOfStack + 1 < stackSize) {
            topOfStack++;
            stackArray[topOfStack] = input;
        } else System.out.println("Stack is full");
        //displayTheStack();
        //System.out.println("PUSH " + input + " was added to the stack");
    }

    public int pop() {
        if (topOfStack >= 0) {
            //displayTheStack();
            //System.out.println("POP " + stackArray[topOfStack] + " was removed from the stack\n");
            stackArray[topOfStack] = -1;
            return stackArray[topOfStack--];
        } else {
            //displayTheStack();
            System.out.println("Your stack EMPTY!");
            return -1;
        }
    }

    public int peek() {
        //displayTheStack();
        //System.out.println("PEEK " + stackArray[topOfStack] + " is at the top of the Stack\n");
        return stackArray[topOfStack];
    }

    public void displayTheStack() {
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < stackSize; n++) {
            System.out.format("| %2s " + " ", n);
        }
        System.out.println("|");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < stackSize; n++) {
            if (stackArray[n] == -1) System.out.print("|     ");
            else System.out.print(String.format("| %2s " + " ", stackArray[n]));
        }
        System.out.println("|");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
    }

    public void displayTheStackFinal(int x) {

        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
        for (int n = 0; n < stackSize; n++) {
            System.out.print(String.format("| %2s " + " ", stackArray[n]));
        }
        if(x < 10)
        System.out.print("| " + x);
        else System.out.print("| X");
        System.out.println("|");
        for (int n = 0; n < 61; n++) System.out.print("-");
        System.out.println();
    }




    public static void main(String[] args){

        for(int y = 0; y < 11; y++) {
            TheStack theStack = new TheStack(9);
            TheStack reverseStack = new TheStack(9, -1);
            TheStack answerStack = new TheStack(9,-1);
            TheStack faStack = new TheStack(9,-1);

            //System.out.println("Randomly generated stack:");
            //theStack.displayTheStack();
            for(int i = 0; i<reverseStack.stackSize;i++){
                reverseStack.push(theStack.stackArray[theStack.topOfStack]);
                theStack.pop();
            }

            //System.out.println("Reversed Stack:");
            //reverseStack.displayTheStack();
            answerStack.push(reverseStack.stackArray[reverseStack.topOfStack]);
            reverseStack.pop();
            for(int i = 1; i<answerStack.stackSize;i++){
                answerStack.push(reverseStack.stackArray[reverseStack.topOfStack]+answerStack.stackArray[answerStack.topOfStack]);
                reverseStack.pop();
        }
            //System.out.println("Like tree stack: ");
            //answerStack.displayTheStack();

                for (int i = 0; i < 9; i++) {
                faStack.push(answerStack.stackArray[i]);
            }
            //System.out.println("For results: ");
            //faStack.displayTheStack();

            int sum = 0;
            for(int i = 0; i<answerStack.stackSize;i++){
                sum += answerStack.stackArray[answerStack.topOfStack];
                answerStack.pop();

            }
            //System.out.println("sum " + sum);


            int answer = sum % 11;
            System.out.println(sum + " " + answer + " final stack " + '\n');
            faStack.displayTheStackFinal(answer);
        }
    }
}



