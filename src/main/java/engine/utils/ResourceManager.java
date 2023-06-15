package engine.utils;

import engine.launcher.SpawnMode;

import javax.swing.*;
import java.io.*;
import java.util.*;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceManager {

    /**
     * Loads the content of a resource file as a string.
     *
     * @param filePath The path of the resource file to load.
     * @return The content of the resource file as a string.
     * @throws Exception If the resource file cannot be loaded.
     */
    public static String loadResource(String filePath) throws Exception {
        // Get the input stream for the specified resource file
        InputStream in = Class.forName(ResourceManager.class.getName()).getResourceAsStream(filePath);

        // Create a scanner to read the input stream as text using UTF-8 encoding
        Scanner scanner = new Scanner(in, "UTF-8");

        // Read the entire content of the resource file as a single string
        String content = scanner.useDelimiter("\\A").next();

        return content;
    }

    /**
     * Saves the Launcher parameters to a file.
     */
    public static void saveLauncherParametersToFile(String filepath, JTextField[] intValues, JTextField[] floatValues, JTextField[] vectorValues, JComboBox spawnmode) {
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
        properties.setProperty("spawnMode", spawnmode.getModel().getSelectedItem().toString());

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            properties.store(outputStream, "Simulation parameters");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JTextField[] loadIntParametersFromFile(Properties properties, JTextField[] intValues) {

        for (int i = 0; i < intValues.length; i++) {
            String value = properties.getProperty("intValues_" + i);
            if (value != null) {
                intValues[i].setText(value);
            }
        }

        return intValues;
    }

    public static JTextField[] loadFloatParametersFromFile(Properties properties, JTextField[] floatValues) {

        for (int i = 0; i < floatValues.length; i++) {
            String value = properties.getProperty("floatValues_" + i);
            if (value != null) {
                floatValues[i].setText(value);
            }
        }

        return floatValues;
    }

    public static JTextField[] loadVectorParametersFromFile(Properties properties, JTextField[] vectorValues) {

        for (int i = 0; i < vectorValues.length; i++) {
            String value = properties.getProperty("vectorValues_" + i);
            if (value != null) {
                vectorValues[i].setText(value);
            }
        }

        return vectorValues;
    }

    public static JComboBox loadSpawnModeParameterFromFile(Properties properties, JComboBox spawnmode) {

        String selectedSpawnMode = properties.getProperty("spawnMode");
        if (selectedSpawnMode != null) {
            spawnmode.getModel().setSelectedItem(SpawnMode.valueOf(selectedSpawnMode));
        }

        return spawnmode;
    }

    /**
     * Loads the parameters from a file, if it exists.
     */
    public static Properties loadParametersFromFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }

        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filepath)) {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Loads the presets from the "presets" folder.
     * If the folder doesn't exist, it creates one.
     * Adds the preset names to the presetsComboBox.
     */
    public static JComboBox loadPresets() {
        File presetsFolder = new File("presets");
        if (!presetsFolder.exists()) {
            presetsFolder.mkdir();
        }

        JComboBox presetsComboBox = new JComboBox();

        String[] presetFiles = presetsFolder.list((dir, name) -> name.endsWith(".properties"));
        if (presetFiles != null) {
            for (String presetFile : presetFiles) {
                presetsComboBox.addItem(presetFile.substring(0, presetFile.length() - ".properties".length()));
            }
        }

        return presetsComboBox;
    }
}
