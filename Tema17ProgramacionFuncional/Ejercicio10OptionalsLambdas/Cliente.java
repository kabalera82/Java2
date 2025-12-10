package Tema17ProgramacionFuncional.Ejercicio10OptionalsLambdas;

public class Cliente {

    private static int contador = 0;

    private int idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;

    public Cliente(String number, String maria, String s) {
        this.idCliente = ++contador;
    }

    public Cliente(String nombre, String apellido, String direccion, int telefono) {
        this.idCliente = ++contador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}




