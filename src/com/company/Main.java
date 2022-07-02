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
                JComboBox comboBox = new JComboBox();
                comboBox.addItem("Красный");
                comboBox.addItem("Синий");
                comboBox.addItem("Желтый");
                comboBox.addItem("Зеленый");
                comboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (comboBox.getSelectedIndex()){
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
                panel.add(comboBox);
                panel.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 500);
                frame.setVisible(true);
            }
        });
    }
}