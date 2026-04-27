package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;

public class GestorOferta {
    public static String realizarOferta(Coleccionista oferente, Subasta subasta, double monto) throws Exception {
        if (subasta.getCreador().getIdentificacion().equals(oferente.getIdentificacion())) {
            throw new Exception("El creador de la subasta no puede ofertar");
        }
        if (monto < subasta.getPrecioMinimo()) {
            throw new Exception("El monto de la oferta no puede ser menor al precio mínimo de la subasta.");
        }
        // Validar si la oferta es mayor a la oferta más alta actual si existiera la validación
        return DAOOferta.registrarOferta(subasta, oferente, monto);
    }
}
