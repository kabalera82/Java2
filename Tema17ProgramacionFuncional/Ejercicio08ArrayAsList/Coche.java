package Tema17ProgramacionFuncional.Ejercicio08ArrayAsList;

public class Coche {

    private String marca;
    private String modelo;
    private double precio;

    public Coche() {
    }

    public Coche(String marca, String modelo, double precio) {
        this.marca = marca;
        this.precio = precio;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + precio + " €";
    }

}
