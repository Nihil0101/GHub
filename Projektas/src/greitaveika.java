import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class greitaveika {

    private static final String[] TYRIMU_VARDAI_S_Q = {" QueueAdd  ", "StackAdd   ", "QueueDel  ", "StackDel    "};
    private static final int[] TIRIAMI_KIEKIAI_S_Q = {100000, 200000, 400000, 800000};
    private static final String TYRIMU_VARDAI_MEDZUI = "TreeAdd";
    private static final int[] TIRIAMI_KIEKIAI_MEDZUI = {2500, 5000, 10000, 20000};
    private static final String[] TYRIMU_VARDAI_ISBNos = {"    Sum       ", "AltSum   ", "RecSum  ", "Remaind  ", "AltRemaind      "};
    private static final int[] TIRIAMI_KIEKIAI_ISBNos = {10000000, 20000000, 40000000, 80000000};

    private static ISBNTree.Tree tree = new ISBNTree.Tree();
    private static TheQueue queue;
    private static TheStack stack;

    static void Stack_Queue_tyrimas() {
        for (int k : TIRIAMI_KIEKIAI_S_Q) {

            long t0 = System.nanoTime();
            queue = new TheQueue( k );
            long t1 = System.nanoTime();
            stack = new TheStack( k );
            long t2 = System.nanoTime();
            for (int i = 0; i < k; i++) {
                queue.remove();
            }
            long t3 = System.nanoTime();
            for (int i = 0; i < k; i++) {
                stack.pop();
            }
            long t4 = System.nanoTime();

            System.out.printf( k+ " ");
            System.out.printf( "%7.8f", (t1 - t0) / 1e9);
            System.out.printf( "%7.8f", (t2 - t1) / 1e9);
            System.out.printf( "%7.8f", (t3 - t2) / 1e9 );
            System.out.printf( "%7.8f", (t4 - t3) / 1e9);
            System.out.println( "\n" );
        }

    }


    static void medzio_tyrimas(){

        for (int k : TIRIAMI_KIEKIAI_MEDZUI) {
            System.out.printf(k + "    ");
        }

        System.out.println( "\n" );

        for (int k : TIRIAMI_KIEKIAI_MEDZUI) {

            tree.clearTree();

            long t0 =System.nanoTime();
            for (int i = 0; i < k; i++) {
                tree.add(ThreadLocalRandom.current().nextInt(0, 10 ), i);
            }
            long t1 =System.nanoTime();
            System.out.printf("%7.4f ",  (t1-t0)/1e9);
        }
    }

    static void ISBNos_tyrimas() {
        Random r = new Random();
        ISBNos isbnos = new ISBNos(  );
        for (int k : TIRIAMI_KIEKIAI_ISBNos) {
            int [] integerArray = new int[k];
            for (int i = 0; i < k; i++) {
                integerArray[i] = ThreadLocalRandom.current().nextInt(0, 10 );
            }
            int sum1, sum2, sum3 = 0, rem1, rem2;

            long t0 = System.nanoTime();
            sum1 = isbnos.Sum(integerArray);
            long t1 = System.nanoTime();
            sum2 = isbnos.AlternativeSum( integerArray );
            long t2 = System.nanoTime();
            sum3 = isbnos.RecursiveSum(integerArray, 0, sum3);
            long t3 = System.nanoTime();
            isbnos.Remainder( sum1 );
            long t4 = System.nanoTime();
            isbnos.AlternativeRemainder( sum1 );
            long t5 = System.nanoTime();


            System.out.printf( k + " ");
            System.out.printf( "%7.8f", (t1 - t0) / 1e9);
            System.out.printf( "%7.8f", (t2 - t1) / 1e9);
            System.out.printf( "%7.8f", (t3 - t2) / 1e9 );
            System.out.printf( "%7.8f", (t4 - t3) / 1e9);
            System.out.printf( "%7.8f", (t5 - t4) / 1e9);
            System.out.println( "\n" );
        }

    }

    static public void ouf(String format, Object... args){
        System.out.printf(format, args);
    }

    public static void main(String[] args){
        StringBuilder antraste = new StringBuilder();
        antraste.append( "Kiekis " );
        for(String s: TYRIMU_VARDAI_S_Q){
            antraste.append(s);
        }
        System.out.println(antraste);
        Stack_Queue_tyrimas();

        System.out.println( "\n" );

        System.out.println(TYRIMU_VARDAI_MEDZUI);
        medzio_tyrimas();

        System.out.println( "\n" );

        antraste = new StringBuilder(  );
        antraste.append( "Kiekis " );
        for(String s: TYRIMU_VARDAI_ISBNos){
            antraste.append(s+ " ");
        }
        System.out.println(antraste);
        ISBNos_tyrimas();

    }
}
