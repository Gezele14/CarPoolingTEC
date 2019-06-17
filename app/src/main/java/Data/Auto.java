package Data;

public class Auto {

    private String Marca, Modelos, Placa;
    private int Capacidad, IdAuto;

    public Auto (int IdAuto,String marca, String modelo, String placa, int Capacidad){
        this.Marca = marca;
        this.Modelos = modelo;
        this.Placa = placa;
        this.Capacidad = Capacidad;
        this.IdAuto = IdAuto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelos() {
        return Modelos;
    }

    public void setModelos(String modelos) {
        Modelos = modelos;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }

    public int getIdAuto() {
        return IdAuto;
    }

    public void setIdAuto(int idAuto) {
        IdAuto = idAuto;
    }
}
