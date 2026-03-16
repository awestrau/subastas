package cr.ac.ucenfotec.bl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

import cr.ac.ucenfotec.bl.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.usuarios.Usuario;
import cr.ac.ucenfotec.bl.usuarios.Vendedor;

public class Subastas {

    //Atributos
    private LocalDateTime fechaVencimiento;
    private Duration TiempoParaVencer;
    private Usuario creador;
    private double calificacionCreador;
    private double precioMinimo;
    private ArrayList<Objetos> objetosSubastados;
    private boolean vigente;
    private Oferta oferta;
    private ArrayList<Oferta> listaOfertas;

    //Constructores
    public Subastas(Coleccionista creador, double precioMinimo, ArrayList<Objetos> objetosSubastados) throws Exception{
        if (objetosSubastados == null || objetosSubastados.isEmpty()){
            throw new Exception("Esta subasta no tiene objetos");
        }
        for (Objetos objeto: objetosSubastados){
            if(!creador.getObjetos().contains(objeto)){
                throw new Exception("El item no pertenece a la colección.");
            }
        }
        this.fechaVencimiento = LocalDateTime.now().plusDays(1);
        this.TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);;
        this.creador = creador;
        this.calificacionCreador = creador.getPuntuacion();
        this.precioMinimo = precioMinimo;
        this.objetosSubastados = objetosSubastados;
        this.vigente = true;
        this.listaOfertas = new ArrayList<>();
    }

    public Subastas(Vendedor creador, double precioMinimo, ArrayList<Objetos> objetosSubastados) throws Exception{
        if (objetosSubastados == null || objetosSubastados.isEmpty()){
            throw new Exception("Esta subasta no tiene objetos");
        }
        this.fechaVencimiento = LocalDateTime.now().plusDays(15);
        TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);;
        this.creador = creador;
        this.calificacionCreador = creador.getPuntuacion();
        this.precioMinimo = precioMinimo;
        this.objetosSubastados = objetosSubastados;
        this.vigente = true;
        this.listaOfertas = new ArrayList<>();
    }

    public Subastas() {
    }


    //Getters
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Duration getTiempoParaVencer() {
        actualizarVigencia();
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

    public boolean isVigente() {
        return vigente;
    }

    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
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

    public void setEstado(boolean estado) {
        this.vigente = estado;
    }

    //toString
    public String toString() {
        return "Subastas{" +
                "fechaVencimiento=" + fechaVencimiento +
                ", TiempoParaVencer=" + TiempoParaVencer +
                ", creador=" + creador.getNombre() +
                ", calificacionCreador=" + calificacionCreador +
                ", precioMinimo=" + precioMinimo +
                ", objetosSubastados=" + objetosSubastados +
                ", estado=" + vigente +
                ", oferta=" + oferta +
                ", listaOfertas=" + listaOfertas +
                '}';
    }

    public void agregarOferta(Coleccionista oferente, Double monto){
        oferta = new Oferta(oferente, monto);
        this.listaOfertas.add(oferta);
    }

    public Oferta obtenerOfertaGanadora(){
        Oferta mejor = null;

        for (Oferta ofertaComparar : listaOfertas) {
            if (mejor == null || ofertaComparar.getPrecioOfertado() > mejor.getPrecioOfertado()) {
                mejor = ofertaComparar;
            }
        }

        return mejor;
    }

    public void actualizarVigencia(){
        if (fechaVencimiento.isAfter(LocalDateTime.now())){
            vigente = false;
            TiempoParaVencer = Duration.ZERO;
        } else {
            TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);
        }
    }

}
