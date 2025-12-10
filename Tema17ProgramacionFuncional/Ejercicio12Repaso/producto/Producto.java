package Tema17ProgramacionFuncional.Ejercicio12Repaso.producto;

public class Producto {

    private static int contador = 0;
    private int idProducto;
    private String name;
    private String tipo;
    private int cantidad;
    private double precio;


    public Producto(String name, String tipo, int cantidad, double precio) {
        this.idProducto = contador++;
        this.name = name;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return
                "Producto: " + name + "\n" +
                        "id: " + idProducto + "\n" +
                        "Categoria: " + tipo + "\n" +
                        "Stock: " + cantidad + "\n" +
                        "Precio: " + precio + "\n";

    }

}
