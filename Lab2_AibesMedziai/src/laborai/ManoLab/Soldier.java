package laborai.ManoLab;

import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public final class Soldier implements KTUable<Soldier> {

    // bendri duomenys visiems kareiviams
    final static private int recruitmentAge = 19;
    final static private int currentYear  = LocalDate.now().getYear();
    final static private double minHeight =     165.1;
    final static private double maxHeight =     228.6;
    private static final String idMilitaryBranch = "Wehrmacht";
    private static int tag = 18;
    final String soldierRegNr;
    // kiekvieno kareivio individualūs duomenys
    private String name;
    private String nationality;
    private int birthYear;
    private int yearsServed;
    private double height;


    public Soldier() { soldierRegNr = idMilitaryBranch + (tag++);
    }
    public Soldier(String name, String nationality,
                   int birthYear, int yearsServed, double height){
        soldierRegNr = idMilitaryBranch + (tag++);
        this.name = name;
        this.nationality = nationality;
        this.birthYear = birthYear;
        this.yearsServed = yearsServed;
        this.height = height;
        validate();
    }
    public Soldier(String dataString){

        soldierRegNr = idMilitaryBranch + (tag++);
        this.parse(dataString);
    }

    public Soldier(Recruiter recruiter) {
        soldierRegNr = idMilitaryBranch + (tag++);
        this.name = recruiter.name;
        this.nationality = recruiter.nationality;
        this.birthYear = recruiter.birthYear;
        this.yearsServed = recruiter.yearsServed;
        this.height = recruiter.height;
        validate();
    }

    @Override
    public Soldier create(String dataString) {
        return new Soldier(dataString);
    }
    @Override
    public final void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            name   = ed.next();
            nationality = ed.next();
            birthYear= ed.nextInt();
            yearsServed    = ed.nextInt();
            setHeight(ed.nextDouble());
        } catch (InputMismatchException  e) {
            Ks.ern("Bad data format about Soldier-> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Missing data about Soldier -> " + dataString);
        }
    }
    @Override
    public String validate() {
        String errotType = "";
        if (LocalDate.now().getYear() - birthYear < recruitmentAge)
            errotType = "Looks like you are to young to serve in military, you need to be atleast: " +
                    recruitmentAge + " years old";
        if(birthYear > currentYear){
            errotType = "Oh my God you are from the future :O";
        }
        if (height < minHeight || height > maxHeight )
            errotType += " Your height must be between " + minHeight +
                    " and " + maxHeight  + " in order to serve";
        return errotType;
    }
    @Override
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4d %7d %8.1f %s",
                name, nationality, birthYear, yearsServed, height, validate());
    };
    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getYearServed() {
        return yearsServed;
    }

    public void setYearServed(int yearsServed){ this.yearsServed = yearsServed;}

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSoldierRegNr() { return soldierRegNr;}

    @Override
    public int compareTo(Soldier a) {
       return getSoldierRegNr().compareTo(a.getSoldierRegNr());
    }
    public static Comparator<Soldier> byName = (Soldier s1, Soldier s2) -> s1.name.compareTo(s2.name);

    public static Comparator<Soldier> byHeight = (Soldier s1, Soldier s2) -> {
        if(s1.height < s2.height) {
        return -1;
        }
        if(s1.height > s2.height){
            return +1;
        }
        return 0;
    };
    public static Comparator<Soldier> byYearsAndHeight = (Soldier s1, Soldier s2) -> {
        if(s1.getBirthYear() > s2.getBirthYear()) {
        return +1;
        }
        if(s1.getBirthYear() < s2.getBirthYear()) {
            return -1;
        }
        if(s1.height > s2.height) {
            return +1;
        }
        if(s1.height < s2.height) {
            return -1;
        }
        return 0;
    };
    public static class Recruiter {
        private final static Random RANDOM = new Random(2017);
        private final static String [] [] NATIONALITIES = {
                {"Lietuvis", "Antanas", "Juozas", "Rimas", "Dominykas"},
                {"Vokietis", "Hansas", "Erichas", "Adolfas", "Josephas", "Albert"},
                {"Italas", "Milanas", "Maksas"},
                {"Rusas", "Igoris", "Viktoras", "Leonidas"},
                {"Iranietis", "Abdulas", "Alkan", "Saydas", "Imanas"},
                {"Lenkas", "Zbignevas", "Albinas", "Vladas"}
        };

        private String name;
        private String nationality;
        private int birthYear;
        private int yearsServed;
        private double height;

        public Soldier recruit() {return new Soldier(this);}

        public Soldier recruitRandom() {
            int ta = RANDOM.nextInt(NATIONALITIES.length);
            int vi = RANDOM.nextInt(NATIONALITIES[ta].length-1)+1;
            return new Soldier(NATIONALITIES[ta][0],
                    NATIONALITIES[ta][vi],
                    1970 + RANDOM.nextInt(20),
                    1 + RANDOM.nextInt(10),
                    166 + RANDOM.nextDouble()*2);
        }
        public Recruiter birthYear(int birthYear){
            this.birthYear = birthYear;
            return this;
        }
        public Recruiter name(String name){
            this.name = name;
            return this;
        }
        public Recruiter nationality (String nationality){
            this.nationality = nationality;
            return this;
        }
        public Recruiter yearsServed(int yearsServed)
        {
            this.yearsServed = yearsServed;
            return this;
        }
        public Recruiter height(double height){
            this.height = height;
            return this;
        }
    }

}
