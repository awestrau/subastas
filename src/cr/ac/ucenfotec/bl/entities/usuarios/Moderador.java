package cr.ac.ucenfotec.bl.entities.usuarios;

import java.time.LocalDate;

public class Moderador extends Usuario {
    public Moderador() {
    }

    public Moderador(String nombre, String id, String password, LocalDate fechaNacimiento, String correo) {
        super(nombre, id, password, fechaNacimiento, correo);
    }

    @Override
    public String toString() {
        return String.format("🛡️ [Moderador] %s", super.toString());
    }
}