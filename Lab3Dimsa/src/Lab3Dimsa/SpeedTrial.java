package Lab3Dimsa;

import studijosktu.HashType;
import studijosktu.MapKTU;
import studijosktu.MapKTUOA;
import gui.MyException;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
public class SpeedTrial {

    public static final String FINISH_COMMAND = "finishCommand";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("gui.messages");

    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"addMapSo", "addMapKVSo","addMapKVSt", "addHash", "remMapSo", "remMapKVSo","remMapKVSt", "remHash"};
    private final int[] TIRIAMI_KIEKIAI = {10000, 20000, 40000, 80000};


    // put
    private final MapKTU<String, Soldier> atvaizdis1
            = new MapKTU(10, 0.75f, HashType.DIVISION);
    private final MapKTUOA<String, Soldier> atvaizdis2
            = new MapKTUOA(new String(), new Soldier(), 10, 0.75f, HashType.DIVISION);
   private final MapKTUOA<String, String> atvaizdis3
            = new MapKTUOA(new String(), new String(), 10, 0.75f, HashType.DIVISION);
    private  final HashSet<String> atvaizdis4 = new HashSet<String>();


    private final Queue<String> chainsSizes = new LinkedList<>();

    public SpeedTrial() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
//
    public void SisteminisTyrimas() throws Exception {

            chainsSizes.add(MESSAGES.getString("msg4"));
            chainsSizes.add("   kiekis      " + TYRIMU_VARDAI[0] + "   " + TYRIMU_VARDAI[1]);
            for (int k : TIRIAMI_KIEKIAI) {
                Soldier[] autoArray = SoldierMaker.makeSoldiers(k);
                String[] autoIdArray = SoldierMaker.makeSoldierIds(k);

                ArrayList<String> words = words(k);

                atvaizdis1.clear();
                atvaizdis2.clear();
                atvaizdis3.clear();
                atvaizdis4.clear();
                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    atvaizdis1.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                String str = "   " + k + "          " + atvaizdis1.getMaxChainSize();
                for (int i = 0; i < k; i++) {
                    atvaizdis2.put(autoIdArray[i], autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[1]);
                for (int i = 0; i < k; i++) {
                    atvaizdis3.put(String.valueOf(i), words.get(i));
                }
                tk.finish(TYRIMU_VARDAI[2]);

                for (int i = 0; i < k; i++) {
                    atvaizdis4.add(words.get(i));
                }
                tk.finish(TYRIMU_VARDAI[3]);
                chainsSizes.add(str);
                for (String s : autoIdArray) {
                    atvaizdis1.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[4]);

                for (String s : autoIdArray) {
                    atvaizdis2.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[5]);
                for (int i = 0; i < k; i++) {
                    atvaizdis3.remove(String.valueOf(i));
                }
                tk.finish(TYRIMU_VARDAI[6]);
                for (String s : words) {
                    atvaizdis4.remove(s);
                }
                tk.finish(TYRIMU_VARDAI[7]);
                tk.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.stream().forEach(p -> sb.append(p).append(System.lineSeparator()));
            tk.logResult(sb.toString());
            tk.logResult(FINISH_COMMAND);

    }

    private ArrayList<String> words(int k) throws Exception {
        ArrayList<String> words = new ArrayList<>(k);

        File file = new File("C:\\Users\\Boratas\\Lab3Dimsa\\src\\gui\\zodynas.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            words.add(st);
        return  words;
    }




        public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
