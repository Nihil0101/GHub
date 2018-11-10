package laborai.ManoLab;

import laborai.studijosktu.BstSetKTUx2;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.SortedSetADTx;
import laborai.studijosktu.BstSetKTUx;
import laborai.gui.MyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class SpeedTrial {

    public static final String FINISH_COMMAND = "finish";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("laborai.gui.messages");
    private static final String[] TYRIMU_VARDAI = {"TreeSetAdd", "HashSetAdd", "TreeSetCA", "HashSetCA"};
    private static final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final TimeKeeper tk;
    private final String[] errors;

    private final TreeSet<Integer> treeSetTrial = new TreeSet<Integer>();
    private final HashSet<Integer> hashSetTrial = new HashSet<Integer>();
    //private final TreeSet<Integer> treeSetConatains = new TreeSet<Integer>();
    //private final HashSet<Integer> hashSetContains = new HashSet<Integer>();


    public SpeedTrial() {
        semaphore.release();
        tk = new TimeKeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
        errors = new String[]{
                MESSAGES.getString("error1"),
                MESSAGES.getString("error2"),
                MESSAGES.getString("error3"),
                MESSAGES.getString("error4")
        };
    }

    public void startTrial() {
        try {
            SystemTrial();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SystemTrial() throws InterruptedException {
        try {
            for (int k : TIRIAMI_KIEKIAI) {
                treeSetTrial.clear();
                hashSetTrial.clear();
                //treeSetConatains.clear();
                //hashSetContains.clear();
                ArrayList<Integer> array = new ArrayList<>(k);
                for (int i = 0; i < k; i++){
                    array.add(i);
                }
                tk.startAfterPause();
                tk.start();
                for (int i = 0; i < k; i++) {
                    treeSetTrial.add(i);
                }
                tk.finish(TYRIMU_VARDAI[0]);
                for (int i = 0; i < k; i++) {
                    hashSetTrial.add(i);
                }
                tk.finish(TYRIMU_VARDAI[1]);
                treeSetTrial.containsAll(array);
                tk.finish(TYRIMU_VARDAI[2]);
                hashSetTrial.containsAll(array);
                tk.finish(TYRIMU_VARDAI[3]);
                tk.seriesFinish();
            }
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                tk.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                tk.logResult(MESSAGES.getString("msg3"));
            } else {
                tk.logResult(e.getMessage());
            }
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}

