package org.dam.modelo.vo;

public class EquipoVo {

    private int id;
    private String nombre;
    private String integrantes="";
    private String partidosGanados="0";
    private String partidosPerdidos="0";
    private int idAdministrador;

    public EquipoVo(String nombre,int idAdministrador) {

        this.nombre = nombre;
        this.idAdministrador = idAdministrador;
        setIntegrantes(""+idAdministrador);

    }

    public EquipoVo() {

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
