package engine.launcher;

import javax.swing.*;
import java.awt.*;

import engine.window.Window;
import org.joml.Vector4f;

import static javax.swing.JOptionPane.showMessageDialog;

public class Launcher {
    Parameters simulationParameters;
    JFrame frame;
    JPanel panel;
    JComboBox spawnMode;
    int windowWidth;
    int windowHeight;
    JTextField[] floatValues;
    JTextField[] vectorValues;
    JTextField[] intValues;

    public void run() {
        this.floatValues = new JTextField[9];
        this.vectorValues = new JTextField[2];
        this.intValues = new JTextField[7];

        createAndShowGUI();
    }

    public void runWithoutLauncher() {
        simulationParameters = new Parameters(
                2560,
                1440,
                new Vector4f(0.0f, 1.0f, 0.7f, 1.0f),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                0.004f,
                0.65f,
                5_000_000,
                0.0f,
                SpawnMode.RANDOM_CIRCLE,

                80.0f,
                -10.0f,
                20.0f,
                1,

                120.0f,
                -3.0f,
                60.0f,
                1
        );

        Window window = new Window("Simulation", 1920, 1080, simulationParameters);
        window.run();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Simulation settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        createLabelsAndTextFields();
        createRunButton();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void createLabelsAndTextFields() {
        JLabel l1 = new JLabel("Main settings");
        Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()+2);
        l1.setFont(boldFont);
        panel.add(l1);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Window width"));
        JTextField t01 = new JTextField("1920");
        panel.add(t01);
        intValues[5] = t01;

        panel.add(new JLabel("Window height"));
        JTextField t02 = new JTextField("1080");
        panel.add(t02);
        intValues[6] = t02;

        panel.add(new JLabel("Simulation width"));
        JTextField t03 = new JTextField("2560");
        panel.add(t03);
        intValues[0] = t03;

        panel.add(new JLabel("Simulation height"));
        JTextField t04 = new JTextField("1440");
        panel.add(t04);
        intValues[1] = t04;

        panel.add(new JLabel("Evaporate speed"));
        JTextField t05 = new JTextField("0.004");
        panel.add(t05);
        floatValues[0] = t05;

        panel.add(new JLabel("Diffuse speed"));
        JTextField t06 = new JTextField("0.65");
        panel.add(t06);
        floatValues[1] = t06;

        panel.add(new JLabel("Agents count"));
        JTextField t07 = new JTextField("3000000");
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
        JTextField t08 = new JTextField("0.0, 1.0, 0.7, 1.0");
        panel.add(t08);
        vectorValues[0] = t08;

        panel.add(new JLabel("Sensor angle spacing"));
        JTextField t09 = new JTextField("80.0");
        panel.add(t09);
        floatValues[2] = t09;

        panel.add(new JLabel("Turn Speed"));
        JTextField t10 = new JTextField("-10.0");
        panel.add(t10);
        floatValues[3] = t10;

        panel.add(new JLabel("Sensor offset distance"));
        JTextField t11 = new JTextField("20.0");
        panel.add(t11);
        floatValues[4] = t11;

        panel.add(new JLabel("Sensor size"));
        JTextField t12 = new JTextField("1");
        panel.add(t12);
        intValues[3] = t12;

        JLabel l3 = new JLabel("B cell settings");
        l3.setFont(boldFont);
        panel.add(l3);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Cells probability"));
        JTextField t13 = new JTextField("0.0");
        panel.add(t13);
        floatValues[5] = t13;

        panel.add(new JLabel("Color"));
        JTextField t14 = new JTextField("1.0, 0.0, 0.7, 1.0");
        panel.add(t14);
        vectorValues[1] = t14;

        panel.add(new JLabel("Sensor angle spacing"));
        JTextField t15 = new JTextField("80.0");
        panel.add(t15);
        floatValues[6] = t15;

        panel.add(new JLabel("Turn Speed"));
        JTextField t16 = new JTextField("-10.0");
        panel.add(t16);
        floatValues[7] = t16;

        panel.add(new JLabel("Sensor offset distance"));
        JTextField t17 = new JTextField("20.0");
        panel.add(t17);
        floatValues[8] = t17;

        panel.add(new JLabel("Sensor size"));
        JTextField t18 = new JTextField("1");
        panel.add(t18);
        intValues[4] = t18;
    }

    private void createRunButton() {
        panel.add(new JLabel(""));
        JButton saveButton = new JButton("Run simulation");
        saveButton.addActionListener(e -> checkValuesDataTypesAndCreateParametersObject());
        panel.add(saveButton);
    }

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
            showMessageDialog(null, "Invalid values");
        }
    }

    private void runSimulation() {
        frame.dispose();

        Window window = new Window("Simulation", windowWidth, windowHeight, simulationParameters);
        window.run();
    }
}