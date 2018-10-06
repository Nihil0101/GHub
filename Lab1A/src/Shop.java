import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import static java.nio.charset.StandardCharsets.*;

/*
U1-23. Juvelyrikos parduotuvė. Turite UAB „Blizgučiai“ parduodamų žiedų sąrašą.
Duomenų faile pateikta informacija apie žiedus: gamintojas, pavadinimas,  metalas, svoris, dydis, praba, kaina.
   Raskite sunkiausią žiedą, ekrane atspausdinkite jo pavadinimą, metalą, skersmenį, svorį ir prabą.
   Raskite, kiek žiedų yra aukščiausios prabos, rezultatą atspausdinkite ekrane.
   Sudarykite visų metalų, iš kurių pagaminti žiedai, sąrašą. Metalų pavadinimus surašykite į failą „Metalai.csv“.
   Sudarykite 12-13 dydžio žiedų, kurių kaina iki 300 eurų, sąrašą.
  Į failą „Žiedai.csv“ įrašykite žiedų dydžius, metalus iš kurio jie pagaminti, prabas, svorius ir kainas.
 */
public class Shop {
    public String Name;
    private List<Ring> rings;

    private List<String> input = Arrays.asList(new String[]{
            "UAB_KalvisA     Pirmasis            Sidabras    6 12.0 550 100",
            "UAB_NykštukasA  PirmasisYpatingas   Sidabras    7 13.0 750 150",
            "UAB_MilžinasA   PenkiųMetų          Platina     9 12.5 750 1000",
            "UAB_VarnaA      Paprastas           Bronza      4 19.0 450 50",
    });
    public Shop(String Name) {
        this.Name = Name;
        rings = new ArrayList<>();
    }
    void print(List<Ring>rings){
        for(Ring d : rings)
            System.out.println(d);
    }

    private void trial1(){
        System.out.println("***** Parduotuvė: " + Name + " vienetiniai žiedai");
        Ring si1 = new Ring("UAB Kalvis","Pirmasis","Auksas",6,14.0,550,100);
        Ring si2 = new Ring("UAB Nykštukas","Metinis","Sidabras",8,16.0,600,130);
        System.out.println(si1);
        System.out.println(si2);

    }
    private void trial2(){
        rings.add(new Ring("UAB_Kalvis","Pirmasis","Auksas",6,14.0,550,100));
        rings.add(new Ring("UAB_Milžinas","Pirmasis2","Sidabras",5,16.0,750,150));
        rings.add(new Ring("UAB_Nykštukas","Metininis","Platina",8,12.0,750,1000));
        rings.add(new Ring("UAB_Metalikas","Penkmečio","Auksas",9,15.0,560,200));
        System.out.println("***** Parduotuvė: " + Name + " iš konstruktoriaus");
        print(rings);
    }
    private void  trial3(){
        for(String data:input)
            rings.add(Ring.parse(data));
        System.out.println("***** Parduotuvė: " + Name + " po papildymo iš eilučių");
        print(rings);
    }
    public Ring heaviestRing(){
        Integer max = 0;
        Ring ringMax = null;
        for(Ring ri : rings)
            if(ri.weight > max){
                max = ri.weight;
                ringMax = ri;
            }
            return ringMax;
    }
    public Integer numberOfHighestHallmarks(){
        Integer max = 0;
        Integer hallmarkCount = 0;
        for(Ring ri : rings) {
            if (ri.hallmark > max) {
                max = ri.hallmark;
            }
        }
        for(Ring ri : rings) {
            if (ri.hallmark.compareTo(max) == 0) {
                hallmarkCount++;
            }
        }
        return hallmarkCount;
    }
    /*public List<Ring> size12And13RingsUnder300Euros(){
        List<Ring> under300Euros = new ArrayList<>();
        for(Ring ri : rings)
            if(ri.size==12 || ri.size == 13 & ri.price<300)
                under300Euros.add(ri);
            return under300Euros;
    }*/
    public List<Ring> filterRingsBySizeAndPrice(double smallestSize, double highestSize, int highestPrice){
        List<Ring> filteredRings = new ArrayList<>();
        for(Ring ri : rings)
            if(ri.size>=smallestSize & ri.size <= highestSize & ri.price<highestPrice)
                filteredRings.add(ri);
        return filteredRings;
    }
    public void allMetals(List<String> listOfAllMetals){
        for(Ring ri : rings){
            int ind = listOfAllMetals.indexOf(ri.metal);
            if(ind < 0)
                listOfAllMetals.add(ri.metal);
        }


    }
    public void reportShopRings(){
        System.out.println("***** Parduotuvės " + Name + " ATASKAITA");
        System.out.println("----- Sunkiausias žiedas yra:");
        System.out.println(heaviestRing());
        System.out.println("----- Aukščiausios prabos žiedų kiekis yra:");
        System.out.println(numberOfHighestHallmarks());
        List<String> listOfAllMetals = new ArrayList<>();
        allMetals(listOfAllMetals);
        System.out.println("----- Metalų sąrašas:");
        for(String metalList : listOfAllMetals)
            System.out.println(metalList);
        double minimumSize = 12.0;
        double maximumSize = 13.0;
        int filterPrice = 300;
        System.out.println("-----Žiedai, kurių dydžiai yra tarp " + minimumSize + " ir " + maximumSize + " bei pigesni nei " + filterPrice + " eurų");
        print(filterRingsBySizeAndPrice(minimumSize,maximumSize,filterPrice));

    }


    public static void main(String... args)
    {
        Shop shop1 = new Shop("UAB Blizgučiai");
        shop1.trial1();
        shop1.trial2();
        shop1.trial3();
        shop1.reportShopRings();
        System.out.println("Bandymai atlikti");
    }
}
