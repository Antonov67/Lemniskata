package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

class MovingClock extends JComponent implements ActionListener {

    private double scale;
    private Color colorLem, colorClock; // цвета траектории и часов

    private int lineWidth = 2; // толщина линии

    private int scaleLine = 0;

    private int moveDir = 1; // направление движения
    private boolean startMove = false;

    private int stroke = 0; // тип линии

    private String baseDot = "C"; // базовая точка вращения

    private int dirRotation = 1;

    private int countRepeat = 0; // количество повторов прохождения траектории

    private int totalCountRepeat = -1; // необходимое количество повторов

    private int speedPulse = 2; // скорость пульсации

    private int speedMoving = 1; // скорость движения

    private int stepAngle = 2; // шаг изменения угла объекта при вращении

    public void setTotalCountRepeat(int totalCountRepeat) {
        countRepeat = 0;
        this.totalCountRepeat = totalCountRepeat;
    }

    public void setMoveDir(int moveDir) {
        this.moveDir = moveDir;
        this.startMove = true;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setBaseDot(String baseDot) {
        this.baseDot = baseDot;
    }

    public void setStepAngle(int stepAngle) {
        this.stepAngle = stepAngle;
    }

    public void setDirRotation(int dirRotation) {
        this.dirRotation = dirRotation;
    }

    public void setSpeedPulse(int speedPulse) {
        this.speedPulse = speedPulse;
    }

    public void setSpeedMoving(int speedMoving) {
        this.speedMoving = speedMoving;
    }

    public void setColorLem(Color colorLem) {
        this.colorLem = colorLem;
    }

    public void setColorClock(Color colorClock) {
        this.colorClock = colorClock;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public void setScaleLine(int scaleLine) {
        this.scaleLine = scaleLine;
    }

    private Timer timer;

    private int radius;
    private int angle = 0; // для поворота часов

    private int a = 70; // отступ слева
    private int b = 720; // ширина лемнискаты

    private int step = 2; // шаг для построения лемнискаты
    private   int c; // фокусное расстояние лемнискаты
    private int x = a; // координата х для положения часов
    public int y = 10; // координата у для положения часов

    public MovingClock(Color colorLem, Color colorClock, int delay, int radius) {
        this.radius = radius;
        c = (int) (radius / Math.sqrt(2));
        scale = 1.1;
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

        //толщина и тип линии
        Stroke dashed;
        switch (stroke){
            case 1:
                dashed = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0);
                break;
            case 5:
                dashed = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
                break;
            case 9:
                dashed = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                break;
            default:
                dashed = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0);
        }

        g2d.setStroke(dashed);
        // вертикальная ось
        Line2D line2D = new Line2D.Double(212, 300, 212, 100);
        g2d.draw(line2D);
        //подписи осей
        g2d.drawString("y", 220, 110);
        g2d.drawString("x", 390, 215);
        g2d.draw(lemkiskata); // помещаем ее на холст


        // рисуем часы

        int t; // вспомогательный параметр для адаптации системы координат

        if (moveDir == 1){
            x+=speedMoving; // увеличиваем координату х, создаем движение часов вправо
        }
        else if (moveDir == -1) {
            x-=speedMoving; // увеличиваем координату х, создаем движение часов влево
        }

        if (moveDir == 1 && startMove){
            x = 70;
            startMove = false;
        }else if (moveDir == -1 && startMove){
            x = 644;
            startMove = false;
        }




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
        if (x > (b-a-5) && moveDir == 1){
           x = a;
           countRepeat++; // подсчитываем количество повторов
            if (countRepeat == totalCountRepeat){
                timer.stop(); // остановим вращение когда достигнуто нужное количество повторов
            }
         }
        if (x < a && moveDir == -1){
            x = 644;
            countRepeat++; // подсчитываем количество повторов
            if (countRepeat == totalCountRepeat){
                timer.stop(); // остановим вращение когда достигнуто нужное количество повторов
            }
        }

        // массивы координат точек часов
//        int xPoly[] = {xPol-10,xPol+10, xPol, xPol-10, xPol+10, xPol};
//        int yPoly[] = {y-20, y-20, y, y+20, y+20, y};

        // изменение размера и угла поворота часов

        if (dirRotation == 1){
            angle += stepAngle;
        }else {
            angle -= stepAngle;
        }

        int xDot=0, yDot=0;

        if (scaleLine%20 <= 9){
            int xPoly[] = {xPol-scaleLine%20-20,xPol+20+scaleLine%20, xPol, xPol-scaleLine%20-20, xPol+20+scaleLine%20, xPol};
            int yPoly[] = {y-scaleLine%20-20, y-scaleLine%20-20, y, y+scaleLine%20+20, y+scaleLine%20+20, y};
            Polygon polygon = new Polygon(xPoly,yPoly,xPoly.length);
            g2d.setColor(colorClock);
            switch (baseDot){
                case "C":
                    xDot = xPol;
                    yDot = y;
                    break;
                case "LT":
                    xDot = xPol-scaleLine%20-20;
                    yDot = y-scaleLine%20-20;
                    break;
                case "RT":
                    xDot = xPol+20+scaleLine%20;
                    yDot = y-scaleLine%20-20;
                    break;
                case "RB":
                    xDot = xPol+20+scaleLine%20;
                    yDot = y+scaleLine%20+20;
                    break;
                case "LB":
                    xDot = xPol-scaleLine%20-20;
                    yDot = y+scaleLine%20+20;
                    break;

            }
            g2d.rotate(Math.toRadians(angle),xDot,yDot); // повернем часы

            g2d.draw(polygon);
        }else {
            int xPoly[] = {xPol-38+scaleLine%20,xPol+38-scaleLine%20, xPol, xPol-38+scaleLine%20, xPol+38-scaleLine%20, xPol};
            int yPoly[] = {y-39+scaleLine%20, y-39+scaleLine%20, y, y+39-scaleLine%20, y+39-scaleLine%20, y};
            Polygon polygon = new Polygon(xPoly,yPoly,xPoly.length);

            switch (baseDot){
                case "C":
                    xDot = xPol;
                    yDot = y;
                    break;
                case "LT":
                    xDot = xPol-38+scaleLine%20;
                    yDot = y-39+scaleLine%20;
                    break;
                case "RT":
                    xDot = xPol+38-scaleLine%20;
                    yDot = y-scaleLine%20-20;
                    break;
                case "RB":
                    xDot = xPol+38-scaleLine%20;
                    yDot = y+39-scaleLine%20;
                    break;
                case "LB":
                    xDot = xPol-38+scaleLine%20;
                    yDot = y+39-scaleLine%20;
                    break;

            }

            g2d.setColor(colorClock);
            g2d.rotate(Math.toRadians(angle),xDot,yDot);  // повернем часы

            g2d.draw(polygon);
        }

        // меняем переменную для изменения размера часов не слишком быстро, а только раз в 10 пикселей
        if (x % 10 > speedPulse){
            scaleLine++;
        }

    }
}
