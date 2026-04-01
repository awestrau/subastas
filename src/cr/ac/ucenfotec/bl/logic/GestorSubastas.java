package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.OrdenAdjudicacion;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;

import java.util.ArrayList;

public class GestorSubastas {

    private static ArrayList<Subasta> listaSubastas;

    public GestorSubastas() {
        listaSubastas = new ArrayList<>();
    }

    public void crearSubasta(Coleccionista creador, double precioMinimo, ArrayList<String> nombresObjetos, ArrayList<String> descripciones) throws Exception {

            ArrayList<Objeto> objetoSubastados = new ArrayList<>();

            for(int i=0;i<nombresObjetos.size();i++){
                Objeto obj = new Objeto(
                        nombresObjetos.get(i),
                        descripciones.get(i)
                );
                objetoSubastados.add(obj);
            }

        Subasta subasta = new Subasta(creador, precioMinimo, objetoSubastados);
        listaSubastas.add(subasta);
    }

    public void crearSubasta(Vendedor creador, double precioMinimo, ArrayList<String> nombresObjetos, ArrayList<String> descripciones) throws Exception {

        ArrayList<Objeto> objetoSubastados = new ArrayList<>();

        for(int i=0;i<nombresObjetos.size();i++){
            Objeto obj = new Objeto(
                    nombresObjetos.get(i),
                    descripciones.get(i)
            );
            objetoSubastados.add(obj);
        }

        Subasta subasta = new Subasta(creador, precioMinimo, objetoSubastados);
        listaSubastas.add(subasta);
    }

    public void realizaroferta (Coleccionista oferente, Subasta subasta, double monto) throws Exception{
        if (subasta.getCreador().equals(oferente)){
            throw new Exception("El creador de la subasta no puede ofertar");
        }
        subasta.agregarOferta(oferente, monto);
    }

    public void crearOrdenAdjudicacion(){
        for(Subasta subasta: listaSubastas){
            subasta.actualizarVigencia();
            if(!subasta.isVigente() && !subasta.getListaOfertas().isEmpty()){
                OrdenAdjudicacion ordenAdjudicacion = new OrdenAdjudicacion(subasta.obtenerOfertaGanadora().getOferente().getNombre(),
                        subasta.getObjetosSubastados(), subasta.obtenerOfertaGanadora().getPrecioOfertado());
            }
        }

    }

    public ArrayList<Subasta> listarSubastas() {
        return listaSubastas;
    }

}