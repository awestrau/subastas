package cr.ac.ucenfotec.bl.usuarios;

import java.util.ArrayList;
import cr.ac.ucenfotec.bl.Objetos;

public class Coleccionista extends Usuario {
    private int puntuacion;
    private String direccion;
    private ArrayList<String> intereses;
    private ArrayList<Objetos> objetos;

    public Coleccionista(String nombre, String correo, String password, int puntuacionColeccionista, String direccion) {
        super(nombre, correo, password);
        this.puntuacion = puntuacionColeccionista;
        this.direccion = direccion;
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

    public ArrayList<Objetos> getObjetos() {
        return objetos;
    }

    public void agregarObjetos(Objetos objeto) {
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
