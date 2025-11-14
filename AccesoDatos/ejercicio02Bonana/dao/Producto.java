package ejercicio02Bonana.dao;

import java.util.Objects;

public class Producto {

    // excepcion con el id
    private int id;
    private String nombre;
    private double stockKg;
    private double precio;

    public Producto(){}

    public Producto(int id, String nombre, double stockKg, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stockKg = stockKg;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getStockKg() {
        return stockKg;
    }
    public void setStockKg(double stockKg) {
        this.stockKg = stockKg;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ejercicio06json.ejercicio06json.src.main.Cliente[" +
                " id= " + id +
                ", nombre= '" + nombre + '\'' +
                ", stockKg= '" + stockKg + '\'' +
                ", precio= " + precio +
                ']';
    }


}
