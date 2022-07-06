package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static final int RADIUS = 142; // радиус базовой окружности
    public static final Color LEMNISKATA_COLOR = Color.RED;
    public static Color clockColor = Color.BLUE; // цвет часов по умолчанию

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Движение часов по лемнискате");
                JPanel panel = new JPanel();
                JPanel mainPanel = new JPanel();
                final MovingClock movingClock = new MovingClock(LEMNISKATA_COLOR, clockColor, 20, RADIUS);
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
                mainPanel.add(movingClock);
                panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                frame.getContentPane().add(mainPanel);

                // цвет часов
                JLabel labelClock = new JLabel("Цвет часов");
                JComboBox comboBoxClock = new JComboBox();
                comboBoxClock.addItem("Красный");
                comboBoxClock.addItem("Синий");
                comboBoxClock.addItem("Желтый");
                comboBoxClock.addItem("Зеленый");
                comboBoxClock.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxClock.getSelectedIndex()){
                            case 0: movingClock.setColorClock(Color.RED);
                                break;
                            case 1: movingClock.setColorClock(Color.BLUE);
                                break;
                            case 2: movingClock.setColorClock(Color.YELLOW);
                                break;
                            case 3: movingClock.setColorClock(Color.GREEN);
                                break;

                        }
                    }
                });

                // цвет лемнискаты
                JLabel labelLem = new JLabel("Цвет лемнискаты");
                JComboBox comboBoxLem = new JComboBox();

                comboBoxLem.addItem("Красный");
                comboBoxLem.addItem("Синий");
                comboBoxLem.addItem("Желтый");
                comboBoxLem.addItem("Зеленый");
                comboBoxLem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxLem.getSelectedIndex()){
                            case 0: movingClock.setColorLem(Color.RED);
                                break;
                            case 1: movingClock.setColorLem(Color.BLUE);
                                break;
                            case 2: movingClock.setColorLem(Color.YELLOW);
                                break;
                            case 3: movingClock.setColorLem(Color.GREEN);
                                break;

                        }
                    }
                });

                //толщина линий
                JLabel labelLineWidth = new JLabel("Толщина линий");
                JComboBox comboBoxLineWidth = new JComboBox();
                comboBoxLineWidth.addItem("2");
                comboBoxLineWidth.addItem("4");
                comboBoxLineWidth.addItem("6");
                comboBoxLineWidth.addItem("8");

                comboBoxLineWidth.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxLineWidth.getSelectedIndex()){
                            case 0: movingClock.setLineWidth(2);
                                break;
                            case 1: movingClock.setLineWidth(4);
                                break;
                            case 2: movingClock.setLineWidth(6);
                                break;
                            case 3: movingClock.setLineWidth(8);
                                break;

                        }
                    }
                });

                // тип линии
                JLabel labelLineStroke = new JLabel("Тип линий");
                JComboBox comboBoxLineStroke = new JComboBox();
                comboBoxLineStroke.addItem("сплошная");
                comboBoxLineStroke.addItem(".....................");
                comboBoxLineStroke.addItem("- - - - - - - - - - -");
                comboBoxLineStroke.addItem("-  -  -  -  -  -  -  -");

                comboBoxLineStroke.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxLineStroke.getSelectedIndex()){
                            case 0: movingClock.setStroke(0);
                                break;
                            case 1: movingClock.setStroke(1);
                                break;
                            case 2: movingClock.setStroke(5);
                                break;
                            case 3: movingClock.setStroke(9);
                                break;

                        }
                    }
                });

                // скорость движения
                JLabel labelSpeedMoving = new JLabel("Скорость движения часов");
                JComboBox comboBoxSpeedMoving = new JComboBox();
                comboBoxSpeedMoving.addItem("1");
                comboBoxSpeedMoving.addItem("4");
                comboBoxSpeedMoving.addItem("6");
                comboBoxSpeedMoving.addItem("8");

                comboBoxSpeedMoving.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxSpeedMoving.getSelectedIndex()){
                            case 0: movingClock.setSpeedMoving(1);
                                break;
                            case 1: movingClock.setSpeedMoving(4);
                                break;
                            case 2: movingClock.setSpeedMoving(6);
                                break;
                            case 3: movingClock.setSpeedMoving(8);
                                break;

                        }
                    }
                });

                // количество повторов
                JLabel labelTotalRepeat = new JLabel("количество повторов");
                JComboBox comboBoxTotalRepeat = new JComboBox();
                comboBoxTotalRepeat.addItem("бесконечно");
                comboBoxTotalRepeat.addItem("1");
                comboBoxTotalRepeat.addItem("4");
                comboBoxTotalRepeat.addItem("10");

                comboBoxTotalRepeat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxTotalRepeat.getSelectedIndex()){
                            case 0: movingClock.setTotalCountRepeat(-1);
                                break;
                            case 1: movingClock.setTotalCountRepeat(1);
                                break;
                            case 2: movingClock.setTotalCountRepeat(4);
                                break;
                            case 3: movingClock.setTotalCountRepeat(10);
                                break;

                        }
                    }
                });



                // скорость пульсации часов
                JLabel labelPulseSpeed = new JLabel("Скорость пульсации часов");
                JComboBox comboBoxPulseSpeed = new JComboBox();
                comboBoxPulseSpeed.addItem("1");
                comboBoxPulseSpeed.addItem("4");
                comboBoxPulseSpeed.addItem("6");
                comboBoxPulseSpeed.addItem("8");

                comboBoxPulseSpeed.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxPulseSpeed.getSelectedIndex()){
                            case 0: movingClock.setSpeedPulse(8);
                                break;
                            case 1: movingClock.setSpeedPulse(6);
                                break;
                            case 2: movingClock.setSpeedPulse(4);
                                break;
                            case 3: movingClock.setSpeedPulse(1);
                                break;

                        }
                    }
                });

                // направление вращения часов
                JLabel labelDirRotation = new JLabel("Направление вращения часов");
                JComboBox comboBoxDirRotation = new JComboBox();
                comboBoxDirRotation.addItem("По часовой стрелке");
                comboBoxDirRotation.addItem("Против часовой стрелки");


                comboBoxDirRotation.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxDirRotation.getSelectedIndex()){
                            case 0: movingClock.setDirRotation(1);
                                break;
                            case 1: movingClock.setDirRotation(-1);
                                break;
                        }
                    }
                });

                // шаг изменения угла объекта при вращении
                JLabel labelStepAngle = new JLabel("Шаг изменения угла объекта при его вращении");
                JComboBox comboBoxStepAngle = new JComboBox();
                comboBoxStepAngle.addItem("2");
                comboBoxStepAngle.addItem("4");
                comboBoxStepAngle.addItem("6");
                comboBoxStepAngle.addItem("8");

                comboBoxStepAngle.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBoxStepAngle.getSelectedIndex()){
                            case 0: movingClock.setStepAngle(2);
                                break;
                            case 1: movingClock.setStepAngle(4);
                                break;
                            case 2: movingClock.setStepAngle(6);
                                break;
                            case 3: movingClock.setStepAngle(8);
                                break;

                        }
                    }
                });

                // выбор опорной точки
                JPanel panelRadioButton = new JPanel();
                panelRadioButton.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();



                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.weightx = 0.5;


                JRadioButton buttonLT = new JRadioButton("");
                constraints.gridx = 0;
                constraints.gridy = 0 ;

                panelRadioButton.add(buttonLT, constraints);

                JRadioButton buttonRT = new JRadioButton("");
                constraints.gridy = 0;
                constraints.gridx = 2;
                panelRadioButton.add(buttonRT, constraints);

                JRadioButton buttonC = new JRadioButton("");
                constraints.ipady = 25;
                constraints.gridy = 1;
                constraints.gridx = 1;
                panelRadioButton.add(buttonC, constraints);

                JRadioButton buttonLB = new JRadioButton("");
                constraints.gridy =4;
                constraints.gridx = 0;
                panelRadioButton.add(buttonLB, constraints);

                JRadioButton buttonRB = new JRadioButton("");
                constraints.gridy = 4;
                constraints.gridx = 2;
                panelRadioButton.add(buttonRB, constraints);

                ButtonGroup group = new ButtonGroup();
                group.add(buttonLB);
                group.add(buttonRB);
                group.add(buttonLT);
                group.add(buttonC);
                group.add(buttonRT);

                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(buttonC.isSelected()) movingClock.setBaseDot("C");
                        if(buttonLT.isSelected()) movingClock.setBaseDot("LT");
                        if(buttonRT.isSelected()) movingClock.setBaseDot("RT");
                        if(buttonLB.isSelected()) movingClock.setBaseDot("LB");
                        if(buttonRB.isSelected()) movingClock.setBaseDot("RB");
                    }
                };

                buttonC.addActionListener(actionListener);
                buttonLT.addActionListener(actionListener);
                buttonRT.addActionListener(actionListener);
                buttonLB.addActionListener(actionListener);
                buttonRB.addActionListener(actionListener);



                final JButton button = new JButton("Старт");
                button.addActionListener(new ActionListener() {
                    private boolean pulsing = false;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pulsing) {
                            pulsing = false;
                            movingClock.stop();
                            button.setText("Старт");
                        } else {
                            pulsing = true;
                            movingClock.start();
                            button.setText("Стоп");
                        }
                    }
                });
                panel.add(labelClock);
                panel.add(comboBoxClock);
                panel.add(labelLem);
                panel.add(comboBoxLem);
                panel.add(labelLineWidth);
                panel.add(comboBoxLineWidth);
                panel.add(labelLineStroke);
                panel.add(comboBoxLineStroke);
                panel.add(labelSpeedMoving);
                panel.add(comboBoxSpeedMoving);
                panel.add(labelPulseSpeed);
                panel.add(comboBoxPulseSpeed);
                panel.add(labelDirRotation);
                panel.add(comboBoxDirRotation);
                panel.add(labelStepAngle);
                panel.add(comboBoxStepAngle);
                panel.add(labelTotalRepeat);
                panel.add(comboBoxTotalRepeat);
                panel.add(new JLabel("выбор опорной точки вращения"));
                panel.add(panelRadioButton);

                panel.add(button);
                mainPanel.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(850, 700);
                frame.setVisible(true);
            }
        });
    }
}