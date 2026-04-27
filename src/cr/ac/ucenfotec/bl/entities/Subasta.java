package cr.ac.ucenfotec.bl.entities;

import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class Subasta {

    // Atributos
    private int id;
    private LocalDateTime fechaVencimiento;
    private Duration TiempoParaVencer;
    private Usuario creador;
    private double calificacionCreador;
    private double precioMinimo;
    private ArrayList<Objeto> objetosSubastados;
    private boolean vigente;
    private Oferta oferta;
    private ArrayList<Oferta> listaOfertas;

    // Constructores
    public Subasta(Coleccionista creador, double precioMinimo, ArrayList<Objeto> objetosSubastados) throws Exception {
        if (objetosSubastados == null || objetosSubastados.isEmpty()) {
            throw new Exception("Esta subasta no tiene objetos");
        }
        for (Objeto objeto : objetosSubastados) {
            boolean pertenece = false;

            for (Objeto objColeccion : creador.getObjetos()) {
                if (objColeccion.getNombre().equals(objeto.getNombre())) {
                    pertenece = true;
                    break;
                }
            }

            if (!pertenece) {
                throw new Exception(
                        "El objeto '" + objeto.getNombre() + "' no pertenece al Coleccionista " + creador.getNombre());
            }
        }
        this.fechaVencimiento = LocalDateTime.now().plusDays(1);
        this.TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);
        this.creador = creador;
        this.calificacionCreador = creador.getPuntuacion();
        this.precioMinimo = precioMinimo;
        this.objetosSubastados = objetosSubastados;
        this.vigente = true;
        this.listaOfertas = new ArrayList<>();
    }

    public Subasta(Vendedor creador, double precioMinimo, ArrayList<Objeto> objetosSubastados) throws Exception {
        if (objetosSubastados == null || objetosSubastados.isEmpty()) {
            throw new Exception("Esta subasta no tiene objetos");
        }
        this.fechaVencimiento = LocalDateTime.now().plusDays(15);
        TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);

        this.creador = creador;
        this.calificacionCreador = creador.getPuntuacion();
        this.precioMinimo = precioMinimo;
        this.objetosSubastados = objetosSubastados;
        this.vigente = true;
        this.listaOfertas = new ArrayList<>();
    }

    public Subasta() {
    }

    // Getters
    public int getId() {
        return id;
    }

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

    public ArrayList<Objeto> getObjetosSubastados() {
        return objetosSubastados;
    }

    public boolean isVigente() {
        return vigente;
    }

    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

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

    public void setObjetosSubastados(ArrayList<Objeto> objetoSubastados) {
        this.objetosSubastados = objetoSubastados;
    }

    public void setEstado(boolean estado) {
        this.vigente = estado;
    }

    // toString
    @Override
    public String toString() {
        String estado = vigente ? "🟢 VIGENTE" : "🔴 CERRADA";
        return String.format("🔨 Subasta [%d] [%s] | Creador: %s | Precio Base: $%.2f\n   Vence: %s | En lista: %d objetos, %d ofertas", 
                id, estado, creador.getNombre(), precioMinimo, fechaVencimiento, objetosSubastados.size(), listaOfertas.size());
    }

    public void agregarOferta(Coleccionista oferente, Double monto) {
        oferta = new Oferta(oferente, monto);
        this.listaOfertas.add(oferta);
    }

    public Oferta obtenerOfertaGanadora() {
        Oferta mejor = null;

        for (Oferta ofertaComparar : listaOfertas) {
            if (mejor == null || ofertaComparar.getPrecioOfertado() > mejor.getPrecioOfertado()) {
                mejor = ofertaComparar;
            }
        }

        return mejor;
    }

    public void actualizarVigencia() {
        if (fechaVencimiento.isBefore(LocalDateTime.now())) {
            vigente = false;
            TiempoParaVencer = Duration.ZERO;
        } else {
            vigente = true;
            TiempoParaVencer = Duration.between(LocalDateTime.now(), fechaVencimiento);
        }
    }

}
