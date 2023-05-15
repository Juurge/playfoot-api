package org.dam.modelo.vo;

public class EquipoVo {

    private int id;
    private String nombre;
    private String integrantes;
    private String partidosGanados;
    private String partidosPerdidos;
    private int idAdministrador;

    public EquipoVo(String nombre, String integrantes, String partidosGanados, String partidosPerdidos, int idAdministrador) {
        this.nombre = nombre;
        this.integrantes = "" + idAdministrador;
        this.partidosGanados = "";
        this.partidosPerdidos = "";
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(String integrantes) {
        this.integrantes = this.integrantes + integrantes;
    }

    public String getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(String partidosGanados) {
        this.partidosGanados = this.partidosGanados + partidosGanados;
    }

    public String getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(String partidosPerdidos) {
        this.partidosPerdidos = this.partidosPerdidos + partidosPerdidos;
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
