package cr.ac.ucenfotec.bl.entities;

import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;

public class Oferta {

    //Atributos
    private Coleccionista oferente;
    private double puntuacionOferente;
    private double precioOfertado;


    //Constructores
    public Oferta() {
    }

    public Oferta(Coleccionista oferente, double precioOfertado) {
        this.precioOfertado = precioOfertado;
        this.puntuacionOferente = oferente.getPuntuacion();
        this.oferente = oferente;
    }

    //Getters


    public Coleccionista getOferente() {
        return oferente;
    }

    public double getPuntuacionOferente() {
        return puntuacionOferente;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    //Setters


    public void setOferente(Coleccionista oferente) {
        this.oferente = oferente;
    }

    public void setPuntuacionOferente(double puntuacionOferente) {
        this.puntuacionOferente = puntuacionOferente;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    //ToString
    @Override
    public String toString() {
        return String.format("💵 Oferta de %s: $%.2f (Puntuación Oferente: %.1f)", 
                oferente.getNombre(), precioOfertado, puntuacionOferente);
    }
}
