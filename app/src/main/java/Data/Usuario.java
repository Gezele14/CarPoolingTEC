package Data;

import java.util.ArrayList;

public class Usuario {

    private String nombre, correo, contraseña;
    private int id,idamigo,cedula, carnet, cant_autos, telefono, cant_puntos, cant_viajes;
    private ArrayList<Auto> listaAutos,listaAutosOriginal;
    private ArrayList<Usuario> listaAmigos, listaBusqueda;
    private ArrayList<solicitud> listaSolicitudes;


    public Usuario(){
        this.cant_puntos = 100;
        this.cant_autos = 0;
        this.nombre = "";
        this.correo = "";
        this.telefono = 0;
        listaAutos = new ArrayList<Auto>();
        listaAutosOriginal = new ArrayList<Auto>();
        listaAmigos = new ArrayList<Usuario>();
        listaBusqueda = new ArrayList<>();
        listaSolicitudes = new ArrayList<solicitud>();
    }
    public Usuario(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        listaAmigos = new ArrayList<Usuario>();
        listaBusqueda = new ArrayList<>();
        listaSolicitudes = new ArrayList<solicitud>();
    }


    public Usuario(String nombre, String correo, int id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        listaAutos = new ArrayList<Auto>();
        listaAutosOriginal = new ArrayList<Auto>();
        listaAmigos = new ArrayList<Usuario>();
        listaBusqueda = new ArrayList<>();
        listaSolicitudes = new ArrayList<solicitud>();
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
        this.cant_autos = listaAutos.size();
    }

    public void addAuto(Auto car){
        this.listaAutos.add(car);
        this.listaAutosOriginal.add(car);
        this.cant_autos = this.listaAutos.size();
    }

    public void addAmigo(Usuario friend){
        this.listaAmigos.add(friend);
    }

    public void addSolicitud(solicitud user){
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
        this.cant_autos = listaAutosOriginal.size();
    }

    public ArrayList<Usuario> getListaAmigos() {
        return this.listaAmigos;
    }

    public void setListaAmigos(ArrayList<Usuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public ArrayList<solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(ArrayList<solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdamigo() {
        return idamigo;
    }

    public void setIdamigo(int idamigo) {
        this.idamigo = idamigo;
    }

    public ArrayList<Usuario> getListaBusqueda() {
        return listaBusqueda;
    }

    public void setListaBusqueda(ArrayList<Usuario> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }
}
