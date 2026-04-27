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
        int cantidadOfertas = cr.ac.ucenfotec.bl.dao.DAOOferta.obtenerCantidadOfertas(id);
        return String.format("🔨 Subasta [%d] [%s] | Creador: %s | Precio Base: $%.2f\n   Vence: %s | En lista: %d objetos, %d ofertas", 
                id, estado, creador.getNombre(), precioMinimo, fechaVencimiento, objetosSubastados.size(), cantidadOfertas);
    }

    public Oferta obtenerOfertaGanadora() {
        try {
            return cr.ac.ucenfotec.bl.dao.DAOOferta.obtenerOfertaGanadora(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
