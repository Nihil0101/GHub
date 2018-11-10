package laborai.ManoLab;

import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import laborai.gui.fx.Lab2WindowFX;

/*
 * Darbo atlikimo tvarka - čia yra JavaFX pradinė klasė.
 */
public class VykdymoModulisFXM extends Application {

    public static void main(String [] args) {
        VykdymoModulisFXM.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        SoldierTests.setsTest();
        Lab2WindowFX.createAndShowFXGUI(primaryStage);
    }
}