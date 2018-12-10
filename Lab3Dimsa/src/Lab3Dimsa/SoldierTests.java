package Lab3Dimsa;//package Lab3Dimsa;

import Lab3Dimsa.SpeedTrial;
import studijosktu.MapKTUx;
import studijosktu.Ks;
import java.util.Locale;
import studijosktu.HashType;
import Lab3Dimsa.Soldier;
import Lab3Dimsa.SoldierMaker;
public class SoldierTests {
    public static void main(String [] args){
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        atvaizdzioTestas();
        //greitaveikosTestas();
    }
    public static void atvaizdzioTestas(){
        Soldier a1 = new Soldier("Jonas","Lietuvis", 1997, 2,  175);
        Soldier a2 = new Soldier("Zbiknevas","Lenkas", 1980, 1, 167);
        Soldier a3 = new Soldier("Antanas", "Lietuvis", 1994, 3, 166.5);
        Soldier a4 = new Soldier("Igor Rusas 1990 6  166.8");
        Soldier a5 = new Soldier.Recruiter().recruitRandom();
        Soldier a6 = new Soldier("Max Italas 1992 5 175");
        Soldier a7 = new Soldier("Abdul   Iranietis  1995  1 185.3");

        String[] soldierId ={"TR156", "TR102", "TR178", "TR171", "TR105", "TR106", "TR107", "TR108"};
        int id = 0;
        MapKTUx<String, Soldier> atvaizdis
                = new MapKTUx(new String(), new Soldier(), HashType.DIVISION);
        MapKTUx<String, Soldier> atvaizdis2
                = new MapKTUx(new String(), new Soldier(), HashType.DIVISION);
        // Reikšmių masyvas
        Soldier[] soldier ={a1, a2, a3, a4, a5, a6, a7};
        for(Soldier s: soldier){
            atvaizdis.put(soldierId[id],s);
            atvaizdis2.put("TR"+id,new Soldier("Aleksas Britas 1996 2 170.5"));
            id++;

        }
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(atvaizdis.contains(soldierId[6]));
        Ks.oun(atvaizdis.contains(soldierId[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(atvaizdis.remove(soldierId[1]));
        Ks.oun(atvaizdis.remove(soldierId[7]));
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Tuščių skaičius");
        Ks.oun(atvaizdis.numberOfEmpty());
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(atvaizdis.get(soldierId[2]));
        Ks.oun(atvaizdis.get(soldierId[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(atvaizdis);
        Ks.oun("Contains Value:");
        Ks.oun(atvaizdis.containsValue(soldier[4]));
        Ks.oun("Vidutinis grandinės ilgis");
        Ks.oun(atvaizdis.averageChainSize());
        Ks.oun("Replace all");
        atvaizdis.replaceAll(soldier[4],soldier[5]);
        Ks.oun(atvaizdis);
        Ks.oun("Put all");
        atvaizdis.putAll(atvaizdis2);
        Ks.oun(atvaizdis);

    }
    private static void greitaveikosTestas() {
        System.out.println("Greitaveikos tyrimas:\n");
        SpeedTrial gt = new SpeedTrial();
        //Šioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(),
                "Greitaveikos_tyrimo_gija").start();
        try {
            String result;
            while (!(result = gt.getResultsLogger().take())
                    .equals(SpeedTrial.FINISH_COMMAND)) {
                System.out.println(result);
                gt.getSemaphore().release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gt.getSemaphore().release();
    }
}
