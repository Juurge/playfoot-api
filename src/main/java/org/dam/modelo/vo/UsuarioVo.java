package org.dam.modelo.vo;

public class UsuarioVo {
    private int id;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String dni;
    private String correo;
    private String posicion;
    private String partidosJugados;
    private int goles;

    private String imagen;
    private int puntos;

    public UsuarioVo(String nombre, String apellidos, int telefono, String dni, String correo) {
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.telefono=telefono;
        this.dni=dni;
        this.correo=correo;
        this.goles=0;
        this.posicion= "";
        this.partidosJugados="";
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

    public String getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(String partidosJugados) {
        this.partidosJugados = this.partidosJugados + partidosJugados;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = this.goles + goles;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
