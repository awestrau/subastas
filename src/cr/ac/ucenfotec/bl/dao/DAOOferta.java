package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.logic.GestorColeccionista;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOOferta {

    public static String registrarOferta(Subasta subasta, Coleccionista oferente, double monto) throws Exception {
        String statement = "INSERT INTO t_ofertas (id_subasta, id_oferente, puntuacion_oferente, precio_ofertado) VALUES (" +
                subasta.getId() + ", '" +
                oferente.getId() + "', " +
                oferente.getPuntuacion() + ", " +
                monto + ");";
        Conector.getConexion().ejecutarStatement(statement);
        return "Oferta registrada correctamente en la base de datos.";
    }

    public static ArrayList<Oferta> listarOfertasPorSubasta(int idSubasta) throws Exception {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        String statement = "SELECT id_oferta, id_subasta, id_oferente, puntuacion_oferente, precio_ofertado FROM t_ofertas WHERE id_subasta = " + idSubasta + ";";
        ResultSet rs = Conector.getConexion().ejecutarQuery(statement);
        
        ArrayList<Coleccionista> coleccionistas = GestorColeccionista.listarColeccionistas();

        while (rs != null && rs.next()) {
            String idOferente = rs.getString("id_oferente");
            Coleccionista oferente = null;
            for (Coleccionista c : coleccionistas) {
                if (c.getId().equals(idOferente)) {
                    oferente = c;
                    break;
                }
            }
            if (oferente != null) {
                Oferta oferta = new Oferta(rs.getInt("id_oferta"), oferente, rs.getDouble("precio_ofertado"));
                ofertas.add(oferta);
            }
        }
        return ofertas;
    }

    public static int obtenerCantidadOfertas(int idSubasta) {
        try {
            String query = "SELECT COUNT(*) as total FROM t_ofertas WHERE id_subasta = " + idSubasta + ";";
            ResultSet rs = Conector.getConexion().ejecutarQuery(query);
            if (rs != null && rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Oferta obtenerOfertaGanadora(int idSubasta) throws Exception {
        String query = "SELECT id_oferta, id_oferente, precio_ofertado FROM t_ofertas WHERE id_subasta = " + idSubasta + " ORDER BY precio_ofertado DESC LIMIT 1;";
        ResultSet rs = Conector.getConexion().ejecutarQuery(query);
        if (rs != null && rs.next()) {
            String idOferente = rs.getString("id_oferente");
            ArrayList<Coleccionista> coleccionistas = GestorColeccionista.listarColeccionistas();
            Coleccionista oferente = null;
            for (Coleccionista c : coleccionistas) {
                if (c.getId().equals(idOferente)) {
                    oferente = c;
                    break;
                }
            }
            if (oferente != null) {
                return new Oferta(rs.getInt("id_oferta"), oferente, rs.getDouble("precio_ofertado"));
            }
        }
        return null;
    }
}
