package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    private int step;
    private Rectangle[][] rect;
    Random los = new Random();
    @FXML
    public Button button = new Button();
    @FXML
    public Button eulerPlus = new Button();
    @FXML
    public Button animacja = new Button();
    @FXML
    public Group grupa = new Group();
    @FXML
    public AnchorPane pane = new AnchorPane();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        los = new Random();
        grupa = new Group();
        pane.getChildren().add(grupa);
    }
    @FXML
    public void rysuj() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        namalujKolka(grupa, 300, 190, 90);
    }
    @FXML
    public void rysujLinie() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        int startX = 50;
        int startY = 80;
        int koniecX = 400;
        int koniecY = 300;
        for (int krok = 3; krok < 40; krok += 2) {
            Line linia = new Line(startX, startY, koniecX, koniecY);
            startX += krok;
            startY -= krok/2;
            koniecX -= krok/2;
            koniecY += krok;
            grupa.getChildren().addAll(linia);
        }
    }
    private void namalujKolka(Group grupa, int x, int y, int r) {
        Circle kolko = new Circle(x , y, r, Color.rgb(0,80,17,0.3));
        kolko.setStroke(Color.BLACK);
        grupa.getChildren().add(kolko);

        if (r > 12) {
            namalujKolka(grupa, x + r, y, r / 2);
            namalujKolka(grupa, x - r, y, r / 2);
            namalujKolka(grupa, x - (int)(2.0*r), y + r, r / 2);
            namalujKolka(grupa, x + (int)(2.0*r), y - r, r / 2);
        }
    }
    private void namalujKwadrat(Group grupa, int x, int y, int r) {
        Rectangle rect = new Rectangle(x,y, r, r );
        rect.setFill(Color.OLIVE);
        rect.setStroke(Color.ROSYBROWN);
        grupa.getChildren().add(rect);

        if (r > 12) {
            namalujKwadrat(grupa, x + r, y, r / 2);
            namalujKwadrat(grupa, x - r, y, r / 2);
            namalujKwadrat(grupa, x - (int)(2.0*r), y + r, r / 2);
            namalujKwadrat(grupa, x + (int)(2.0*r), y - r, r / 2);
        }
    }
    @FXML
    public void rysujKwadraty() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        namalujKwadrat(grupa, 300, 190, 90);
    }
    private void drawTree(int x1, int y1, double angle, int depth) {
        if (depth == 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);
        Line line = new Line(x1, y1, x2, y2);
        grupa.getChildren().add(line);
        drawTree(x2, y2, angle - 20, depth - 1);
        drawTree(x2, y2, angle + 20, depth - 1);
    }
    private void drawSickTree(int x1, int y1, double angle, int depth) {
        if (depth == 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);
        Line line = new Line(x1, y1, x2, y2);
        grupa.getChildren().add(line);
        // chore drzewo ;)
        if (los.nextDouble() > 0.2 - ((double)(depth)/50))
            drawTree(x2, y2, angle - 20, depth - 1);
        if (los.nextDouble() > 0.2 -((double)(depth)/50))
            drawTree(x2, y2, angle + 20, depth - 1);
        if (los.nextDouble() > (0.9 - ((double)depth)/40))
            drawTree(x2, y2, angle - 45, depth - 1);
        if (los.nextDouble() > (0.9 - ((double)depth)/40))
            drawTree(x2, y2, angle + 45, depth - 1);
    }
    @FXML
    public void rysujDrzewo() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        drawTree(300, 550, -60, 10);
    }
    @FXML
    public void rysujChoreDrzewo() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        drawSickTree(300, 550, -60, 10);
    }
    @FXML
    public void animuj() {
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        rect = new Rectangle[40][40];
        for(int i = 0; i < 40; i++)
            for(int j = 0; j < 40; j++) {
                Rectangle r = new Rectangle();
                r.setWidth(5);
                r.setHeight(5);
                r.setX(i * 8);
                r.setY(j * 8 + 20);
                r.setFill(Color.rgb(200, 100, 0));
                grupa.getChildren().add(r);
                rect[i][j] = r;
        }
        Random los = new Random();
        System.out.println(step);
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 40; j++) {
                int r = los.nextInt(255);
                int g = los.nextInt(255);
                int b = los.nextInt(255);
                rect[i][j].setFill(Color.rgb(r, g, b));
            }
    }
    @FXML
    public void button1Action() {
        try {
            for(int i = 0; i < 10; i++) {
                animacja.fire();
                Thread.sleep(200);
            }
        }
        catch(InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }
    @FXML
    public void eulerplus() {
        int SIZE = 3;
        pane.getChildren().remove(grupa);
        grupa = new Group();
        pane.getChildren().add(grupa);
        double g = 6.6743 * Math.pow(10, -3); // albo do -3
        double dt = 0.2;
        double[] vx = new double[SIZE];
        double[] vy = new double[SIZE];
        double[] vx0 = new double[SIZE];
        double[] vy0 = new double[SIZE];
        double[] vx1 = new double[SIZE];
        double[] vy1 = new double[SIZE];
        double[] vx2 = new double[SIZE];
        double[] vy2 = new double[SIZE];
        double[] rx = new double[SIZE];
        double[] ry = new double[SIZE];
        double[] rx0 = new double[SIZE];
        double[] ry0 = new double[SIZE];
        double[] ax = new double[SIZE];
        double[] ay = new double[SIZE];
        double[] ma = new double[SIZE];
        ma[0] =   100;
        ma[1] =   800;
        ma[2] = 10000;
        vx[0] = 60;
        vx[1] = 20;
        vx[2] = 70;
        rx[0] = 0;
        ry[0] = 10;
        rx[1] = 90;
        ry[1] = 30;
        rx[2] = 6;
        ry[2] = 20;
        int N = 40;
        double[][] tx = new double[SIZE][N];
        double[][] ty = new double[SIZE][N];
        for (int i = 0; i < SIZE; i++) {
            tx[i][0] = rx[i];
            ty[i][0] = ry[i];
        }
        double[][] px = new double[SIZE][N];
        double[][] py = new double[SIZE][N];
        double maxX = -99999;
        double minX = 99999;
        double minY = 99999;
        double maxY = -99999;
        for (int t = 1; t < N; t++) {
            for (int i = 0; i < SIZE; i++) {
                rx0[i] = rx[i];
                ry0[i] = ry[i];
                vx0[i] = vx[i];
                vy0[i] = vy[i];
                ax[i] = 0;
                ay[i] = 0;
                for (int j = 0; j < SIZE; j++)
                    if (i != j) {
                        double r = Math.sqrt((rx[i] - rx[j]) * (rx[i] - rx[j]) + (ry[i] - ry[j]) * (ry[i] - ry[j]));
                        ax[i] = ax[i] - g * ma[j] * (rx[i] - rx[j]) / (r * r * r);
                        ay[i] = ay[i] - g * ma[j] * (ry[i] - ry[j]) / (r * r * r);
                    }
                vx1[i] = ax[i] * dt;
                vy1[i] = ay[i] * dt;
                rx[i] = rx0[i] + vx1[i] * dt;
                ry[i] = ry0[i] + vy1[i] * dt;
                ax[i] = 0;
                ay[i] = 0;
                for (int j = 0; j < SIZE; j++)
                    if (i != j) {
                        double r = Math.sqrt((rx[i] - rx[j]) * (rx[i] - rx[j]) + (ry[i] - ry[j]) * (ry[i] - ry[j]));
                        ax[i] = ax[i] - g * ma[j] * (rx[i] - rx[j]) / (r * r * r);
                        ay[i] = ay[i] - g * ma[j] * (ry[i] - ry[j]) / (r * r * r);
                    }
                vx2[i] = ax[i] * dt;
                vy2[i] = ay[i] * dt;
                // właściwe pozycje
                vx[i] = vx0[i] + (vx1[i] + vx2[i]) / 2;
                vy[i] = vy0[i] + (vy1[i] + vy2[i]) / 2;
                rx[i] = rx0[i] + vx[i] * dt;
                ry[i] = ry0[i] + vy[i] * dt;
            }
            for (int i = 0; i < SIZE; i++) {
                tx[i][t] = rx[i];
                ty[i][t] = ry[i];
                if (rx[i] > maxX)
                    maxX = rx[i];
                if (rx[i] < minX)
                    minX = rx[i];
                if (ry[i] > maxY)
                    maxY = ry[i];
                if (ry[i] < minY)
                    minY = ry[i];
            }
        }
        for (int t = 0; t < N; t++)
            for(int i = 0; i < SIZE;i++) {
                double x = (tx[i][t] - minX)/(maxX - minX)*500;
                double y = (ty[i][t] - minY)/(maxY - minY)*500;
                System.out.println(x + " " + y);
                Color c = Color.BLACK;
                if (i == 1)
                    c = Color.RED;
                if (i == 2)
                    c = Color.GREEN;
                Circle cir = new Circle();
                cir.setFill(c);
                cir.setRadius(3);
                cir.setCenterX(x);
                cir.setCenterY(y);
                grupa.getChildren().add(cir);
            }
    }
    @FXML
    public void help() {
        System.out.println("pomoc");
    }
}

