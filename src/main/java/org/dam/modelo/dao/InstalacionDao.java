package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstalacionDao {

    public static void crearInstalacion(String ubicacion, int telefono, String correo,
                                 double tarifas, String horarios, String imagen) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());
        String instruccion = "insert into instalaciones (ubicacion, telefono, correo," +
                " tarifas, horarios, imagen) values (?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, ubicacion);
        query.setInt(2, telefono);
        query.setString(3, correo);
        query.setDouble(4, tarifas);
        query.setString(5, horarios);
        query.setString(6, imagen);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static void consultarInstalacion(int idInstalacion) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from instalaciones where id_instalacion=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idInstalacion);

        ResultSet rs=query.executeQuery();
        while(rs.next()){
                //duda, que hago ahora? porque nosotros no mostramos nada por pantalla
                //
        }

        autoRollback.commit();
        conexion.disconnect();
        }

    public static void borrarInstalacion(int idInstalacion) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "DELETE FROM instalaciones WHERE id_instalacion =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idInstalacion);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
}
