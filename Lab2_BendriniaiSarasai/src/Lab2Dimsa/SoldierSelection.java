package Lab2Dimsa;

import studijosKTU.*;

public class SoldierSelection {

    public ListKTUx<Soldier> allSoldiers = new ListKTUx<>(new Soldier());
    private static final Soldier exampleSoldier = new Soldier();

    // suformuojamas sąrašas kareiviųpagal nurodytą amžiaus cenzą
    public ListKTUx<Soldier> selectYoungest(int year) {
        ListKTUx<Soldier> naujiAuto = new ListKTUx<>(exampleSoldier);
        for (Soldier a : allSoldiers) {
            if (a.getBirthYear() >= year) {
                naujiAuto.add(a);
            }
        }
        return naujiAuto;
    }
    // suformuojamas sąrašas kareivių, kurių ūgis yra tarp ribų
    public ListKTUx<Soldier> seleectByHeight(int h1, int h2) {
        ListKTUx<Soldier> averageSoldiers = new ListKTUx(exampleSoldier);
        for (Soldier a : allSoldiers) {
            if (a.getHeight() >= h1 && a.getHeight() <= h2) {
                averageSoldiers.add(a);
            }
        }
        return averageSoldiers;
    }
    // suformuojamas sąrašas aukščiausių kareivių
    public ListKTUx<Soldier> tallestSoldiers() {
        ListKTUx<Soldier> tallestSoldier = new ListKTUx(exampleSoldier);
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxHeight = 0;
        for (Soldier a : allSoldiers) {
            double height = a.getHeight();
            if (height >= maxHeight) {
                if (height > maxHeight) {
                    tallestSoldier.clear();
                    maxHeight = height;
                }
                tallestSoldier.add(a);
            }
        }
        return tallestSoldier;
    }
    // suformuojams sąrašas kareivių, kurių duomenys atitinka nurodytus
    public ListKTUx<Soldier> selectByNationality(String nationality) {
        ListKTUx<Soldier> nationalSoldier = new ListKTUx(exampleSoldier);
        for (Soldier a : allSoldiers) {
            String fullEthnicity = a.getNationality()+ " " + a.getName();
            if (fullEthnicity.startsWith(nationality)) {
                nationalSoldier.add(a);
            }
        }
        return nationalSoldier;
    }
    // metodo main nėra -> demo bandymai klasėje SoldierTests
}
