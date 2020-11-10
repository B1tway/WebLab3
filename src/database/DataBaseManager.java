package database;
import model.Point;


import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orbis";
    private static String USER;
    private static String PASS;
    private static final String FILE_WITH_ACCOUNT = "src\\main\\java\\database\\account";
    private static final String TABLE_NAME = "POINTS";

    static {
        USER = "****";
        PASS = "****";

        System.out.println("Connection to Oracle JDBC");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver") ;
            System.out.println("JDBC Driver successfully connected");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver is not found. Include it in your library path");
            e.printStackTrace();
        }
    }

    private Connection connection;

    public DataBaseManager() {
        this(DB_URL, USER, PASS);
    }

    public DataBaseManager(String dbUrl, String user, String pass) {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            System.out.println("Connection to database failed" + dbUrl +" " + user+ " " + pass);
            e.printStackTrace();
        }
    }

    public ArrayList<Point> getCollectionFromDataBase() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from " + TABLE_NAME);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Point> collection = new ArrayList<>();
        while (resultSet.next()) {
            Point point = new Point();
            point.setX(resultSet.getDouble(1));
            point.setY(resultSet.getDouble(2));
            point.setR(resultSet.getDouble(3));
            point.setResult(resultSet.getInt(4));
            collection.add(point);
        }
        return collection;
    }

    public boolean addPoint(double x, double y, double r, int result) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into " + TABLE_NAME +
                    " values (?, ?, ?, ?)");
            statement.setDouble(1, x);
            statement.setDouble(2, y);
            statement.setDouble(3, r);
            statement.setInt(4, result);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeAllPoints() {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from " + TABLE_NAME);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}