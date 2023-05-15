package org.dam.modelo.vo;







import org.dam.modelo.vo.enums.Estado;
import org.dam.modelo.vo.enums.Tipo;

import java.util.Date;

public class PartidoVo {

    private int id;
    private Date fecha;
    private String hora;
    private String resultado;
    private String ganador;
    private String integrantes;
    private String goleadores;
    private Tipo tipo;
    private Estado estado;
    private int contador;
    private int idInstalacion;
    private int idAdministrador;

    public PartidoVo(Date fecha, String hora, String integrantes, String goleadores, Tipo tipo,
                     Estado estado, int idInstalacion, int idAdministrador){
        this.fecha=fecha;
        this.hora=hora;
        this.resultado="0-0";
        this.integrantes="" + idAdministrador;
        this.goleadores="";
        this.tipo=tipo;
        this.estado=estado;
        this.contador=0;
        this.idInstalacion=idInstalacion;
        this.idAdministrador=idAdministrador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(String integrantes) {
        this.integrantes = this.integrantes + integrantes;
    }

    public String getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(String goleadores) {
        this.goleadores = goleadores;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = this.contador + 1;
    }

    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
