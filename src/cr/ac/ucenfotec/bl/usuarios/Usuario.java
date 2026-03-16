package cr.ac.ucenfotec.bl.usuarios;

import java.time.LocalDate;
import java.time.Period;

public abstract class Usuario {

    private String nombre;
    private String id;
    private LocalDate fechaNacimiento;
    private String password;
    private String correo;
    public Usuario() {
    }

    public Usuario(String nombre, String id, String password, LocalDate fechaNacimiento, String correo) {
        this.nombre = nombre;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", id=" + id + ", fechaNacimiento=" + fechaNacimiento + ", password="
                + password + ", correo=" + correo + "]";
    }

    
}
