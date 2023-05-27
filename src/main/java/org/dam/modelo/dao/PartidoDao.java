package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.PartidoVo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartidoDao {

    public static void crearPartido(PartidoVo partido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "insert into partidos (fecha, hora, resultado, ganador, integrantes, goleadores, tipo, estado, contador, comentarios, id_instalacion, id_administrador) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, partido.getFecha());
        query.setString(2, partido.getHora());
        query.setString(3, partido.getResultado());
        query.setString(4, partido.getGanador());
        query.setString(5, partido.getIntegrantes());
        query.setString(6, partido.getGoleadores());
        query.setString(7, partido.getTipo());
        query.setString(8, partido.getEstado());
        query.setInt(9, partido.getContador());
        query.setString(10,partido.getComentarios());
        query.setInt(11, partido.getIdInstalacion());
        query.setInt(12, partido.getIdAdministrador());

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static PartidoVo consultarPartido(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from partidos where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();

        PartidoVo partido= new PartidoVo();
        while(rs.next()){
            partido.setId(rs.getInt("id_partido"));
            partido.setFecha(rs.getString("fecha"));
            partido.setHora(rs.getString("hora"));
            partido.setResultado(rs.getString("resultado"));
            partido.setGanador(rs.getString("ganador"));
            partido.setIntegrantes(rs.getString("integrantes"));
            partido.setGoleadores(rs.getString("goleadores"));
            partido.setTipo(rs.getString("tipo"));
            partido.setEstado(rs.getString("estado"));
            partido.setContador(rs.getInt("contador"));
            partido.setComentarios(rs.getString("comentarios"));
            partido.setIdInstalacion(rs.getInt("id_instalacion"));
            partido.setIdAdministrador(rs.getInt("id_administrador"));
        }

        autoRollback.commit();
        conexion.disconnect();
        if(partido.getId()==0) {
            return null;
        } else {
            return partido;
        }
    }

    public static void actualizarPartido(PartidoVo partido,int id) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE partidos SET resultado = ?, ganador = ?, goleadores =?, comentarios =?" +
                " WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, partido.getResultado());
        query.setString(2, partido.getGanador());
        query.setString(3, partido.getGoleadores());
        query.setString(4, partido.getComentarios());
        query.setInt(5, id);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }

    public static ArrayList<PartidoVo> consultarTodos() throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from partidos;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);
        ResultSet rs=query.executeQuery();

        ArrayList<PartidoVo> partidos = new ArrayList<>();
        PartidoVo partido= new PartidoVo();
        while(rs.next()){
            partido.setId(rs.getInt("id_partido"));
            partido.setFecha(rs.getString("fecha"));
            partido.setHora(rs.getString("hora"));
            partido.setResultado(rs.getString("resultado"));
            partido.setGanador(rs.getString("ganador"));
            partido.setIntegrantes(rs.getString("integrantes"));
            partido.setGoleadores(rs.getString("goleadores"));
            partido.setTipo(rs.getString("tipo"));
            partido.setEstado(rs.getString("estado"));
            partido.setContador(rs.getInt("contador"));
            partido.setComentarios(rs.getString("comentarios"));
            partido.setIdInstalacion(rs.getInt("id_instalacion"));
            partido.setIdAdministrador(rs.getInt("id_administrador"));
            partidos.add(partido);
        }

        autoRollback.commit();
        conexion.disconnect();

        return partidos;
    }

    public static void unirsePartidoPublico(int idUsuario, int idPartido) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE partidos SET integrantes = ? WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idUsuario);
        query.setInt(2, idPartido);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }

    public static PartidoVo consultarPartidoPorIdAdministrador(int idAdministrador) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from partidos where id_administrador=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idAdministrador);

        ResultSet rs=query.executeQuery();

        PartidoVo partido= new PartidoVo();
        while(rs.next()){
            partido.setId(rs.getInt("id_partido"));
            partido.setFecha(rs.getString("fecha"));
            partido.setHora(rs.getString("hora"));
            partido.setResultado(rs.getString("resultado"));
            partido.setGanador(rs.getString("ganador"));
            partido.setIntegrantes(rs.getString("integrantes"));
            partido.setGoleadores(rs.getString("goleadores"));
            partido.setTipo(rs.getString("tipo"));
            partido.setEstado(rs.getString("estado"));
            partido.setContador(rs.getInt("contador"));
            partido.setComentarios(rs.getString("comentarios"));
            partido.setIdInstalacion(rs.getInt("id_instalacion"));
            partido.setIdAdministrador(rs.getInt("id_administrador"));
        }

        autoRollback.commit();
        conexion.disconnect();
        if(partido.getId()==0) {
            return null;
        } else {
            return partido;
        }
    }

    public static void eliminarPartido(int idPartido) throws SQLException {

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "DELETE FROM partidos WHERE id_partido =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }

}
