package engine.launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import engine.window.Window;
import org.joml.Vector4f;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * The Launcher class provides the user with a GUI to input simulation parameters
 * and then launches the simulation with the specified settings.
 */
public class Launcher {
    private Parameters simulationParameters;
    private JFrame frame;
    private JPanel panel;
    private JComboBox spawnMode;
    private JComboBox<String> presetsComboBox;
    private JButton savePresetButton;
    private JButton deletePresetButton;
    private int windowWidth;
    private int windowHeight;
    private JTextField[] floatValues;
    private JTextField[] vectorValues;
    private JTextField[] intValues;

    /**
     * Default constructor that initializes the text fields and creates the GUI.
     */
    public Launcher() {
        this.floatValues = new JTextField[9];
        this.vectorValues = new JTextField[2];
        this.intValues = new JTextField[7];

        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    /**
     * Constructor that launches the simulation with the specified width, height, and parameters.
     *
     * @param windowWidth      The width of the simulation window.
     * @param windowHeight     The height of the simulation window.
     * @param parameters       The simulation parameters.
     */
    public Launcher(int windowWidth, int windowHeight, Parameters parameters) {
        Window window = new Window("Simulation", windowWidth, windowHeight, parameters);
        showMessageDialog(null, "Press space to run the simulation.");
        window.run();
    }

    /**
     * Creates and displays the GUI for setting simulation parameters.
     */
    private void createAndShowGUI() {
        frame = new JFrame("Simulation settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        createPresetsMenu();
        createLabelsAndTextFields();
        createRunButton();

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Creates labels and text fields for various settings.
     */
    private void createLabelsAndTextFields() {
            JLabel l1 = new JLabel("Main settings");
            Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()+2);
            l1.setFont(boldFont);
            panel.add(l1);
            panel.add(new JLabel(""));



            panel.add(new JLabel("Window width"));
            JTextField t01 = new JTextField("");
            panel.add(t01);
            intValues[5] = t01;

            panel.add(new JLabel("Window height"));
            JTextField t02 = new JTextField("");
            panel.add(t02);
            intValues[6] = t02;

            panel.add(new JLabel("Simulation width"));
            JTextField t03 = new JTextField("");
            panel.add(t03);
            intValues[0] = t03;

            panel.add(new JLabel("Simulation height"));
            JTextField t04 = new JTextField("");
            panel.add(t04);
            intValues[1] = t04;

            panel.add(new JLabel("Evaporate speed"));
            JTextField t05 = new JTextField("");
            panel.add(t05);
            floatValues[0] = t05;

            panel.add(new JLabel("Diffuse speed"));
            JTextField t06 = new JTextField("");
            panel.add(t06);
            floatValues[1] = t06;

            panel.add(new JLabel("Agents count"));
            JTextField t07 = new JTextField("");
            panel.add(t07);
            intValues[2] = t07;

            panel.add(new JLabel("Spawn mode"));
            spawnMode = new JComboBox(SpawnMode.values());
            panel.add(spawnMode);

            JLabel l2 = new JLabel("A cell settings");
            l2.setFont(boldFont);
            panel.add(l2);
            panel.add(new JLabel(""));

            panel.add(new JLabel("Color"));
            JTextField t08 = new JTextField("");
            panel.add(t08);
            vectorValues[0] = t08;

            panel.add(new JLabel("Sensor angle spacing"));
            JTextField t09 = new JTextField("");
            panel.add(t09);
            floatValues[2] = t09;

            panel.add(new JLabel("Turn Speed"));
            JTextField t10 = new JTextField("");
            panel.add(t10);
            floatValues[3] = t10;

            panel.add(new JLabel("Sensor offset distance"));
            JTextField t11 = new JTextField("");
            panel.add(t11);
            floatValues[4] = t11;

            panel.add(new JLabel("Sensor size"));
            JTextField t12 = new JTextField("");
            panel.add(t12);
            intValues[3] = t12;

            JLabel l3 = new JLabel("B cell settings");
            l3.setFont(boldFont);
            panel.add(l3);
            panel.add(new JLabel(""));

            panel.add(new JLabel("Cells probability"));
            JTextField t13 = new JTextField("");
            panel.add(t13);
            floatValues[5] = t13;

            panel.add(new JLabel("Color"));
            JTextField t14 = new JTextField("");
            panel.add(t14);
            vectorValues[1] = t14;

            panel.add(new JLabel("Sensor angle spacing"));
            JTextField t15 = new JTextField("");
            panel.add(t15);
            floatValues[6] = t15;

            panel.add(new JLabel("Turn Speed"));
            JTextField t16 = new JTextField("");
            panel.add(t16);
            floatValues[7] = t16;

            panel.add(new JLabel("Sensor offset distance"));
            JTextField t17 = new JTextField("");
            panel.add(t17);
            floatValues[8] = t17;

            panel.add(new JLabel("Sensor size"));
            JTextField t18 = new JTextField("");
            panel.add(t18);
            intValues[4] = t18;

            FocusListener focusListener = new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    presetsComboBox.getModel().setSelectedItem("");
                }

                @Override
                public void focusLost(FocusEvent e) {}
            };

            // Attach focus listener to text fields
            for (JTextField intValue : intValues) {
                intValue.addFocusListener(focusListener);
            }
            for (JTextField floatValue : floatValues) {
                floatValue.addFocusListener(focusListener);
            }
            for (JTextField vectorValue : vectorValues) {
                vectorValue.addFocusListener(focusListener);
            }

            spawnMode.addFocusListener(focusListener);

            loadParametersFromFile("simulation_parameters.properties");
    }

