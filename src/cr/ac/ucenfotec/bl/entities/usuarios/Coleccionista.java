package cr.ac.ucenfotec.bl.entities.usuarios;

import cr.ac.ucenfotec.bl.entities.Objeto;

import java.time.LocalDate;

import java.util.ArrayList;

public class Coleccionista extends Usuario {
    private int puntuacion;
    private String direccion;
    private ArrayList<String> intereses;
    private ArrayList<Objeto> objetos;

    public Coleccionista() {
        this.intereses = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    public Coleccionista(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacionColeccionista, String direccion) {
        super(nombre, id, password, fechaNacimiento, correo);
        this.puntuacion = puntuacionColeccionista;
        this.direccion = direccion;
        this.intereses = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    public void agregarIntereses(String interes) {
        this.intereses.add(interes);
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void agregarObjetos(Objeto objeto) {
        this.objetos.add(objeto);
    }

    @Override
    public String toString() {
        return "Coleccionista [puntuacion=" + puntuacion + ", direccion=" + direccion + ", intereses=" + intereses
                + ", objetos=" + objetos + ", getPuntuacion()=" + getPuntuacion() + ", getNombre()=" + getNombre()
                + ", getId()=" + getId() + ", getDireccion()=" + getDireccion() + ", getFechaNacimiento()="
                + getFechaNacimiento() + ", getCorreo()=" + getCorreo() + "]";
    }

    
}
