package org.dam.modelo.vo;

public class InstalacionVo {
    private int id;
    private String ubicacion;
    private int telefono;
    private String correo;
    private String tarifas;
    private String horarios;

    public InstalacionVo(String ubicacion, int telefono, String correo, String tarifas, String horarios) {
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
        this.tarifas = tarifas;
        this.horarios = horarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTarifas() {
        return tarifas;
    }

    public void setTarifas(String tarifas) {
        this.tarifas = tarifas;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
}
