package org.dam.modelo.dao;

import org.dam.modelo.conexion.AutoRollback;
import org.dam.modelo.conexion.BDConexion;
import org.dam.modelo.vo.EquipoVo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static void modificarEquipoNombre(EquipoVo miEquipo, int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update equipos set nombre = ? where id_equipo=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miEquipo.getNombre());
            query.setInt(2, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void aniadirIntegranteEquipo(EquipoVo miEquipo, int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update equipos set integrantes = ? where id_equipo=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);


            query.setString(1, EquipoDao.listarIntegrantes(id)+miEquipo.getIntegrantes());
            query.setInt(2, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void borrarIntegranteEquipo(String integrantes, int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update equipos set integrantes = ? where id_equipo=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);


            query.setString(1, integrantes);
            query.setInt(2, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static EquipoVo verEquipo(int id) throws SQLException {
        BDConexion conexion = new BDConexion();



        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select * from equipos where id_equipo =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);


        query.setInt(1, id);

        ResultSet rs = query.executeQuery();
        EquipoVo equipo = new EquipoVo();
        while (rs.next()) {
            equipo.setId(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setIntegrantes(rs.getString("integrantes"));
            equipo.setPartidosGanados(rs.getString("partidos_ganados"));
            equipo.setPartidosPerdidos(rs.getString("partidos_perdidos"));
            equipo.setIdAdministrador(rs.getInt("id_administrador"));
        }

        autoRollback.commit();
        conexion.disconnect();
        if (equipo.getId() == 0){
            return null;
        }
        else{
            return equipo;
        }

    }


    public static String listarIntegrantes(int id) throws SQLException {

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes from equipos where id_equipo =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, id);
        String integrantes="";
        ResultSet rs = query.executeQuery();
        while(rs.next()) {
            integrantes=rs.getString("integrantes");
        }
        integrantes=integrantes+", ";
        return integrantes;
    }


    public static void actualizarResultadoEquipo(EquipoVo miEquipo,int id) {
        BDConexion conexion = new BDConexion();

        try {

            AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

            String instruccion = "update equipos set partidos_ganados= ?,partidos_perdidos = ? where id_equipo=?;";

            PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

            query.setString(1, miEquipo.getPartidosGanados());
            query.setString(2, miEquipo.getPartidosPerdidos());
            query.setInt(3, id);


            query.executeUpdate();

            autoRollback.commit();
            conexion.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static ArrayList<EquipoVo> consultarTodos() throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select * from equipos;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);
        ResultSet rs=query.executeQuery();

        ArrayList<EquipoVo> equipos = new ArrayList<>();
        EquipoVo equipo= new EquipoVo();
        while(rs.next()){
            equipo.setId(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setIntegrantes(rs.getString("integrantes"));
            equipo.setPartidosGanados(rs.getString("partidos_ganados"));
            equipo.setPartidosPerdidos(rs.getString("partidos_perdidos"));
            equipo.setIdAdministrador(rs.getInt("id_administrador"));
            equipos.add(equipo);
        }

        autoRollback.commit();
        conexion.disconnect();

        return equipos;
    }
    public static void cambioRolEquipo(int idAdministrador,int idEquipo) throws SQLException {

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        EquipoVo equipo=verEquipo(idEquipo);

        String[] integrantes = equipo.getIntegrantes().split(",");
        boolean administradorCambiado=false;
        int i=0;

        do{
            if(integrantes[i].equalsIgnoreCase(""+idAdministrador)){
                i++;
            }else{
                String instruccion = "update equipos set id_administrador=? where id_equipo=?;";

                PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

                query.setInt(1,idAdministrador);;
                query.setInt(2, idEquipo);

                query.executeUpdate();
                administradorCambiado=true;
            }
        }while(!administradorCambiado);

        autoRollback.commit();
        conexion.disconnect();

    }

}
