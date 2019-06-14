package Data;

public class Usuario {

    private String nombre, correo, contraseña;
    private int cedula, carnet, cant_autos, telefono, cant_puntos, cant_viajes;

    public Usuario(){
        this.cant_puntos = 100;
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
}
