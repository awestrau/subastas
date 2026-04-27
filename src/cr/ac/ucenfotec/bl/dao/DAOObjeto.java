package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.dl.Conector;

public class DAOObjeto {
    private static String statement;

    public static String insertarObjeto(Objeto objeto) throws Exception {
        statement = "INSERT INTO t_objetos (nombre, descripcion, estado, fecha_compra, antiguedad) VALUES ('" +
                objeto.getNombre() + "', '" +
                objeto.getDescripcion() + "', '" +
                objeto.getEstado() + "', '" +
                objeto.getFechaCompra().toString() + "', '" +
                objeto.getAntiguedad() + "');";
        Conector.getConexion().ejecutarStatement(statement);
        return "Objeto registrado exitosamente.";
    }

    public static java.util.ArrayList<Objeto> listarObjetos() throws Exception {
        java.util.ArrayList<Objeto> objetos = new java.util.ArrayList<>();
        statement = "SELECT id_objeto, nombre, descripcion, estado, fecha_compra, antiguedad FROM t_objetos;";
        java.sql.ResultSet rs = Conector.getConexion().ejecutarQuery(statement);
        while (rs != null && rs.next()) {
            Objeto o = new Objeto(
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getDate("fecha_compra").toLocalDate(),
                    rs.getString("antiguedad")
            );
            o.setId(rs.getInt("id_objeto"));
            objetos.add(o);
        }
        return objetos;
    }

    public static String actualizarEstado(int id, String nuevoEstado) throws Exception {
        statement = "UPDATE t_objetos SET estado = '" + nuevoEstado + "' WHERE id_objeto = " + id + ";";
        Conector.getConexion().ejecutarStatement(statement);
        return "Estado del objeto actualizado a: " + nuevoEstado;
    }
}
