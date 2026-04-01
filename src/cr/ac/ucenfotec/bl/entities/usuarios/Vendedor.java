package cr.ac.ucenfotec.bl.entities.usuarios;

import java.time.LocalDate;

public class Vendedor extends Usuario {
    private int puntuacion;
    private String direccion;

    public Vendedor() {
    }

    public Vendedor(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacionVendedor, String direccion) {
        super(nombre, id, password, fechaNacimiento, correo);
        this.puntuacion = puntuacionVendedor;
        this.direccion = direccion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacionVendedor) {
        this.puntuacion = puntuacionVendedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Vendedor [puntuacion=" + puntuacion + ", direccion=" + direccion + ", getNombre()=" + getNombre()
                + ", getId()=" + getId() + ", getFechaNacimiento()=" + getFechaNacimiento() + ", getCorreo()="
                + getCorreo() + "]";
    }
    
    
}
