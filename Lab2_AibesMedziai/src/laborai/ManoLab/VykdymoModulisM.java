package laborai.ManoLab;

import laborai.gui.swing.Lab2Window;
import java.util.Locale;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class VykdymoModulisM {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        SoldierTests.setsTest();
        Lab2Window.createAndShowGUI();
    }
}
