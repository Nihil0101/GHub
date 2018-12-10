package Lab3Dimsa;

import studijosktu.Ks;
import studijosktu.KTUable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;

public final class Soldier implements KTUable{
    final static private int recruitmentAge = 19;
    final static private int currentYear  = LocalDate.now().getYear();
    final static private double minHeight =     165.1;
    final static private double maxHeight =     228.6;
    private String name;
    private String nationality;
    private int birthYear;
    private int yearsServed;
    private double height;

    public Soldier(){}
    public Soldier(String name, String nationality,
                   int birthYear, int yearsServed, double height){
        this.name = name;
        this.nationality = nationality;
        this.birthYear = birthYear;
        this.yearsServed = yearsServed;
        this.height = height;
        validate();
    }
    public Soldier(String dataString){
        this.parse(dataString);
    }
    public Soldier(Recruiter recruiter) {
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
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4d %7d %8.1f %s",
                name, nationality, birthYear, yearsServed, height, validate());
    }
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

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, birthYear, yearsServed, height);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Soldier other = (Soldier) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (this.birthYear != other.birthYear) {
            return false;
        }
        if (this.yearsServed != other.yearsServed) {
            return false;
        }
        if (Double.doubleToLongBits(this.height) != Double.doubleToLongBits(other.height)) {
            return false;
        }

        return true;
    }
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
