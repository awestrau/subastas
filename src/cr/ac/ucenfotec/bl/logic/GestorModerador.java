package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOModerador;
import cr.ac.ucenfotec.bl.entities.usuarios.Moderador;

import java.time.LocalDate;

public class GestorModerador {

    public static String registrarModerador(String nombre, String id, String password, LocalDate fechaNacimiento, String correo) throws Exception {
        return DAOModerador.insertarModerador(new Moderador(nombre, id, password, fechaNacimiento, correo));
    }
}
