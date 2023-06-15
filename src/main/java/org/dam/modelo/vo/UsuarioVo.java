package org.dam.modelo.vo;

import com.fasterxml.jackson.core.JsonToken;

public class UsuarioVo {
    private int id;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String dni;
    private String correo;
    private String password;
    private String posicion="";
    private int partidosJugados=0;
    private int goles=0;

    private int idEquipoAdministracion=0;

    public UsuarioVo() {
    }

    public UsuarioVo(String nombre, String apellidos, int telefono, String dni, String correo, String password) {
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.telefono=telefono;
        this.dni=dni;
        this.correo=correo;
        this.password=password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = this.partidosJugados + partidosJugados;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = this.goles + goles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdEquipoAdministracion() {
        return idEquipoAdministracion;
    }

    public void setIdEquipoAdministracion(int idEquipoAdministracion) {
        this.idEquipoAdministracion = idEquipoAdministracion;
    }
}
