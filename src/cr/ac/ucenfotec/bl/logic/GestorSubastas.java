package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.OrdenAdjudicacion;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import java.util.ArrayList;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;
import java.util.ArrayList;

public class GestorSubastas {
    // public GestorSubastas() {
    // listaSubastas = new ArrayList<>();
    // }

    public static String crearSubasta(
            Coleccionista creador,
            double precioMinimo,
            ArrayList<Objeto> objetosSubastados) throws Exception {
        Subasta subasta = new Subasta(creador, precioMinimo, objetosSubastados);
        return DAOSubasta.insertarSubasta(subasta);
    }

    public static String crearSubasta(
            Vendedor creador,
            double precioMinimo,
            ArrayList<Objeto> objetosSubastados) throws Exception {
        Subasta subasta = new Subasta(creador, precioMinimo, objetosSubastados);
        return DAOSubasta.insertarSubasta(subasta);
    }

    public static String actualizarEstadoSubasta(int id, boolean vigente) throws Exception {
        return DAOSubasta.actualizarEstado(id, vigente);
    }



    public static ArrayList<Subasta> listarSubastas() throws Exception {
        return DAOSubasta.listarSubastas();
    }
}
