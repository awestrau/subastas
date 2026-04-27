package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.OrdenAdjudicacion;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOOrdenAdjudicacion {

    public static String registrarOrden(OrdenAdjudicacion orden) throws Exception {
        String statement = "INSERT INTO t_ordenes_adjudicacion (id_ganador, id_subasta, id_oferta) VALUES ('" +
                orden.getIdGanador() + "', " +
                orden.getIdSubasta() + ", " +
                orden.getIdOferta() + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Orden de adjudicación registrada exitosamente.";
    }

    public static ArrayList<OrdenAdjudicacion> listarOrdenes() throws Exception {
        ArrayList<OrdenAdjudicacion> ordenes = new ArrayList<>();
        String statement = "SELECT id_orden, id_ganador, id_subasta, id_oferta, fecha FROM t_ordenes_adjudicacion;";
        ResultSet rs = Conector.getConexion().ejecutarQuery(statement);
        
        while (rs != null && rs.next()) {
            OrdenAdjudicacion orden = new OrdenAdjudicacion(
                    rs.getInt("id_orden"),
                    rs.getString("id_ganador"),
                    rs.getInt("id_subasta"),
                    rs.getInt("id_oferta"),
                    rs.getTimestamp("fecha").toLocalDateTime()
            );
            ordenes.add(orden);
        }
        return ordenes;
    }
}
