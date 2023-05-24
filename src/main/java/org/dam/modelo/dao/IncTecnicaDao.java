package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.IncPartidoVo;
import org.dam.modelo.vo.IncTecnicaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncTecnicaDao {
    public static void registrarIncTecnica(IncTecnicaVo miIncTecnica) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into inc_tecnicas (descripcion,id_usuario) values (?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miIncTecnica.getDescripcion());
        query.setInt(2, miIncTecnica.getIdUsuario());

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static IncTecnicaVo verIncTecnica(int idIncTecnica) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from inc_tecnicas where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idIncTecnica);

        ResultSet rs=query.executeQuery();
        IncTecnicaVo inc = new IncTecnicaVo();
        while(rs.next()){
            inc.setId(rs.getInt("id_incidencia"));
            inc.setDescripcion(rs.getString("descripcion"));
            inc.setIdUsuario(rs.getInt("id_usuario"));
        }

        autoRollback.commit();
        conexion.disconnect();

        return inc;
    }
    public static void actualizarIncTecnica(IncTecnicaVo miIncTecnica, int id) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "update inc_tecnicas set descripcion =? where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miIncTecnica.getDescripcion());
        query.setInt(2, id);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static void borrarIncTecnica(int idIncTecnica) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "delete from inc_tecnicas where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idIncTecnica);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
}
