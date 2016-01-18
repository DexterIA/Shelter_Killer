package sample.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Employer;
import sample.Room;

import java.sql.*;

public class Conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public Conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:emp.dbd");
        statmt = conn.createStatement();
    }

    public String checkUser(String login, String pass) throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM emp");
        String name, password;
        String ret = "";
        while (resSet.next()) {
            name = resSet.getString("login");
            password = resSet.getString("pass");
            if ((name.equals(login)) && (password.equals(pass))) {
                ret = name;
            }
        }
        conn.close();
        resSet.close();
        return ret;
    }

    public ObservableList<Room> getFreeRooms() throws SQLException {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        resSet = statmt.executeQuery("SELECT * FROM kvnof");
        Room room;
        while (resSet.next()) {
            room = new Room();
            room.setNum(resSet.getString("num"));
            room.setType(resSet.getString("type"));
            room.setPrice(resSet.getString("price"));
            rooms.add(room);
        }
        conn.close();
        return rooms;
    }

    public ObservableList<Employer> getEmployers() throws SQLException {
        ObservableList<Employer> employers = FXCollections.observableArrayList();
        resSet = statmt.executeQuery("SELECT * FROM emp");
        Employer employer;
        while (resSet.next()) {
            employer = new Employer();
            employer.setTabNo(resSet.getString("tableNo"));
            employer.setLogin(resSet.getString("login"));
            employer.setPass(resSet.getString("pass"));
            employers.add(employer);
        }
        conn.close();
        resSet.close();
        return employers;
    }

    public void deleteUser(String login) throws SQLException {
        statmt.executeUpdate("DELETE FROM emp WHERE login = \"" + login + "\";");
        conn.close();
        resSet.close();
    }

    public void addUser(String login, String pass) throws SQLException {
        PreparedStatement prepInfo = conn.prepareStatement("insert into emp (login, pass)values (?, ?)");
        prepInfo.setString(1, login);
        prepInfo.setString(2, pass);
        prepInfo.execute();
        conn.close();
        resSet.close();
    }

    public Boolean findUser(String login) throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM emp");
        Boolean b = false;
        while (resSet.next()) {
            if (resSet.getString("login").equals(login)) {
                b = true;
            }
        }
        return b;
    }
}
