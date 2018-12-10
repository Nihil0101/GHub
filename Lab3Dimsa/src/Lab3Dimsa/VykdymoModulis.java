package Lab3Dimsa;

import gui.swing.Lab3Window;

import java.util.Locale;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class VykdymoModulis {

    public static void main(String[] args) {
        //Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        SoldierTests.atvaizdzioTestas();
        Lab3Window.createAndShowGUI();
    }
}
