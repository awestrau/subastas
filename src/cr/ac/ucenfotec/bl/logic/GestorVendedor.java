package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOVendedor;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;

import java.time.LocalDate;

public class GestorVendedor {

    public static String registrarVendedor(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacion, String direccion)
            throws Exception {
        return DAOVendedor.insertarVendedor(new Vendedor(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion));
    }

    public static void listarVendedores() throws Exception {
        DAOVendedor.listarVendedores();
    }
}
