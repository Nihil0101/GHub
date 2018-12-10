package Lab3Dimsa;
import gui.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class SoldierMaker {
    private static final String ID_CODE = "DEU";
    private static int serNr = 1000;

    private Soldier[] soldiers;
    private String [] keys;
    private int amount = 0, idAmount = 0;

    public static Soldier[] makeSoldiers(int amount){
        Soldier[]soldiers = IntStream.range(0, amount)
                .mapToObj(i -> new Soldier.Recruiter().recruitRandom())
                .toArray(Soldier[]::new);
        Collections.shuffle(Arrays.asList(soldiers));
        return soldiers;
    }
    public static String[] makeSoldierIds(int amount) {
        String[] keys = IntStream.range(0, amount)
                .mapToObj(i -> ID_CODE + (serNr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(keys));
        return keys;
    }
    public Soldier[] makeAndSendSoldiers(int setsSize,int setsSample) throws MyException {
        if (setsSample > setsSize) {
            setsSample = setsSize;
        }
        soldiers = makeSoldiers(setsSize);
        keys = makeSoldierIds(setsSize);
        this.amount = setsSample;
        return Arrays.copyOf(soldiers, setsSample);
    }
    public Soldier sendSoldier() {
        if (soldiers == null) {
            throw new MyException("soldiersNotGenerated");
        }
        if (amount < soldiers.length) {
            return soldiers[amount++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }

    public String getSoldierIdFromBase() {
        if (keys == null) {
            throw new MyException("soldiersIdsNotGenerated");
        }
        if (idAmount >= keys.length) {
            idAmount = 0;
        }
        return keys[idAmount++];
    }
}
