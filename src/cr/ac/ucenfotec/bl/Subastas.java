package cr.ac.ucenfotec.bl;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class Subastas {

    //Atributos
    private LocalDateTime fechaVencimiento;
    private Duration TiempoParaVencer;
    private Usuario creador;
    private double calificacionCreador;
    private double precioMinimo;
    private ArrayList<Objetos> objetosSubastados;
    private EstadoSubasta estado;

    //Constructores
    public Subastas(LocalDateTime fechaVencimiento, Duration tiempoParaVencer, Usuario creador, double calificacionCreador,
                    double precioMinimo, ArrayList<Objetos> objetosSubastados, EstadoSubasta estado) {
        this.fechaVencimiento = LocalDateTime.now().plusDays(15);
        TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);;
        this.creador = creador;
        this.calificacionCreador = calificacionCreador;
        this.precioMinimo = precioMinimo;
        this.objetosSubastados = objetosSubastados;
        this.estado = estado;
    }

    public Subastas() {
    }


    //Getters
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Duration getTiempoParaVencer() {
        return TiempoParaVencer;
    }

    public Usuario getCreador() {
        return creador;
    }

    public double getCalificacionCreador() {
        return calificacionCreador;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public ArrayList<Objetos> getObjetosSubastados() {
        return objetosSubastados;
    }

    public EstadoSubasta getEstado() {
        return estado;
    }

    //Setters
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setTiempoParaVencer(Duration tiempoParaVencer) {
        TiempoParaVencer = tiempoParaVencer;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public void setCalificacionCreador(double calificacionCreador) {
        this.calificacionCreador = calificacionCreador;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public void setObjetosSubastados(ArrayList<Objetos> objetosSubastados) {
        this.objetosSubastados = objetosSubastados;
    }

    public void setEstado(EstadoSubasta estado) {
        this.estado = estado;
    }


    public String toString() {
        return "Subastas{" +
                "fechaVencimiento=" + fechaVencimiento +
                ", TiempoParaVencer=" + TiempoParaVencer +
                ", creador=" + creador +
                ", calificacionCreador=" + calificacionCreador +
                ", precioMinimo=" + precioMinimo +
                ", objetosSubastados=" + objetosSubastados +
                ", estado=" + estado +
                '}';
    }

}
