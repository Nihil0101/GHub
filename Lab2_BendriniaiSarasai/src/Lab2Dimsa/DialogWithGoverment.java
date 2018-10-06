package Lab2Dimsa;
import java.util.Locale;
import studijosKTU.*;

public class DialogWithGoverment {
    SoldierSelection aSoldier = new SoldierSelection();

   void bendravimasSuKlientu() {
      ListKTUx<Soldier> selection = null;
      int varNr;  // skaičiavimo varijantas pasirenkamas nurodant jo numerį
      String dialogMeniu = "Pasirinkimas: "
            + "1-skaityti iš failo; 2-papildyti sąrašą; 3-jaunų atranka;\n    "
            + "4-atranka pagal aukštį; 5-auksčiausi kareiviai; 6-pagal tautybę;\n    "
            + "0-baigti skaičiavimus > ";
      while ((varNr = Ks.giveInt(dialogMeniu, 0, 6)) != 0) {
         if (varNr == 1) {
            aSoldier.allSoldiers.load(Ks.giveFileName());
            aSoldier.allSoldiers.println("Visų kareivių sąrašas");
         } else {
            if (varNr == 2) {
               String soldierData = Ks.giveString("Nurodykite kareivio vardą, "+
                            "tautybę, gimimo metus, pratarnautus metus ir ūgį\n ");
               Soldier a = new Soldier();
               a.parse(soldierData);
               String errorType = a.validate();
               if (errorType.isEmpty()) // dedame tik su gerais duomenimis
                   aSoldier.allSoldiers.add(a);
               else
                 Ks.oun("!!! Soldiers list not excepted "+errorType);
            } else {  // toliau vykdomos atskiri atrankos metodai
               switch (varNr) {
                  case 3:
                     int nR = Ks.giveInt("Nurodykite naujų kareivių metų ribą: ");
                     selection = aSoldier.selectYoungest(nR);
                     break;
                  case 4:
                     int r1 = Ks.giveInt("Nurodykite žemiausią ūgį: ");
                     int r2 = Ks.giveInt("Nurodykite aukščiausią ūgį: ");
                     selection = aSoldier.seleectByHeight(r1, r2);
                     break;
                  case 5:
                     selection = aSoldier.tallestSoldiers();
                     break;
                  case 6:
                     String nationality=Ks.giveString("Nurodykite norimą vardą ir "+
                             "tautybę, atskirtus tarpu: ");
                     selection = aSoldier.selectByNationality(nationality);
                     break;
               }
               selection.println("Štai atrinktų kareivių sąrašas");
               selection.save(Ks.giveString
                     ("Kur saugoti atrinktus kareivius (jei ne-tai ENTER) ? "));
            }
         }
      }
   }
   public static void main(String[] args) {
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
      new DialogWithGoverment().bendravimasSuKlientu();
   }
}
