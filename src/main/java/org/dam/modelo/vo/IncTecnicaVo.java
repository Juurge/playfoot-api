package org.dam.modelo.vo;

public class IncTecnicaVo {

    private int id;
    private String descripcion;
    private int idUsuario;

    public IncTecnicaVo(String descripcion, int idUsuario) {
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }
    public IncTecnicaVo(){

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
