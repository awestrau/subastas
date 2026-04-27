package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.dl.Conector;

import java.time.LocalDate;

public class DAOVendedor {
    private static String statement;
    private static String query;

    public static String insertarVendedor(Vendedor vendedor) throws Exception {
        statement = "INSERT INTO t_vendedores VALUES ('" + vendedor.getNombre() + "', '" + vendedor.getId() + "', '" +
                vendedor.getPassword() + "', " + vendedor.getFechaNacimiento() + ", '" + vendedor.getCorreo() + "', " +
                vendedor.getPuntuacion() + ", '" + vendedor.getDireccion() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Vendedor registrado exitosamente.";
    }
}
