package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.IncPartidoVo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncPartidoDao {

    public static void registrarIncPartido(IncPartidoVo miIncPartido) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "insert into inc_partidos (descripcion,id_partido) values (?,?);";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miIncPartido.getDescripcion());
            query.setInt(2, miIncPartido.getIdPartido());

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrarIncPartido(int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "delete from inc_partidos where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setInt(1, id);

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verIncPartido(IncPartidoVo miIncPartido) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "select * from inc_partidos where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setInt(1, miIncPartido.getId());

            query.executeQuery();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void actualizarIncPartido(IncPartidoVo miIncPartido,int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update inc_partidos set descripcion values =? where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miIncPartido.getDescripcion());
            query.setInt(2, id);

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
