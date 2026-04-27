package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrdenAdjudicacion {

    //Atributos
    private int idOrden;
    private String idGanador;
    private int idSubasta;
    private int idOferta;
    private LocalDateTime fechaOrden;

    //Constructores
    public OrdenAdjudicacion() {
    }

    public OrdenAdjudicacion(String idGanador, int idSubasta, int idOferta) {
        this.idGanador = idGanador;
        this.idSubasta = idSubasta;
        this.idOferta = idOferta;
        this.fechaOrden = LocalDateTime.now();
    }

    public OrdenAdjudicacion(int idOrden, String idGanador, int idSubasta, int idOferta, LocalDateTime fechaOrden) {
        this.idOrden = idOrden;
        this.idGanador = idGanador;
        this.idSubasta = idSubasta;
        this.idOferta = idOferta;
        this.fechaOrden = fechaOrden;
    }

    //Getters
    public int getIdOrden() {
        return idOrden;
    }

    public String getIdGanador() {
        return idGanador;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public LocalDateTime getFechaOrden() {
        return fechaOrden;
    }

    //Setters
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setIdGanador(String idGanador) {
        this.idGanador = idGanador;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public void setFechaOrden(LocalDateTime fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    //ToString
    @Override
    public String toString() {
        return String.format("🏆 Orden de Adjudicación [%d] | Ganador ID: %s | Subasta ID: %d | Oferta ID: %d | Fecha: %s", 
                idOrden, idGanador, idSubasta, idOferta, fechaOrden);
    }
}
