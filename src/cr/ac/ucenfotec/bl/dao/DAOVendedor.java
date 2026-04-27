package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Moderador;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;
import java.time.LocalDate;

public class DAOVendedor {
    private static String statement;
    private static String query;

    public static String insertarVendedor(Vendedor vendedor) throws Exception {
        statement = "INSERT INTO t_vendedores VALUES ('" + vendedor.getId() + "', '" + vendedor.getNombre() + "', '" +
                vendedor.getPassword() + "', '" + vendedor.getFechaNacimiento() + "', '" + vendedor.getCorreo() + "', " +
                vendedor.getPuntuacion() + ", '" + vendedor.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Vendedor registrado exitosamente.";
    }

    public static java.util.ArrayList<Vendedor> listarVendedores() throws Exception {
        query = "SELECT * FROM t_vendedores;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        java.util.ArrayList<Vendedor> lista = new java.util.ArrayList<>();
        if (!resultado.next()){
            return lista;
        }

        do{
            Vendedor vendedor = new Vendedor(resultado.getString("nombre"), resultado.getString("id"),
                    resultado.getString("password"), resultado.getDate("fecha_nacimiento").toLocalDate(),
                    resultado.getString("correo"), resultado.getInt("puntuacion"), resultado.getString("direccion"));
            lista.add(vendedor);
        }while(resultado.next());
        return lista;
    }
}
