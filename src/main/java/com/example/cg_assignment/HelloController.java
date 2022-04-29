package com.example.cg_assignment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    public ColorPicker point_clr;
    public ColorPicker line_clr;
    @FXML public Circle indicator;
    @FXML public Circle indicator1;
    @FXML public Circle indicator2;
    @FXML public Circle indicator3;
    @FXML public Circle indicator4;
    @FXML private Canvas img;
    @FXML private Label pos_text;
    @FXML private Label top_lab;
    @FXML private Button clear_bt;
    @FXML private Button curve_bt;
    @FXML private Button plus;
    @FXML private Button minus;
    private GraphicsContext gc;
    private boolean curve;
    ArrayList<Point> points = new ArrayList<>();

    Paint line_color;
    Paint point_color;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        curve = true;
        point_clr.setValue(Color.RED);
        line_clr.setValue(Color.WHEAT);
        Timeline colorTracker = new Timeline(new KeyFrame(Duration.seconds(0.1),actionEvent -> {
            line_color = line_clr.getValue();
            point_color = point_clr.getValue();
            if(curve) {
                indicator.setFill(Paint.valueOf("#00ff00"));
                indicator1.setFill(Paint.valueOf("#00ff00"));
                indicator2.setFill(Paint.valueOf("#00ff00"));
                indicator3.setFill(Paint.valueOf("#00ff00"));
                indicator4.setFill(Paint.valueOf("#00ff00"));

            }
            else {
                indicator.setFill(Color.GRAY);
                indicator1.setFill(Color.GRAY);
                indicator2.setFill(Color.GRAY);
                indicator3.setFill(Color.GRAY);
                indicator4.setFill(Color.GRAY);
            }

        }));
        colorTracker.setCycleCount(Timeline.INDEFINITE);
        colorTracker.play();

        top_lab.setText("Press the CURVE button and select any 5 points");

        curve_bt.setOnAction(actionEvent -> {
            if (curve) {
                curve = false;
                top_lab.setText("");
            } else {
                curve = true;
                top_lab.setText("Curve on points : ");
            }
        });

        gc = img.getGraphicsContext2D();
        gc.setFill(Paint.valueOf("#888888"));
        gc.fillRect(0, 0, img.getWidth(), img.getHeight());
        gc.setFill(line_color);

        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            int size = points.size();

            for (int i = 0; i < size; i++) {
                gc.setFill(point_color);
                gc.fillOval(X(i), Y(i), 10, 10);
            }

            if (points.size() >= 2) {
                double x;
                double y;
                double u = 0.0;
                double t = 0.0001;
                switch (size) {
                    case 2:
                        double a;
                        double b;
                        gc.setFill(line_color);
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = a * X(0) + b * X(1);
                            y = a * Y(0) + b * Y(1);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(0), Y(0), 10, 10);
                        gc.fillOval(X(1), Y(1), 10, 10);
                        gc.setFill(line_color);
                        break;
                    case 3:
                        gc.setFill(Paint.valueOf("#888888"));
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = a * X(0) + b * X(1);
                            y = a * Y(0) + b * Y(1);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(1), Y(1), 10, 10);
                        gc.setFill(line_color);
                        u = 0.0;

                        gc.setFill(line_color);
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a, 2) * X(0) + 2 * a * b * X(1) + Math.pow(b, 2) * X(2);
                            y = Math.pow(a, 2) * Y(0) + 2 * a * b * Y(1) + Math.pow(b, 2) * Y(2);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(0), Y(0), 10, 10);
                        gc.fillOval(X(2), Y(2), 10, 10);
                        gc.setFill(line_color);
                        break;
                    case 4:
                        gc.setFill(Paint.valueOf("#888888"));
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a, 2) * X(0) + 2 * a * b * X(1) + Math.pow(b, 2) * X(2);
                            y = Math.pow(a, 2) * Y(0) + 2 * a * b * Y(1) + Math.pow(b, 2) * Y(2);
                            gc.fillOval(x - 10, y - 10, 30, 30);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(2), Y(2), 10, 10);
                        gc.setFill(line_color);
                        u = 0.0;
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a, 3) * X(0) + 3 * b * Math.pow(a, 2) * X(1) + 3 * Math.pow(b, 2) * a * X(2) + Math.pow(b, 3) * X(3);
                            y = Math.pow(a, 3) * Y(0) + 3 * b * Math.pow(a, 2) * Y(1) + 3 * Math.pow(b, 2) * a * Y(2) + Math.pow(b, 3) * Y(3);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(0), Y(0), 10, 10);
                        gc.fillOval(X(3), Y(3), 10, 10);
                        gc.setFill(line_color);
                        break;
                    case 5:
                        gc.setFill(Paint.valueOf("#888888"));
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a, 3) * X(0) + 3 * b * Math.pow(a, 2) * X(1) + 3 * Math.pow(b, 2) * a * X(2) + Math.pow(b, 3) * X(3);
                            y = Math.pow(a, 3) * Y(0) + 3 * b * Math.pow(a, 2) * Y(1) + 3 * Math.pow(b, 2) * a * Y(2) + Math.pow(b, 3) * Y(3);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(3), Y(3), 10, 10);
                        u = 0.0;
                        gc.setFill(line_color);
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;

                            x = Math.pow(a, 4) * X(0) + 4 * Math.pow(a, 3) * b * X(1) + 6 * Math.pow(a, 2) * Math.pow(b, 2) * X(2) + 4 * a * Math.pow(b, 3) * X(3) + Math.pow(b, 4) * X(4);
                            y = Math.pow(a, 4) * Y(0) + 4 * Math.pow(a, 3) * b * Y(1) + 6 * Math.pow(a, 2) * Math.pow(b, 2) * Y(2) + 4 * a * Math.pow(b, 3) * Y(3) + Math.pow(b, 4) * Y(4);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(0), Y(0), 10, 10);
                        gc.fillOval(X(4), Y(4), 10, 10);
                        gc.setFill(line_color);
                        break;
                    case 6:
                        gc.setFill(Paint.valueOf("#888888"));
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a, 4) * X(0) + 4 * Math.pow(a, 3) * b * X(1) + 6 * Math.pow(a, 2) * Math.pow(b, 2) * X(2) + 4 * a * Math.pow(b, 3) * X(3) + Math.pow(b, 4) * X(4);
                            y = Math.pow(a, 4) * Y(0) + 4 * Math.pow(a, 3) * b * Y(1) + 6 * Math.pow(a, 2) * Math.pow(b, 2) * Y(2) + 4 * a * Math.pow(b, 3) * Y(3) + Math.pow(b, 4) * Y(4);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(3), Y(3), 10, 10);
                        u = 0.0;
                        gc.setFill(line_color);
                        while (u <= 1) {
                            a = 1 - u;
                            b = u;
                            x = Math.pow(a,5)*X(0) + 5*Math.pow(a,4)*b*X(1) + 10*Math.pow(a,3)*Math.pow(b,2)*X(2) + 10*Math.pow(a,2)*Math.pow(b,3)*X(3)+ 5*a*Math.pow(b,4)*X(4) + Math.pow(b,5)*X(5);
                            y = Math.pow(a,5)*Y(0) + 5*Math.pow(a,4)*b*Y(1) + 10*Math.pow(a,3)*Math.pow(b,2)*Y(2) + 10*Math.pow(a,2)*Math.pow(b,3)*Y(3)+ 5*a*Math.pow(b,4)*Y(4) + Math.pow(b,5)*Y(5);
                            gc.fillOval(x, y, 10, 10);
                            u = u + t;
                        }
                        gc.setFill(point_color);
                        gc.fillOval(X(0), Y(0), 10, 10);
                        gc.fillOval(X(5), Y(5), 10, 10);
                        gc.setFill(line_color);
                        curve = false;
                        points.clear();
                        top_lab.setText("Press the CURVE button and select any 5 points");
                }
            }
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();



        plus.setOnAction(actionEvent -> {
            double x = img.getWidth();
            double y = img.getHeight();
            img.setWidth(img.getWidth() + 30);
            img.setHeight(img.getHeight() + 30);
            gc.setFill(Paint.valueOf("#888888"));
            gc.fillRect(x, 0, 30, y);
            gc.fillRect(0, y, x, 30);
            gc.fillRect(x, y, 30, 30);
        });

        minus.setOnAction(actionEvent -> {
            img.setWidth(img.getWidth() - 30);
            img.setHeight(img.getHeight() - 30);
        });

        clear_bt.setOnAction(actionEvent -> {
            gc.setFill(Paint.valueOf("#888888"));
            gc.fillRect(0, 0, img.getWidth(), img.getHeight());
            gc.setFill(line_color);
            top_lab.setText("CLEARED");
            points.clear();
        });

        img.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (curve) {
                gc.setFill(point_color);
                gc.fillOval(event.getX(), event.getY(), 10, 10);
                gc.setFill(line_color);
                points.add(new Point(event.getX(), event.getY()));
                top_lab.setText(top_lab.getText() + "(" + Math.round(event.getX()) + "," + Math.round(event.getY()) + ") ");
            } else {
                gc.setFill(line_color);
                gc.fillOval(event.getX(), event.getY(), 10, 10);
            }
        });

        img.addEventHandler(MouseEvent.MOUSE_DRAGGED,event -> {
            gc.setFill(line_color);
            gc.fillOval(event.getX(), event.getY(), 10, 10);
        });

        img.addEventHandler(MouseEvent.MOUSE_MOVED, event -> pos_text.setText("(" + Math.round(event.getX()) + "," + Math.round(event.getY()) + ")"));
    }

    public double X(int i) {
        return points.get(i).x;
    }

    public double Y(int i) {
        return points.get(i).y;
    }
}

class Point {
    double x;
    double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}