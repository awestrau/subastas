package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Moderador;
import cr.ac.ucenfotec.dl.Conector;

public class DAOModerador {
    private static String statement;
    private static String query;

    public static String insertarModerador(Moderador moderador) throws Exception {
        statement = "INSERT INTO t_moderadores VALUES ('" + moderador.getNombre() + "', '" + moderador.getId() + "', '" +
                moderador.getPassword() + "', " + moderador.getFechaNacimiento() + ", '" + moderador.getCorreo() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Moderador registrado exitosamente.";
    }
}
