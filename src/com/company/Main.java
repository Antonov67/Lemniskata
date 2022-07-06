package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

    public static final int RADIUS = 142; // радиус базовой окружности
    public static final Color LEMNISKATA_COLOR = Color.RED;
    public static Color clockColor = Color.BLUE; // цвет часов по умолчанию

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Движение часов по лемнискате");
                JPanel panel = new JPanel();
                final MovingClock movingClock = new MovingClock(LEMNISKATA_COLOR, clockColor, 20, RADIUS);
                panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
                panel.add(movingClock);
                frame.getContentPane().add(panel);

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

                panel.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 700);
                frame.setVisible(true);
            }
        });
    }
}