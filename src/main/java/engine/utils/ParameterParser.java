package engine.utils;

import engine.launcher.Parameters;
import engine.launcher.SpawnMode;
import org.joml.Vector4f;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ParameterParser {

    /**
     * Checks the data types of the input values and creates a Parameters object.
     */
    public static Parameters parseParameters(JTextField[] intValues, JTextField[] floatValues, JTextField[] vectorValues, JComboBox spawnmode) {
        try {
            int windowWidth = Integer.parseInt(intValues[5].getText());
            int windowHeight = Integer.parseInt(intValues[6].getText());

            Parameters simulationParameters = new Parameters(
                    Integer.parseInt(intValues[0].getText()),
                    Integer.parseInt(intValues[1].getText()),
                    windowWidth, windowHeight, new Vector4f(
                            Float.parseFloat(vectorValues[0].getText().split(",")[0]),
                            Float.parseFloat(vectorValues[0].getText().split(",")[1]),
                            Float.parseFloat(vectorValues[0].getText().split(",")[2]),
                            Float.parseFloat(vectorValues[0].getText().split(",")[3])
                    ),
                    new Vector4f(
                            Float.parseFloat(vectorValues[1].getText().split(",")[0]),
                            Float.parseFloat(vectorValues[1].getText().split(",")[1]),
                            Float.parseFloat(vectorValues[1].getText().split(",")[2]),
                            Float.parseFloat(vectorValues[1].getText().split(",")[3])
                    ),
                    Float.parseFloat(floatValues[0].getText()),
                    Float.parseFloat(floatValues[1].getText()),
                    Integer.parseInt(intValues[2].getText()),
                    Float.parseFloat(floatValues[5].getText()),
                    (SpawnMode) spawnmode.getSelectedItem(),
                    Float.parseFloat(floatValues[2].getText()),
                    Float.parseFloat(floatValues[3].getText()),
                    Float.parseFloat(floatValues[4].getText()),
                    Integer.parseInt(intValues[3].getText()),
                    Float.parseFloat(floatValues[6].getText()),
                    Float.parseFloat(floatValues[7].getText()),
                    Float.parseFloat(floatValues[8].getText()),
                    Integer.parseInt(intValues[4].getText())
            );

            return simulationParameters;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Show an error message if there are invalid input values
            showMessageDialog(null, "Invalid values");
        }
        return null;
    }
}
