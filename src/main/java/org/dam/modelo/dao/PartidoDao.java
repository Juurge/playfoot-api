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

        String instruccion = "insert into partidos (fecha, hora, resultado, ganador, integrantes,integrantes_2, goleadores, tipo, estado, contador, comentarios, id_instalacion, id_administrador) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, partido.getFecha());
        query.setString(2, partido.getHora());
        query.setString(3, partido.getResultado());
        query.setString(4, partido.getGanador());
        query.setString(5, partido.getIntegrantes());
        query.setString(6, partido.getIntegrantes2());
        query.setString(7, partido.getGoleadores());
        query.setString(8, partido.getTipo());
        query.setString(9, partido.getEstado());
        query.setInt(10, partido.getContador());
        query.setString(11,partido.getComentarios());
        query.setInt(12, partido.getIdInstalacion());
        query.setInt(13, partido.getIdAdministrador());

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
            partido.setIntegrantes(rs.getString("integrantes_2"));
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
            partido.setIntegrantes2(rs.getString("integrantes_2"));
            partido.setGoleadores(rs.getString("goleadores"));
            partido.setTipo(rs.getString("tipo"));
            partido.setEstado(rs.getString("estado"));
            partido.setContador(rs.getInt("contador"));
            partido.setComentarios(rs.getString("comentarios"));
            partido.setIdInstalacion(rs.getInt("id_instalacion"));
            partido.setIdAdministrador(rs.getInt("id_administrador"));
            partidos.add(partido);
            partido=new PartidoVo();
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

        query.setString(1,PartidoDao.listarIntegrantes(idPartido)+ idUsuario);
        query.setInt(2, idPartido);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }

    public static void unirsePartidoPublico2(int idUsuario, int idPartido) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "UPDATE partidos SET integrantes_2 = ? WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1,PartidoDao.listarIntegrantes2(idPartido)+ idUsuario);
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
            partido.setIntegrantes(rs.getString("integrantes_2"));
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

    public static boolean estaFinalizado(int idPartido) throws SQLException{

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "SELECT estado FROM partidos WHERE id_partido =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();
        String estado="";
        while(rs.next()){
            estado=rs.getString("estado");
        }

        if (estado.equals("En curso")){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean esAdminPartido(int id,int idPartido) throws SQLException{

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "SELECT id_administrador FROM partidos WHERE id_partido =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, id);
        query.setInt(2, idPartido);

        ResultSet rs=query.executeQuery();
        int id2=0;
        while(rs.next()){
            id2=rs.getInt("id_administrador");
        }

        if (id2==id){
            return true;
        }
        else{
            return true;
        }
    }

    public static String listarIntegrantes(int id) throws SQLException {

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes from partidos where id_partido =?;";

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


    public static String listarIntegrantes2(int id) throws SQLException {

        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes_2 from partidos where id_partido =?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, id);
        String integrantes="";
        ResultSet rs = query.executeQuery();
        while(rs.next()) {
            integrantes=rs.getString("integrantes_2");
        }
        if (integrantes.equals("")){
            return integrantes;
        } else {
            integrantes=integrantes+", ";
            return integrantes;
        }
    }
    public static boolean consultarIntegrantesPartido(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes, integrantes_2 from partidos where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();

        PartidoVo partido= new PartidoVo();
        int integrantes1=0;
        int integrantes2=0;


        while(rs.next()){
            integrantes1=rs.getString("integrantes").length();
            integrantes2=rs.getString("integrantes_2").length();

        }

        autoRollback.commit();
        conexion.disconnect();
        if(integrantes1==integrantes2) {
            return true;
        } else {
            return false;
        }
    }
    public static void aumentarJugadores(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());
        int contador=(consultarNumJugadores(idPartido)+1);
        String instruccion = "UPDATE partidos SET contador = ? WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, contador);
        query.setInt(2, idPartido);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static int consultarNumJugadores(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select contador from partidos where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs = query.executeQuery();
        int contador=0;
        while(rs.next()) {
            contador=rs.getInt("contador");
        }
        return contador;
    }
    public static boolean comprobarExisteJugadorPartido(int idPartido,int idUsuario) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes, integrantes_2 from partidos where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();

        PartidoVo partido= new PartidoVo();

        String integrantes1Total="";
        String integrantes2Total="";

        while(rs.next()){
            integrantes1Total=rs.getString("integrantes");
            integrantes2Total=rs.getString("integrantes_2");
        }

        String [] split1=integrantes1Total.split(",");
        boolean idIgual=false;
        for (int i = 0; i < split1.length; i++) {
           if ((" "+idUsuario).equals(split1[i])){
               idIgual=true;
           }
        }

        String [] split2=integrantes2Total.split(",");
        for (int i = 0; i < split2.length; i++) {
            if ((" "+idUsuario).equals(split2[i])){
                idIgual=true;
            }
        }

        autoRollback.commit();
        conexion.disconnect();
        if(!idIgual) {
            return false;
        } else {
            return true;
        }
    }

    public static int estadoPistaIndividual(String fecha,String hora,int idInstalacion)throws SQLException{
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "SELECT contador FROM partidos WHERE fecha =? and hora=? and id_instalacion=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, fecha);
        query.setString(2,hora);
        query.setInt(3,idInstalacion);

        ResultSet rs = query.executeQuery();
        int cont=0;
        while(rs.next()){
            cont=rs.getInt("contador");
        }
        autoRollback.commit();
        conexion.disconnect();
        int cod=1;

        if(cont>=1 && cont <14){
            cod=2;
            return cod;
        }
        else if(cont==14){
            cod=3;
            return cod;
        }
        return cod;
    }

    public static int estadoPistaEquipos(String fecha,String hora,int idInstalacion)throws SQLException{
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "SELECT contador FROM partidos WHERE fecha =? and hora= and id_intalacion=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, fecha);
        query.setString(2,hora);
        query.setInt(3,idInstalacion);

        ResultSet rs = query.executeQuery();
        int cont=0;
        while(rs.next()){
            cont=rs.getInt("contador");
        }
        autoRollback.commit();
        conexion.disconnect();
        int cod=1;
        if(cont==1){
            cod=2;
            return cod;
        }
        else if(cont==2){
            cod=3;
            return cod;
        }
        return cod;
    }

    public static boolean comprobarExisteEquipoPartido(int idPartido,int idEquipo) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String instruccion = "select integrantes, integrantes_2 from partidos where id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, idPartido);

        ResultSet rs=query.executeQuery();

        String integrantes1Total="";
        String integrantes2Total="";

        while(rs.next()){
            integrantes1Total=rs.getString("integrantes");
            integrantes2Total=rs.getString("integrantes_2");
        }
        if(EquipoDao.dameIntegrantesEquipo(idEquipo).equals(integrantes1Total) || EquipoDao.dameIntegrantesEquipo(idEquipo).equals(integrantes2Total)){
            return true;
        }
        else {
            return false;
        }
    }

    public static void unirsePartidoEquipos(int idEquipo, int idPartido) throws SQLException {

        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());

        String integrantes=EquipoDao.dameNombresEquipo(idEquipo);

        String instruccion = "UPDATE partidos SET integrantes_2 = ? WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1,integrantes);
        query.setInt(2, idPartido);
        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();

    }
    public static void aumentarNumeroEquipo(int idPartido) throws SQLException {
        BDConexion conexion= new BDConexion();

        AutoRollback autoRollback=new AutoRollback(conexion.getConnection());
        int contador=2;
        String instruccion = "UPDATE partidos SET contador = ? WHERE id_partido=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setInt(1, contador);
        query.setInt(2, idPartido);

        query.executeUpdate();

        autoRollback.commit();
        conexion.disconnect();
    }
    public static int devolverIdPartido(String fecha,String hora,int idInstalacion)throws SQLException{
        BDConexion conexion = new BDConexion();

        AutoRollback autoRollback = new AutoRollback(conexion.getConnection());

        String instruccion = "SELECT id_partido FROM partidos WHERE fecha =? and hora=? and id_instalacion=?;";

        PreparedStatement query = conexion.getConnection().prepareStatement(instruccion);

        query.setString(1, fecha);
        query.setString(2,hora);
        query.setInt(3,idInstalacion);

        ResultSet rs = query.executeQuery();
        int id=0;
        while(rs.next()){
            id=rs.getInt("id_partido");
        }
        autoRollback.commit();
        conexion.disconnect();
        return id;
    }
}
