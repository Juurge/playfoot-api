package org.dam.modelo.vo;

public class IncPartidoVo {

    private int id;
    private String descripcion;
    private int idPartido;
    public IncPartidoVo(String descripcion, int idPartido) {
        this.descripcion = descripcion;
        this.idPartido = idPartido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
