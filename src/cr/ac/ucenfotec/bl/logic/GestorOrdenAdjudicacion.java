package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.OrdenAdjudicacion;
import cr.ac.ucenfotec.bl.dao.DAOOrdenAdjudicacion;
import java.util.ArrayList;

public class GestorOrdenAdjudicacion {

    public static String registrarOrdenAdjudicacion(String idGanador, int idSubasta, int idOferta) throws Exception {
        OrdenAdjudicacion orden = new OrdenAdjudicacion(idGanador, idSubasta, idOferta);
        return DAOOrdenAdjudicacion.registrarOrden(orden);
    }

    public static ArrayList<OrdenAdjudicacion> listarOrdenes() throws Exception {
        return DAOOrdenAdjudicacion.listarOrdenes();
    }
}
