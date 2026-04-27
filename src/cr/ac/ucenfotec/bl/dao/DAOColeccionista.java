package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.dl.Conector;

public class DAOColeccionista {
    private static String statement;
    private static String query;

    public static String insertarColeccionista(Coleccionista coleccionista) throws Exception {
        statement = "INSERT INTO t_coleccionistas VALUES ('" + coleccionista.getNombre() + "', '" + coleccionista.getId() + "', '" +
                coleccionista.getPassword() + "', " + coleccionista.getFechaNacimiento() + ", '" + coleccionista.getCorreo() + "', " +
                coleccionista.getPuntuacion() + ", '" + coleccionista.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Coleccionista registrado exitosamente.";
    }
}
