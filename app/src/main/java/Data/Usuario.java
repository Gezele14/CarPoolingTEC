package Data;

import java.util.ArrayList;

public class Usuario {

    private String nombre, correo, contraseña;
    private int cedula, carnet, cant_autos, telefono, cant_puntos, cant_viajes;
    private ArrayList<Auto> listaAutos,listaAutosOriginal;
    private ArrayList<Usuario> listaAmigos, listaSolicitudes;

    public Usuario(){
        this.cant_puntos = 100;
        this.cant_autos = 1;
        this.nombre = "";
        this.correo = "";
        this.telefono = 0;
        listaAutos = new ArrayList<Auto>();
        listaAutosOriginal = new ArrayList<Auto>();
        listaAmigos = new ArrayList<Usuario>();
        listaSolicitudes = new ArrayList<Usuario>();
    }
    public Usuario(String nombre, int telefono){
        this.nombre = nombre;
        this.telefono = telefono;
        listaAmigos = new ArrayList<Usuario>();
        listaSolicitudes = new ArrayList<Usuario>();

    }


    //Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public int getCant_autos() {
        return cant_autos;
    }

    public void setCant_autos(int cant_autos) {
        this.cant_autos = cant_autos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCant_puntos() {
        return cant_puntos;
    }

    public void setCant_puntos(int cant_puntos) {
        this.cant_puntos = cant_puntos;
    }

    public int getCant_viajes() {
        return cant_viajes;
    }

    public void setCant_viajes(int cant_viajes) {
        this.cant_viajes = cant_viajes;
    }

    public ArrayList<Auto> getListaAutos() {
        return listaAutos;
    }

    public void setListaAutos(ArrayList<Auto> listaAutos) {
        this.listaAutos = listaAutos;
    }

    public void addAuto(Auto car){
        this.listaAutos.add(car);
        this.listaAutosOriginal.add(car);
        this.cant_autos = this.listaAutos.size();
    }

    public void addAmigo(Usuario friend){
        this.listaAmigos.add(friend);
    }

    public void addSolicitud(Usuario user){
        this.listaSolicitudes.add(user);
    }

    public void resetListaAutos(){
        this.listaAutos = this.listaAutosOriginal;
    }

    public ArrayList<Auto> getListaAutosOriginal() {
        return listaAutosOriginal;
    }

    public void setListaAutosOriginal(ArrayList<Auto> listaAutosOriginal) {
        this.listaAutosOriginal = listaAutosOriginal;
    }

    public ArrayList<Usuario> getListaAmigos() {
        return this.listaAmigos;
    }

    public void setListaAmigos(ArrayList<Usuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public ArrayList<Usuario> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(ArrayList<Usuario> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }
}
