/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai yra darbo su sąrašais greitaveikos tyrimo klasė.
 * Pavyzdyje pateiktas rikiavimo metodų tyrimas.
 * Tyrimo metu pateiktais metodais naudojamasi kaip šablonais,
 * išbandant įvairius rūšiavimo aspektus.
   *  IŠSIAIŠKINKITE metodų sudarymą, jų paskirtį.
   *  SUDARYKITE sąrašo peržiūros antišablono efektyvumo tyrimą.
   *  PASIRINKITE savo objektų klasę ir sudarykite jų generavimo metodą.
   ****************************************************************************/
package Lab2Dimsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;
/*
 */
public class GreitaveikosTyrimas {
    Soldier[] soldierBase1;
    ArrayList<Integer> aList = new ArrayList();
    LinkedList<Integer> lList = new LinkedList();
    ListKTU<Soldier> aSeries = new ListKTU<>();
    ListKTU<Soldier> bSeries = new ListKTU<>();
    Random ag = new Random();  // atsitiktinių generatorius
    //int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    void generateSoldiers(int kiekis){
       String[][] am = { // galimų automobilių markių ir jų modelių masyvas
          {"Lietuvis", "Antanas", "Juozas", "Rimas", "Dominykas"},
          {"Vokietis", "Hansas", "Erichas", "Adolfas", "Josephas", "Albert"},
          {"Italas", "Milanas", "Maksas"},
          {"Rusas", "Igoris", "Viktoras", "Leonidas"},
          {"Iranietis", "Abdulas", "Alkan", "Saydas", "Imanas"},
          {"Lenkas", "Zbignevas", "Albinas", "Vladas"}
       };
        soldierBase1= new Soldier[kiekis];
        ag.setSeed(2017);
        for(int i=0;i<kiekis;i++){
            int ta = ag.nextInt(am.length);        // tautybės indeksas  0..
            int vi = ag.nextInt(am[ta].length-1)+1;// modelio indeksas 1..
            soldierBase1[i]= new Soldier(am[ta][0], am[ta][vi],
                1970 + ag.nextInt(20),           // metai tarp 1994 ir 2013
                1 + ag.nextInt(10),      // rida tarp 6000 ir 228000
                166 + ag.nextDouble()*2); // kaina tarp 1000 ir 351_000
        }
        Collections.shuffle(Arrays.asList(soldierBase1));
        aSeries.clear();
        for(Soldier a: soldierBase1) aSeries.add(a);
//        for(Soldier b: soldierBase1) bSeries.add(b);
//        bSeries.addAll(5, aSeries);
//        Object[] a = aSeries.toArray();
//        bSeries.remove(aSeries.get(2));
//        Object[] b = bSeries.toArray();
//        
    }
    
    void generateIntegeratArray(int kiekis){
        ag.setSeed(2017);
        for(int i = 0; i < kiekis; i++){
            aList.add((ag.nextInt()));
            lList.add(ag.nextInt());
        }
        
    }
    
    void paprastasTyrimas(int elementųKiekis){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generateSoldiers(elementųKiekis);
        ListKTU<Soldier> aSeries2 = aSeries.clone();
        ListKTU<Soldier> aSeries3 = aSeries.clone();
        ListKTU<Soldier> aSeries4 = aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sortSystem();
        long t3=System.nanoTime();
        aSeries2.sortSystem(Soldier.byHeight);
        long t4=System.nanoTime();
        aSeries3.sortBuble();
        long t5=System.nanoTime();
        aSeries4.sortBuble(Soldier.byHeight);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimas(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generateSoldiers(kiekis);
           ListKTU<Soldier> aSeries2 = aSeries.clone();
           ListKTU<Soldier> aSeries3 = aSeries.clone();
           ListKTU<Soldier> aSeries4 = aSeries.clone();

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sortSystem();
            tk.finish("SysBeCom");
            aSeries2.sortSystem(Soldier.byHeight);
            tk.finish("SysSuCom");
            aSeries3.sortBuble();
            tk.finish("BurBeCom");
            aSeries4.sortBuble(Soldier.byHeight);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
    void sisteminisTyrimasKelimoKvadratu(){
        double skaicius;
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for(int kiekis : tiriamiKiekiai)
        {
            generateSoldiers(kiekis);
        tk.start();
        for(Soldier s : aSeries)
            skaicius = s.getHeight() * s.getHeight();
        tk.finish("x*x");
        for(Soldier s : aSeries)
            Math.pow(s.getHeight(),2);
        tk.finish("math.pow(x,2)");
        tk.seriesFinish();
        }
        
    }
    void sisteminisTyrimasArrayirLinked(){
        
         Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for(int kiekis : tiriamiKiekiai){
           // generateSoldiers(kiekis);
        tk.start();
        for(int i = 0; i < kiekis;i++)
            aList.add((aList.size() / 2), i);
        tk.finish("ArrayList<Integer> add");
         for(int i = 0; i < kiekis;i++)
            lList.add((lList.size() / 2), i);
         tk.finish("LinkedList<Integer> add");
        tk.seriesFinish();
        }
    }
    void paprastasTyrimasKėlimasKvadratu(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        double skaicius;
        long t0 = System.nanoTime();
        generateSoldiers(elementųKiekis);
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        for (Soldier s : aSeries) {
            //Math.sqrt(s.getHeight());
            skaicius = s.getHeight() * s.getHeight();
        }
        long t3 = System.nanoTime();
        for (Soldier s : aSeries) {
            Math.pow(s.getHeight(),2);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }
    void paprastasTyrimasArrayirList(int elementųKiekis){
       // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        //generateIntegeratArray(elementųKiekis);
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        for(int i=0;i<elementųKiekis;i++)
        {
        aList.add((aList.size() / 2),i);
        }
        long t3 = System.nanoTime();
        for(int i=0;i<elementųKiekis;i++)
        {
        lList.add((lList.size() / 2),i);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9); 
    }
    
    
    void tyrimoPasirinkimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateSoldiers(20);
        for(Soldier a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimas(n);
        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
        sisteminisTyrimas();
        
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - skaicius = s.getHeight()*s.getHeight");
        Ks.oun("4 - Math.pow(s.getHeight(),2)");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasKėlimasKvadratu(n);
        }
        sisteminisTyrimasKelimoKvadratu();
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - ArrayList<Integer> add");
        Ks.oun("4 - LinkedList<Integer> add");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasArrayirList(n);
        }
        sisteminisTyrimasArrayirLinked();
    }
   public static void main(String[] args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new GreitaveikosTyrimas().tyrimoPasirinkimas();
   }    
}
