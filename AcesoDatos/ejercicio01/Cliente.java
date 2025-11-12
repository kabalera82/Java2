package ejercicio01;

import java.util.Objects;

public class Cliente {

    // excepcion con el id
    private int id;
    private String nombre;
    private String email;
    private double saldo;

    public Cliente(){}

    public Cliente(int id, String nombre, String email, double saldo) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.saldo = saldo;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente[" +
                " id= " + id +
                ", nombre= '" + nombre + '\'' +
                ", email= '" + email + '\'' +
                ", saldo= " + saldo +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Double.compare(saldo, cliente.saldo) == 0 && Objects.equals(nombre, cliente.nombre) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, saldo);
    }
}
