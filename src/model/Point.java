package model;


public class Point {
    private long id;
    private double x;
    private double y;
    private double r = 1;
    private int result = 0;
    public int isResult() {
        result = 0;
       if(x >= 0 && y <= 0 && x <= r/2 && y <= r) {
           result = 1;
           return result;
       }
        if(x <= 0 && y <= 0 && (x*x + y*y) <= (r*r)/4) {
            result = 1;
            return result;
        }
        if(x >= 0 && y >= 0 &&  -0.5 * x + r/2 >= y) {
            result = 1;
            return result;
        }
        return result;
    }

    public double getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getResult() {
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
