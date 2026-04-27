package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOColeccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;

import java.time.LocalDate;

public class GestorColeccionista {

    public static String registrarColeccionista(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacion, String direccion) throws Exception {
        return DAOColeccionista.insertarColeccionista(new Coleccionista(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion));
    }
}
