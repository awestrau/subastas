package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.dao.DAOObjeto;

import java.util.ArrayList;

public class GestorObjeto {

    public static String crearObjeto(String nombre, String descripcion) throws Exception {
        Objeto nuevoObjeto = new Objeto(nombre, descripcion);
        return DAOObjeto.insertarObjeto(nuevoObjeto);
    }

    public static Objeto crearYObtenerObjeto(String nombre, String descripcion) throws Exception {
        Objeto nuevoObjeto = new Objeto(nombre, descripcion);
        return DAOObjeto.insertarYObtenerObjeto(nuevoObjeto);
    }

    public static ArrayList<Objeto> listarObjetos() throws Exception {
        return DAOObjeto.listarObjetos();
    }

    public static String actualizarEstadoObjeto(int id, String estado) throws Exception {
        return DAOObjeto.actualizarEstado(id, estado);
    }
}
