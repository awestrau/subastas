package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;

public class DAOColeccionista {
    private static String statement;
    private static String query;

    public static String insertarColeccionista(Coleccionista coleccionista) throws Exception {
        statement = "INSERT INTO t_coleccionistas VALUES ('" + coleccionista.getId() + "', '" + coleccionista.getNombre() + "', '" +
                coleccionista.getPassword() + "', '" + coleccionista.getFechaNacimiento() + "', '" + coleccionista.getCorreo() + "', " +
                coleccionista.getPuntuacion() + ", '" + coleccionista.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Coleccionista registrado exitosamente.";
    }

    public static void listarColeccionistas() throws Exception {
        query = "SELECT * FROM t_coleccionistas;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()){
            System.out.println("\n***No hay coleccionistas***");
            return;
        }

        do{
            Coleccionista coleccionista = new Coleccionista(resultado.getString("nombre"), resultado.getString("id"),
                    resultado.getString("password"), resultado.getDate("fecha_nacimiento").toLocalDate(),
                    resultado.getString("correo"), resultado.getInt("puntuacion"), resultado.getString("direccion"));

            System.out.println(coleccionista);

        }while(resultado.next());
    }
}
