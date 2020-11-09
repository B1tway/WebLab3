package controller;

import database.DataBaseManager;
import model.Point;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pointListBean", eager = true)
@ApplicationScoped
public class PointListBean implements Serializable {
    private Point point;
    private static List<Point> points;

    private DataBaseManager db;
    public PointListBean() {
    }


    @PostConstruct
    public void init()  {
        db = new DataBaseManager();
//        System.out.println("I m init");
//        System.out.println(points.size());
        point = new Point();
        points = getPoints();
    }

    private boolean validate(double x, double y, double r) {
        System.out.println(x + " " + y + " " + r);
        if (x > -3 && x < 5 && y > -5 && y < 5 && r >= 1 && r <= 5) return true;
        return false;
    }

    public void addPoint() throws SQLException {
        System.out.println("add point: " + " " + point.getX() + " " + point.getY() + " " + point.getR());
        if (validate(point.getX(), point.getY(), point.getR())) {
            point.isResult();
//            points.add(point);
            db.addPoint(point.getX(), point.getY(), point.getR(), point.getResult());
            System.out.println(points.size());
            double r = point.getR();
            point = new Point();
            point.setR(r);
            points = db.getCollectionFromDataBase();
        }

    }
    public void deleteAllPoints() {
        db.removeAllPoints();
    }

    public void addPointWithoutValidation() throws SQLException {
        System.out.println("add point: " + " " + point.getX() + " " + point.getY() + " " + point.getR());

        point.isResult();
//        points.add(point);
        db.addPoint(point.getX(), point.getY(), point.getR(), point.getResult());
        System.out.println(points.size());
        double r = point.getR();
        point = new Point();
        point.setR(r);
        points = db.getCollectionFromDataBase();

    }

    public List<Point> getPoints() {
        points = new ArrayList<>();
        try {
            points = db.getCollectionFromDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

}
