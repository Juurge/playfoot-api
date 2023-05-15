package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.IncTecnicaVo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncTecnicaDao {

    public static void registrarIncTecnica(IncTecnicaVo miIncTecnica) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "insert into inc_tecnicas (descripcion,id_usuario) values (?,?);";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miIncTecnica.getDescripcion());
            query.setInt(2, miIncTecnica.getIdUsuario());

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarIncTecnica(IncTecnicaVo miIncTecnica) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "delete from inc_tecnicas where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setInt(1, miIncTecnica.getId());

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verIncTecnica(IncTecnicaVo miIncTecnica) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "select * from inc_tecnicas where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setInt(1, miIncTecnica.getId());

            query.executeQuery();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void actualizarIncTecnica(IncTecnicaVo miIncTecnica) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update inc_tecnicas set descripcion values =? where id_incidencia=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miIncTecnica.getDescripcion());
            query.setInt(2, miIncTecnica.getId());

            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