        /**
         * Creates the "Run simulation" button and adds an action listener.
         */
        private void createRunButton() {
            panel.add(new JLabel(""));

            JButton saveButton = new JButton("Run simulation");
            saveButton.addActionListener(e -> checkValuesDataTypesAndCreateParametersObject());
            panel.add(saveButton);
        }

    private void createPresetsMenu() {
        JLabel l1 = new JLabel("Presets:");
        Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()+2);
        l1.setFont(boldFont);
        panel.add(l1);

        presetsComboBox = new JComboBox<>();
        loadPresets(); // Load available presets
        presetsComboBox.getModel().setSelectedItem("");
        presetsComboBox.addActionListener(e -> loadParametersFromPreset((String) presetsComboBox.getSelectedItem()));
        panel.add(presetsComboBox);

        savePresetButton = new JButton("Save");
        savePresetButton.addActionListener(e -> savePreset());
        panel.add(savePresetButton);

        deletePresetButton = new JButton("Delete");
        deletePresetButton.addActionListener(e -> deletePreset());
        panel.add(deletePresetButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
    }


        /**
         * Checks the data types of the input values and creates a Parameters object.
         */
        private void checkValuesDataTypesAndCreateParametersObject() {
            try {
                windowWidth = Integer.parseInt(intValues[5].getText());
                windowHeight = Integer.parseInt(intValues[6].getText());

                simulationParameters = new Parameters(
                        Integer.parseInt(intValues[0].getText()),
                        Integer.parseInt(intValues[1].getText()),
                        new Vector4f(
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
                        (SpawnMode) spawnMode.getSelectedItem(),
                        Float.parseFloat(floatValues[2].getText()),
                        Float.parseFloat(floatValues[3].getText()),
                        Float.parseFloat(floatValues[4].getText()),
                        Integer.parseInt(intValues[3].getText()),
                        Float.parseFloat(floatValues[6].getText()),
                        Float.parseFloat(floatValues[7].getText()),
                        Float.parseFloat(floatValues[8].getText()),
                        Integer.parseInt(intValues[4].getText())
                );

                runSimulation();

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Show an error message if there are invalid input values
                showMessageDialog(null, "Invalid values");
            }
        }

    /**
     * Runs the simulation with the specified settings and disposes of the frame.
     */
    private void runSimulation() {
        frame.dispose();
        saveParametersToFile("simulation_parameters.properties");

        Window window = new Window("Simulation", windowWidth, windowHeight, simulationParameters);
        showMessageDialog(null, "Press space to run the simulation.");
        window.run();
    }

    /**
     * Saves the current parameters to a file.
     */
    private void saveParametersToFile(String filepath) {
        Properties properties = new Properties();

        for (int i = 0; i < intValues.length; i++) {
            properties.setProperty("intValues_" + i, intValues[i].getText());
        }
        for (int i = 0; i < floatValues.length; i++) {
            properties.setProperty("floatValues_" + i, floatValues[i].getText());
        }
        for (int i = 0; i < vectorValues.length; i++) {
            properties.setProperty("vectorValues_" + i, vectorValues[i].getText());
        }
        properties.setProperty("spawnMode", spawnMode.getSelectedItem().toString());

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            properties.store(outputStream, "Simulation parameters");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the parameters from a file, if it exists.
     */
    private void loadParametersFromFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            return;
        }

        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filepath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < intValues.length; i++) {
            String value = properties.getProperty("intValues_" + i);
            if (value != null) {
                intValues[i].setText(value);
            }
        }
        for (int i = 0; i < floatValues.length; i++) {
            String value = properties.getProperty("floatValues_" + i);
            if (value != null) {
                floatValues[i].setText(value);
            }
        }
        for (int i = 0; i < vectorValues.length; i++) {
            String value = properties.getProperty("vectorValues_" + i);
            if (value != null) {
                vectorValues[i].setText(value);
            }
        }
        String selectedSpawnMode = properties.getProperty("spawnMode");
        if (selectedSpawnMode != null) {
            spawnMode.getModel().setSelectedItem(SpawnMode.valueOf(selectedSpawnMode));
        }
    }

    /**
     * Loads the presets from the "presets" folder.
     * If the folder doesn't exist, it creates one.
     * Adds the preset names to the presetsComboBox.
     */
    private void loadPresets() {
        File presetsFolder = new File("presets");
        if (!presetsFolder.exists()) {
            presetsFolder.mkdir();
        }

        String[] presetFiles = presetsFolder.list((dir, name) -> name.endsWith(".properties"));
        if (presetFiles != null) {
            for (String presetFile : presetFiles) {
                presetsComboBox.addItem(presetFile.substring(0, presetFile.length() - ".properties".length()));
            }
        }
    }

    /**
     * Loads the parameters from a preset file and applies them.
     * @param presetName The name of the preset to load.
     */
    private void loadParametersFromPreset(String presetName) {
        if (presetName == null) {
            return;
        }

        loadParametersFromFile("presets/" + presetName + ".properties");
    }

    /**
     * Saves the current parameters as a new preset.
     * Prompts the user to enter a preset name using JOptionPane.
     * If a valid name is provided, saves the parameters to the corresponding preset file.
     * If the preset is new, adds it to the presetsComboBox.
     * Sets the selected item in the presetsComboBox to the newly saved preset.
     */
    private void savePreset() {
        String presetName = JOptionPane.showInputDialog("Enter preset name:");
        if (presetName != null && !presetName.isBlank()) {
            saveParametersToFile("presets/" + presetName + ".properties");

            if (!presetExists(presetName)) {
                presetsComboBox.addItem(presetName);
            }

            presetsComboBox.getModel().setSelectedItem(presetName);
        }
    }

    /**
     * Deletes the selected preset.
     * Removes the preset file if it exists.
     * Removes the preset from the presetsComboBox.
     */
    private void deletePreset() {
        String presetName = (String) presetsComboBox.getSelectedItem();
        if (presetName != null) {
            File presetFile = new File("presets/" + presetName + ".properties");
            if (presetFile.exists()) {
                presetFile.delete();
                presetsComboBox.removeItem(presetName);
            }
        }
    }

    /**
     * Checks if a preset with the given name exists in the presetsComboBox.
     * @param presetName The name of the preset to check.
     * @return true if the preset exists, false otherwise.
     */
    private boolean presetExists(String presetName) {
        for (int i = 0; i < presetsComboBox.getItemCount(); i++) {
            if (presetsComboBox.getItemAt(i).equals(presetName)) {
                return true;
            }
        }
        return false;
    }
}