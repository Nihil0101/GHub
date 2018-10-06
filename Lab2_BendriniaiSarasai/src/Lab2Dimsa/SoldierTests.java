package Lab2Dimsa;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import studijosKTU.*;

public class SoldierTests{
    ListKTUx<Soldier> testers = new ListKTUx<>(new Soldier());

    void methodChooser(){
        checkIndividualSoldiers();
        formSoldierList();
        viewList();
        updateList();
        checkSoldiers();
        checkSorting();
    }

    void checkIndividualSoldiers() {
        Soldier a1 = new Soldier("Jonas","Lietuvis",1997,1,170);
        Soldier a2 = new Soldier("Antanas","Lietuvis",1992,5,166);
        Soldier a3 = new Soldier("Igor","Rusas",1996,3,180);
        Soldier a4 = new Soldier();
        Soldier a5 = new Soldier();
        Soldier a6 = new Soldier();
        a4.parse("Max Italas 1992 5 175");
        a5.parse("Zbignev Lenkas 1986 10 195");
        a6.parse("Abdul   Iranietis  1995  1 185,3");

        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun("Average years served between first 3 soldiers: "+
                (a1.getYearServed()+a2.getYearServed()+a3.getYearServed())/3);
        Ks.oun(a4);
        Ks.oun(a5);
        Ks.oun(a6);
        //could have added new parameter to Soldier class named age
        Ks.oun("Average age between next 3 soldiers " + (((LocalDate.now().getYear() - a4.getBirthYear()) + (LocalDate.now().getYear() - a5.getBirthYear()) + (LocalDate.now().getYear() - a6.getBirthYear())))/3
                );
    }
    void formSoldierList() {
        Soldier a1 = new Soldier("Jonas","Lietuvis",1997,1,170);
        Soldier a2 = new Soldier("Antanas","Lietuvis",1992,5,166);
        Soldier a3 = new Soldier("Igor","Rusas",1996,3,180);
        testers.add(a1);
        testers.add(a2);
        testers.add(a3);
        testers.println("Pirmi 3 auto");
        testers.add("Max Italas 1992 5 175");
        testers.add("Zbignev Lenkas 1986 10 195");
        testers.add("Abdul   Iranietis  1995  1 185,3");

        testers.println("All 6 soldier");
        testers.forEach(System.out::println);
        Ks.oun("Average years served between first 3 soldiers: "+
                (testers.get(0).getYearServed()+testers.get(1).getYearServed()+
                 testers.get(2).getYearServed())/3);

        Ks.oun("Average age between next 3 soldiers: "+
                (testers.get(3).getHeight()+testers.get(4).getHeight()+
                 testers.get(5).getHeight()));
        testers.set(4, a3);

   }
    void viewList(){
        int sk=0;
        for (Soldier a: testers){
            if (a.getName().compareTo("Antanas")==0)
                sk++;
        }
        Ks.oun("Number of soldier named Antanas "+sk);
    }
    void updateList(){
        testers.add("Lee Kinas  1995 2 189,0");
        testers.add("Karolis Lietuvis   1998 1 180,0");
        testers.add("Hans Vokietis  1999 1 177,0");
        testers.add("Suomi Suomis      1996 3 167,0");
        testers.println("List of tested soldiers");
        testers.save("ban.txt");
    }
    void checkSoldiers(){
        SoldierSelection aSoldier = new SoldierSelection();
        
        aSoldier.allSoldiers.load("ban.txt");
        aSoldier.allSoldiers.println("Bandomasis rinkinys");

        testers = aSoldier.selectYoungest(2001);
        testers.println("Pradedant nuo 1994");

        testers = aSoldier.seleectByHeight(175, 200);
        testers.println("Ūgis tarp 175 cm ir 200");

        testers = aSoldier.tallestSoldiers();
        testers.println("Auksčiausi kariai");

        testers = aSoldier.selectByNationality("Lietuvis");
        testers.println("Turi būti tik Lietuviai");

        testers = aSoldier.selectByNationality("Lietuvis A");

        testers.println("Turi būti tik Lietuviai Antanai");
        int sk=0;
        for (Soldier a: testers){
                sk++;    // testuojame ciklo veikimą
        }
        Ks.oun("Lietuvių Antanų kiekis = "+sk);
    }
    void checkSorting(){
        SoldierSelection aps = new SoldierSelection();

        aps.allSoldiers.load("ban.txt");
        Ks.oun("========"+aps.allSoldiers.get(0));
        aps.allSoldiers.println("Bandomasis rinkinys");
        aps.allSoldiers.sortBuble(Soldier.byNameAndNationality);
        aps.allSoldiers.println("Rūšiavimas pagal vardą ir tautybę");
        aps.allSoldiers.sortBuble(Soldier.byHeight);
        aps.allSoldiers.println("Rūšiavimas pagal aukštį");
        aps.allSoldiers.sortBuble(Soldier.byYearsAndHeight);
        aps.allSoldiers.println("Rūšiavimas pagal Metus ir Aukštį");
        aps.allSoldiers.sortBuble(orderByYearsServed);
        aps.allSoldiers.sortBuble((a, b) -> Integer.compare(a.getYearServed(), b.getYearServed()));
        aps.allSoldiers.println("Rūšiavimas pagal pratarnautą laiką");
        aps.allSoldiers.sortBuble();
        aps.allSoldiers.println("Rūšiavimas pagal compareTo - Aukštį");
    }
//
    static Comparator orderByYearsServed = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            int r1 = ((Soldier) o1).getYearServed();
            int r2 = ((Soldier) o2).getYearServed();
            // pratarnauti metai atvirkščia mažėjančia tvarka, pradedant nuo didžiausios
            if(r1<r2) return 1;
            if(r1>r2) return -1;
            return 0;
        }
    };
    public static void main(String... args) {
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new SoldierTests().methodChooser();
    }
}
