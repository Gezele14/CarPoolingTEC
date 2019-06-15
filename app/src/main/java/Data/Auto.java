package Data;

public class Auto {

    private String Marca, Modelo, Placa;
    private int cant_pers;

    public Auto (String marca, String modelo, String placa, int cant_pers){
        this.Marca = marca;
        this.Modelo = modelo;
        this.Placa = placa;
        this.cant_pers = cant_pers;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public int getCant_pers() {
        return cant_pers;
    }

    public void setCant_pers(int cant_pers) {
        this.cant_pers = cant_pers;
    }
}
