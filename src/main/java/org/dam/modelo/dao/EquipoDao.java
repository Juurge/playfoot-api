package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.EquipoVo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipoDao {

    public static void registrarEquipo(EquipoVo miEquipo) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "insert into equipos (nombre,integrantes,partidos_ganados,partidos_perdidos,id_administrador) values (?,?,?,?,?);";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miEquipo.getNombre());
            query.setString(2, miEquipo.getIntegrantes());
            query.setString(3, miEquipo.getPartidosGanados());
            query.setString(4, miEquipo.getPartidosPerdidos());
            query.setInt(5, miEquipo.getIdAdministrador());


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarEquipo(int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "delete from equipos where id_equipo =?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);


            query.setInt(1, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarEquipo(EquipoVo miEquipo,int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update equipos set nombre = ?,integrantes = ?,id_administrador = ? where id_equipo=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miEquipo.getNombre());
            query.setString(2, miEquipo.getIntegrantes());
            query.setInt(3, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void verEquipo(int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "select * from equipos where id_equipo =?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);


            query.setInt(1, id);


            query.executeQuery();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
