package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.IncPartidoVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncPartidoDao {
    public static void registrarIncPartido(IncPartidoVo miIncPartido) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into inc_partidos (descripcion,id_partido) values (?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miIncPartido.getDescripcion());
        query.setInt(2, miIncPartido.getIdPartido());

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static IncPartidoVo consultarIncPartido(int idIncPartido) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select * from inc_partidos where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idIncPartido);

        ResultSet rs = query.executeQuery();
        IncPartidoVo inc = new IncPartidoVo();
        while (rs.next()) {
            inc.setId(rs.getInt("id_incidencia"));
            inc.setDescripcion(rs.getString("descripcion"));
            inc.setIdPartido(rs.getInt("id_partido"));
        }

        autoRollback.commit();
        conexion.disconnect();

        return inc;
    }
    public static void actualizarIncPartido(IncPartidoVo miIncPartido,int id) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "update inc_partidos set descripcion =? where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, miIncPartido.getDescripcion());
        query.setInt(2, id);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static void borrarIncPartido(int id) throws SQLException {
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "delete from inc_partidos where id_incidencia=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, id);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
}
