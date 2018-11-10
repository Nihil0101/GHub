package laborai.ManoLab;

import laborai.gui.fx.KsFX;
import laborai.studijosktu.*;

import java.util.*;

public class SoldierTests {
    static Soldier[] soldierBase;
    static SortedSetADTx<Soldier> sSeries = new BstSetKTUx(new Soldier(), Soldier.byHeight);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        setsTest();
    }

    static SortedSetADT generateSet(int amount, int generN) {
        soldierBase = new Soldier[generN];
        for(int i = 0; i < generN; i++){
            soldierBase[i] = new Soldier.Recruiter().recruitRandom();
        }
        Collections.shuffle(Arrays.asList(soldierBase));
        sSeries.clear();
        for(int i = 0; i < amount; i++) {
            sSeries.add(soldierBase[i]);
        }
        return sSeries;
    }

    public static void setsTest() throws CloneNotSupportedException {
        Soldier s1 = new Soldier.Recruiter().recruitRandom();
        Soldier s2 = new Soldier.Recruiter().recruitRandom();
        Soldier s3 = new Soldier.Recruiter().recruitRandom();
        Soldier s4 = new Soldier.Recruiter().recruitRandom();
        Soldier s5 = new Soldier.Recruiter().recruitRandom();
        Soldier s6 = new Soldier.Recruiter().recruitRandom();
        Soldier s7 = new Soldier.Recruiter().recruitRandom();
        Soldier s8 = new Soldier.Recruiter().recruitRandom();
        Soldier s9 = new Soldier.Recruiter().recruitRandom();

        Soldier[] soldierArray = { s9, s7, s8, s5, s1, s6};
        Ks.oun("Soldier Set:");
        BstSetKTU<Soldier> soldierSet = new BstSetKTU<Soldier>();

        for(Soldier s : soldierArray){
            soldierSet.add(s);
            Ks.oun("Set is updated: " + s + ". It's size: " + soldierSet.size());
        }
        Ks.oun("");
        Ks.oun(soldierSet.toVisualizedString(""));

        BstSetKTU<Soldier> soldierSetCopy = (BstSetKTU<Soldier>) soldierSet.clone();
        soldierSetCopy.add(s2);
        soldierSetCopy.add(s3);
        soldierSetCopy.add(s4);
        Ks.oun("Updated copy of set");
        Ks.oun(soldierSetCopy.toVisualizedString(""));

        s9.setYearServed(2);

        Ks.oun("Original:");
        Ks.ounn(soldierSet.toVisualizedString(""));

        Ks.oun("Do elements exist in set?");
        for(Soldier s : soldierArray) {
            Ks.oun(s + ": " + soldierSet.contains(s));
        }
        Ks.oun(s2 + ": " + soldierSet.contains(s2));
        Ks.oun(s3 + ": " + soldierSet.contains(s3));
        Ks.oun(s4 + ": " + soldierSet.contains(s4));
        Ks.oun("");

        Ks.oun("Do elements exist in copy of set?");
        for(Soldier s : soldierArray){
            Ks.oun(s + ": " + soldierSetCopy.contains(s));
        }
        Ks.oun(s2 + ": " + soldierSetCopy.contains(s2));
        Ks.oun(s3 + ": " + soldierSetCopy.contains(s3));
        Ks.oun(s4 + ": " + soldierSetCopy.contains(s4));
        Ks.oun("");

        Ks.oun("Removal of elements from copy. Set size before removal: " + soldierSetCopy.size());
        for(Soldier s: new Soldier[]{s2, s1, s9, s8, s5, s3, s4, s2, s7, s6, s7, s9}) {
            soldierSetCopy.remove(s);
            Ks.oun(s + " was removed from copy. Set size now is: " + soldierSetCopy.size());
        }
        Ks.oun("");

        Ks.oun("Soldier set with Iterator:");
        Ks.oun("");
        for(Soldier s: soldierSet){
            Ks.oun(s);
        }
        Ks.oun("");
        Ks.oun("Soldier set in AVL-tree: ");
        SortedSetADTx<Soldier> soldierSet3 = new AvlSetKTUx<>(new Soldier());
        for(Soldier s : soldierArray) {
            soldierSet3.add(s);
        }
        Ks.ounn(soldierSet3.toVisualizedString(""));

        Ks.oun("Soldier set with Iterator:");
        Ks.oun("");
        for(Soldier s: soldierSet3){
            Ks.oun(s);
        }

        Ks.oun("");
        Ks.oun("Soldier set with reverse Iterator: ");
        Ks.oun("");
        Iterator iterator = soldierSet3.descendingIterator();
        while(iterator.hasNext()) {
            Ks.oun(iterator.next());
        }

        Ks.oun("");
        Ks.oun("Soldier set toString() method:");
        Ks.ounn(soldierSet3);

        //skaitymai iš failų


        Ks.oun("");
        Ks.oun("Soldier who is lower than " + s7);
        Ks.oun(soldierSet.lower(s4));

        Ks.oun("");
        Ks.oun("SubSet " );
        int nr = new Random().nextInt(soldierSet.size());
        Soldier soldier = (Soldier) soldierSet.toArray()[nr];
        int nr2 = new Random().nextInt(soldierSet.size());
        Soldier soldier2 = (Soldier) soldierSet.toArray()[nr2];
        SortedSetADT<Soldier> subset = new BstSetKTU<Soldier>();
        if(nr < nr2)
            subset = soldierSet.subSet(soldier, soldier2);
        else {
            subset = soldierSet.subSet(soldier2, soldier);
        }
        Ks.oun(soldier + " " + " Pirmas");
        Ks.oun(soldier2 + " "  + " Antras");
        Ks.oun("");
        for(Soldier s: subset){
            Ks.oun(s);
        }

        Ks.oun("");
        Ks.oun("TailSet " );
        SortedSetADT<Soldier> taiSet = new BstSetKTU<Soldier>();
        int nr3 = new Random().nextInt(soldierSet.size());
        Soldier soldier3 = (Soldier) soldierSet.toArray()[nr3];
        taiSet = soldierSet.tailSet1(soldier3);
        Ks.oun(soldier3 );
        Ks.oun("");
        for(Soldier s: taiSet){
            Ks.oun(s);
        }
    }
}
