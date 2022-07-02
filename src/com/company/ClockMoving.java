package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MovingClock extends JComponent implements ActionListener {

    private double scale;
    private Color colorLem, colorClock;
    private Timer timer;

    private int radius;

    private int a = 70; // отступ слева
    private int b = 720; // ширина лемнискаты

    private int step = 2; // шаг для построения лемнискаты
    private   int c; // фокусное расстояние лемнискаты
    private int x = a; // координата х для положения часов
    public int y = 10; // координата у для положения часов

    public MovingClock(Color colorLem, Color colorClock, int delay, int radius) {
        this.radius = radius;
        c = (int) (radius / Math.sqrt(2));
        scale = 1.2;
        timer = new Timer(delay, this); // таймер для управления скорость анимации
        this.colorLem = colorLem;
        this.colorClock = colorClock;
        setPreferredSize(new Dimension(550, 500));
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // нарисуем белый прямоугольник с черной границей - это область анимации
        g2d.setColor(Color.white);
        int width = 550;
        int height = 450;
        g.fillRect(0, 0, width, height);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.scale(scale, scale); // зададим масштаб области рисования

        // Лемниската
        // для ее рисования используем polygon

        int[] xLemn = new int[(b-a) / step]; // массив координат х точек лемнискаты
        int[] yLemn = new int[(b-a) / step]; // массив координат у точек лемнискаты
        int p = 0; // вспомогательный параметр для "адаптации" координатной плоскости java
        // так как у нас нет отрицательных координат
        int count = 0; // индекс массива
        // цикл заполнения массивов координат лемнискаты
        for (int i = a; i < b; i += step){
            if (i <= ((b-a)/2 + a)) {
                p = i - radius - a;
                xLemn[count] = i;
                yLemn[count] = (int)Math.sqrt(Math.sqrt(c*c*c*c + 4*p*p*c*c) - p*p - c*c) + 200; // формула нижней части лемнискаты
            }
            else {
                p = i - radius - a  - b/2;
                xLemn[count] = i - b/2;
                yLemn[count] = -(int)Math.sqrt(Math.sqrt(c*c*c*c + 4*p*p*c*c) - p*p - c*c) + 200; // формула верхней части лемнискаты
            }
            count++;
        }
        Polygon lemkiskata = new Polygon(xLemn,yLemn,xLemn.length); // создаем лемнискату
        g2d.setColor(colorLem); // задаем ее цвет
        g2d.setStroke(new BasicStroke(2)); // толщина линий
        g2d.draw(lemkiskata); // помещаем ее на холст


        // рисуем часы

        int t; // вспомогательный параметр для адаптации системы координат
        x++; // увеличиваем координату х, создаем движение часов
        int xPol = x; // вспомогательная переменная для отображения часов
        if (x >= a && x <= b/2 - 5){
            t = x - radius - a;
            xPol = x;
            y = (int)Math.sqrt(Math.sqrt(c*c*c*c + 4*t*t*c*c) - t*t - c*c) + 200;
//            g2d.setColor(Color.BLACK);
//            g2d.drawString("низ " + x, 10, 350);
        }
        if (x > b/2 - 5 && x <= (b-a-5)){
            t = x - b/2 - radius;
            xPol = radius + a - t;
            y = -(int)Math.sqrt(Math.sqrt(c*c*c*c + 4*t*t*c*c) - t*t - c*c) + 200;
//            g2d.setColor(Color.BLACK);
//            g2d.drawString("верх " + x, 10, 350);
        }
        if (x > (b-a-5)){
            x = a;
        }
        // массивы координат точек часов
        int xPoly[] = {xPol-10,xPol+10, xPol, xPol-10, xPol+10, xPol};
        int yPoly[] = {y-20, y-20, y, y+20, y+20, y};
        Polygon polygon = new Polygon(xPoly,yPoly,xPoly.length);
        g2d.setColor(colorClock);
        g2d.draw(polygon);
    }
}
