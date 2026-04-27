package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Moderador;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;

public class DAOModerador {
    private static String statement;
    private static String query;

    public static String insertarModerador(Moderador moderador) throws Exception {
        statement = "INSERT INTO t_moderadores VALUES ('" + moderador.getId() + "', '" + moderador.getNombre() + "', '" +
                moderador.getPassword() + "', '" + moderador.getFechaNacimiento() + "', '" + moderador.getCorreo() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Moderador registrado exitosamente.";
    }

    public static void listarModeradores() throws Exception {
        query = "SELECT * FROM t_moderadores;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()){
            System.out.println("\n***No hay moderadores***");
            return;
        }

        do{
            Moderador moderador = new Moderador(resultado.getString("nombre"), resultado.getString("id"),
                    resultado.getString("password"), resultado.getDate("fecha_nacimiento").toLocalDate(), resultado.getString("correo"));

            System.out.println(moderador);

        }while(resultado.next());
    }

    public static Boolean existeModerador() throws Exception {
        query = "SELECT * FROM t_moderadores;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()){
            return false;
        }
        return true;
    }
}
