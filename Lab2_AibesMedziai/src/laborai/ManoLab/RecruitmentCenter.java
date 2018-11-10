package laborai.ManoLab;

import laborai.gui.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class RecruitmentCenter {
    private static Soldier[] soldiers;
    private static int startIndex = 0, finalIndex = 0;
    private static boolean isStart = true;

    public static Soldier[] generate(int amount) {
        soldiers = new Soldier[amount];
        for (int i = 0; i < amount; i++) {
            soldiers[i] = new Soldier.Recruiter().recruitRandom();
        }
        return soldiers;
    }

    public static Soldier[] generateAndShuffle(int setsSize, double distributionKof) throws MyException {
        return generateAndShuffle(setsSize, setsSize, distributionKof);
    }

    /**
     * @param setsSize
     * @param setsMat
     * @param distributionKof
     * @return Returns setsMat length array
     * @throws MyException
     */
    public static Soldier[] generateAndShuffle(int setsSize, int setsMat, double distributionKof) throws MyException {
        soldiers = generate(setsSize);
        return myShuffle(soldiers, setsSize, distributionKof);
    }

    public static Soldier[] myShuffle(Soldier[] soldierBase, int amount, double distributionKof) throws MyException {
        if (soldierBase == null) {
            throw new IllegalArgumentException("soldierBase yra null");
        }
        if (amount <= 0) {
            throw new MyException(String.valueOf(amount), 1);
        }
        if (soldierBase.length < amount) {
            throw new MyException(soldierBase.length + " >= " + amount, 2);
        }
        if ((distributionKof < 0) || (distributionKof > 1)) {
            throw new MyException(String.valueOf(distributionKof), 3);
        }
        int amountLeft = soldierBase.length - amount;
        int startIndex = (int) (amountLeft * distributionKof / 2);

        Soldier[] startingSoldierMat = Arrays.copyOfRange(soldierBase, startIndex, startIndex + amount);
        Soldier[] soldiersLeftMat = Stream.concat(Arrays.stream(Arrays.copyOfRange(soldierBase, 0, startIndex)),
                Arrays.stream(Arrays.copyOfRange(soldierBase, startIndex + amount, soldierBase.length ))).toArray(Soldier[]::new);
        Collections.shuffle(Arrays.asList(startingSoldierMat).subList(0,(int) (startingSoldierMat.length * distributionKof)));
        Collections.shuffle(Arrays.asList(soldiersLeftMat).subList(0,(int) (soldiersLeftMat.length * distributionKof)));

        RecruitmentCenter.startIndex = 0;
        finalIndex = soldiersLeftMat.length - 1;
        RecruitmentCenter.soldiers = soldiersLeftMat;
        return startingSoldierMat;


    }
    public static Soldier getFromCenter() throws MyException{
        if((finalIndex - startIndex) < 0){
            throw new MyException(String.valueOf(finalIndex - startIndex), 4);
        }
        isStart = !isStart;
        return soldiers[isStart ? startIndex++: finalIndex--];
    }
}