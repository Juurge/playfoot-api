package org.dam.modelo.vo;

public class PartidoVo {

    private int id;
    private String fecha;
    private String hora;
    private String resultado="0-0";
    private String ganador;
    private String integrantes="";
    private String integrantes2="";
    private String goleadores="";
    private String tipo;
    private String estado="En curso";
    private int contador=0;
    private String comentarios="";
    private int idInstalacion;
    private int idAdministrador;

    public PartidoVo(String fecha, String hora, String tipo,
                      int idInstalacion, int idAdministrador){
        this.fecha=fecha;
        this.hora=hora;
        this.tipo=tipo;
        this.idInstalacion=idInstalacion;
        this.idAdministrador=idAdministrador;
    }

    public PartidoVo() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public String getIntegrantes2() {
        return integrantes2;
    }

    public void setIntegrantes2(String integrantes) {

        this.integrantes2 = this.integrantes2 + integrantes;
    }

    public String getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(String goleadores) {
        this.goleadores = goleadores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
