package Lab2Dimsa;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

public class Soldier implements KTUable<Soldier> {
    
    // bendri duomenys visiems kareiviams
    final static private int recruitmentAge = 18;
    final static private int currentYear  = LocalDate.now().getYear();
    final static private double minHeight =     165.1;
    final static private double maxHeight =     228.6;

    // kiekvieno kareivio individualūs duomenys
    private String name;
    private String nationality;
    private int birthYear;  
    private int yearsServed;
    private double height; 

    
    public Soldier() {
    }
    public Soldier(String name, String nationality,
                        int birthYear, int yearsServed, double height){
        this.name = name;
        this.nationality = nationality;
        this.birthYear = birthYear;
        this.yearsServed = yearsServed;
        this.height = height;
    }
    public Soldier(String dataString){
        this.parse(dataString);
    }
    @Override
    public Soldier create(String dataString) {
        Soldier a = new Soldier();
        a.parse(dataString);
        return a;
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
    public double getHeight() {
        return height;
    }
    // keisti bus galima tik ūgį - kiti parametrai pastovūs
    public void setHeight(double height) {
        this.height = height;
    }
    @Override
    public int compareTo(Soldier a) { 
        // lyginame pagal ūgį
        double otherHeight = a.getHeight();
        if (height < otherHeight) return -1;
        if (height > otherHeight) return +1;
        return 0;
    }
    public final static Comparator<Soldier> byNameAndNationality =
              new Comparator<Soldier>() {
       @Override
       public int compare(Soldier a1, Soldier a2) {
          // pradžioje pagal vardus, o po to pagal tautybę
          int cmp = a1.getName().compareTo(a2.getName());
          if(cmp != 0) return cmp;
          return a1.getNationality().compareTo(a2.getNationality());
       }
    };
    public final static Comparator byHeight = new Comparator() {
       @Override
       public int compare(Object o1, Object o2) {
          double k1 = ((Soldier) o1).getHeight();
          double k2 = ((Soldier) o2).getHeight();
          // didėjanti tvarka, pradedant nuo mažiausios
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0;
       }
    };
    public final static Comparator byYearsAndHeight = new Comparator() {
       @Override
       public int compare(Object o1, Object o2) {
          Soldier a1 = (Soldier) o1;
          Soldier a2 = (Soldier) o2;
          // metai mažėjančia tvarka, esant vienodiems lyginamas ūgis
          if(a1.getBirthYear() < a2.getBirthYear()) return 1;
          if(a1.getBirthYear() > a2.getBirthYear()) return -1;
          if(a1.getHeight() < a2.getHeight()) return 1;
          if(a1.getHeight() > a2.getHeight()) return -1;
          return 0;
       }
    };
    // metodas main = tiesiog paprastas pirminis kareivių išbandymas
    public static void main(String... args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT")); 
        Soldier a1 = new Soldier("Jonas","Lietuvis", 1997, 2,  175);
        Soldier a2 = new Soldier("Zbiknevas","Lenkas", 2015, 0, 65);
        Soldier a3 = new Soldier();
        Soldier a4 = new Soldier();
        a3.parse("Antanas Lietuvis 1994 3 166,5");
        a4.parse("Igor Rusas 1990 6  166,8");
        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
    }    
}
