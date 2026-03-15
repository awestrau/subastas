package cr.ac.ucenfotec.bl;

public class Oferta {

    //Atributos
    private String nombreOferente;
    private double puntuacionOferente;
    private double precioOfertado;


    //Constructores
    public Oferta() {
    }

    public Oferta(String nombreOferente, double puntuacionOferente, double precioOfertado) {
        this.nombreOferente = nombreOferente;
        this.puntuacionOferente = puntuacionOferente;
        this.precioOfertado = precioOfertado;
    }

    //Getters
    public String getNombreOferente() {
        return nombreOferente;
    }

    public double getPuntuacionOferente() {
        return puntuacionOferente;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    //Setters
    public void setNombreOferente(String nombreOferente) {
        this.nombreOferente = nombreOferente;
    }

    public void setPuntuacionOferente(double puntuacionOferente) {
        this.puntuacionOferente = puntuacionOferente;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    //ToString
    public String toString() {
        return "Oferta{" +
                "nombreOferente='" + nombreOferente + '\'' +
                ", puntuacionOferente=" + puntuacionOferente +
                ", precioOfertado=" + precioOfertado +
                '}';
    }
}
