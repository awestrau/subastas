package cr.ac.ucenfotec.bl;

import java.time.LocalDate;
import java.time.Period;

public class Objetos {
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate fechaCompra;
    private String antiguedad;

    public Objetos(String nombre, String descripcion, String estado, LocalDate fechaCompra, String antiguedad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.antiguedad = antiguedad;
    }

    public Objetos(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = "En subasta";
        this.fechaCompra = LocalDate.now();
        this.antiguedad = "Años: " + Period.between(fechaCompra, LocalDate.now()).getYears() + "Meses: " +
                Period.between(fechaCompra, LocalDate.now()).getMonths() + "Días: " + Period.between(fechaCompra, LocalDate.now()).getDays();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Objeto [nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", fechaCompra="
                + fechaCompra + ", antiguedad=" + antiguedad + "]";
    }
}
