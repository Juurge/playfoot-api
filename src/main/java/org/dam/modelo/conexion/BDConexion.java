package org.dam.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {

    static String bd = "playfoot";
    static String login = "root";
    static String password = "root";
    static String url = "jdbc:mysql://localhost/" + bd;

    Connection conn = null;

    public BDConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void disconnect() {
        conn = null;
    }

    public void commit(){
        try{
            conn.commit();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
