package cr.ac.ucenfotec.bl.usuarios;

public class Coleccionista extends Usuario {
    private int puntuacion;
    private String direccion;

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

    @Override
    public String toString() {
        return "Coleccionista [puntuacion=" + puntuacion + ", direccion=" + direccion + ", getPuntuacion()="
                + getPuntuacion() + ", getNombre()=" + getNombre() + ", getId()=" + getId() + ", getDireccion()="
                + getDireccion() + ", getFechaNacimiento()=" + getFechaNacimiento() + ", getCorreo()=" + getCorreo()
                + "]";
    }

    
}
