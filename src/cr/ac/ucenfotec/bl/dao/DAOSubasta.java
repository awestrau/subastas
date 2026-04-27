package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.bl.logic.GestorUsuarios;
import cr.ac.ucenfotec.dl.Conector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOSubasta {
    private static String statement;

    public static String insertarSubasta(Subasta subasta) throws Exception {
        int vigenteInt = subasta.isVigente() ? 1 : 0;
        statement = "INSERT INTO t_subastas (fecha_vencimiento, tiempo_para_vencer, creador_id, calificacion_creador, precio_minimo, vigente) VALUES ('" +
                subasta.getFechaVencimiento().toLocalDate().toString() + "', '" +
                subasta.getTiempoParaVencer().toString() + "', '" +
                subasta.getCreador().getId() + "', " +
                subasta.getCalificacionCreador() + ", " +
                subasta.getPrecioMinimo() + ", " +
                vigenteInt + ");";

        int idSubasta = Conector.getConexion().ejecutarStatementConRetornoId(statement);

        // Insertar objetos asociados
        for (Objeto obj : subasta.getObjetosSubastados()) {
            String insertObj = "INSERT INTO t_subasta_objetos (id_subasta, id_objeto) VALUES (" + idSubasta + ", " + obj.getId() + ");";
            Conector.getConexion().ejecutarStatement(insertObj);
        }

        return "Subasta registrada exitosamente con ID: " + idSubasta;
    }

    public static ArrayList<Subasta> listarSubastas() throws Exception {
        ArrayList<Subasta> subastas = new ArrayList<>();
        statement = "SELECT id_subasta, fecha_vencimiento, tiempo_para_vencer, creador_id, calificacion_creador, precio_minimo, vigente FROM t_subastas;";
        ResultSet rs = Conector.getConexion().ejecutarQuery(statement);
        
        ArrayList<Usuario> todosLosUsuarios = GestorUsuarios.listarUsuarios();
        ArrayList<Objeto> todosLosObjetos = DAOObjeto.listarObjetos();

        while (rs != null && rs.next()) {
            Subasta s = new Subasta();
            s.setId(rs.getInt("id_subasta"));
            s.setFechaVencimiento(rs.getDate("fecha_vencimiento").toLocalDate().atStartOfDay());
            s.setCalificacionCreador(rs.getDouble("calificacion_creador"));
            s.setPrecioMinimo(rs.getDouble("precio_minimo"));
            s.setEstado(rs.getBoolean("vigente"));

            String creadorId = rs.getString("creador_id");
            Usuario creador = null;
            for (Usuario u : todosLosUsuarios) {
                if (u.getId().equals(creadorId)) {
                    creador = u;
                    break;
                }
            }
            s.setCreador(creador);

            // Fetch objects for this subasta
            ArrayList<Objeto> objetosDeSubasta = new ArrayList<>();
            String queryObjs = "SELECT id_objeto FROM t_subasta_objetos WHERE id_subasta = " + s.getId() + ";";
            ResultSet rsObjs = Conector.getConexion().ejecutarQuery(queryObjs);
            while (rsObjs != null && rsObjs.next()) {
                int idObj = rsObjs.getInt("id_objeto");
                for (Objeto o : todosLosObjetos) {
                    if (o.getId() == idObj) {
                        objetosDeSubasta.add(o);
                        break;
                    }
                }
            }
            s.setObjetosSubastados(objetosDeSubasta);
            subastas.add(s);
        }
        return subastas;
    }

    public static String actualizarEstado(int id, boolean vigente) throws Exception {
        int vigenteInt = vigente ? 1 : 0;
        statement = "UPDATE t_subastas SET vigente = " + vigenteInt + " WHERE id_subasta = " + id + ";";
        Conector.getConexion().ejecutarStatement(statement);
        return "Estado de la subasta actualizado exitosamente.";
    }
}
