package cr.ac.ucenfotec.bl;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrdenAdjudicacion {

    //Atributos
    private String nombreGanador;
    private LocalDateTime fechaOrden;
    private ArrayList<Objetos> objetosAdjudicados;
    private double precioTotal;


    //Constructores
    public OrdenAdjudicacion() {
    }

    public OrdenAdjudicacion(String nombreGanador, ArrayList<Objetos> objetosAdjudicados, double precioTotal) {
        this.nombreGanador = nombreGanador;
        this.fechaOrden = LocalDateTime.now();
        this.objetosAdjudicados = objetosAdjudicados;
        this.precioTotal = precioTotal;
    }

    //Getters
    public String getNombreGanador() {
        return nombreGanador;
    }

    public LocalDateTime getFechaOrden() {
        return fechaOrden;
    }

    public ArrayList<Objetos> getObjetosAdjudicados() {
        return objetosAdjudicados;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    //Setters
    public void setNombreGanador(String nombreGanador) {
        this.nombreGanador = nombreGanador;
    }

    public void setFechaOrden(LocalDateTime fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public void setObjetosAdjudicados(ArrayList<Objetos> objetosAdjudicados) {
        this.objetosAdjudicados = objetosAdjudicados;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    //ToString
    public String toString() {
        return "OrdenAdjudicacion{" +
                "nombreGanador='" + nombreGanador + '\'' +
                ", fechaOrden=" + fechaOrden +
                ", objetosAdjudicados=" + objetosAdjudicados +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
