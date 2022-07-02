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
                panel.add(movingClock);
                frame.getContentPane().add(panel);
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
                panel.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 500);
                frame.setVisible(true);
            }
        });
    }
}