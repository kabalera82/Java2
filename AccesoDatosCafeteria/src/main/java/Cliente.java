import ejercicio07GestorProcesos.main.java.ejercicio07.DetalleCliente;
import ejercicio07GestorProcesos.main.java.ejercicio07.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Integer id;            // PK
    private String nombre;
    private String email;

    // 1:1
    private DetalleCliente detalle; // puede ser null si aún no hay detalle

    // 1:N
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}
    public Cliente(Integer id, String nombre, String email) {
        this.id = id; this.nombre = nombre; this.email = email;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public DetalleCliente getDetalle() { return detalle; }
    public void setDetalle(DetalleCliente detalle) { this.detalle = detalle; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    @Override public String toString() {
        return "ejercicio06json.ejercicio06json.src.main.Cliente{id=%d, nombre='%s', email='%s'}".formatted(id, nombre, email);
    }
}
