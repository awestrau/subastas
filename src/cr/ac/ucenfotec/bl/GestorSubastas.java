package cr.ac.ucenfotec.bl;

import cr.ac.ucenfotec.bl.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.usuarios.Usuario;
import cr.ac.ucenfotec.bl.usuarios.Vendedor;

import java.util.ArrayList;

public class GestorSubastas {

    private ArrayList<Subastas> listaSubastas;

    public GestorSubastas() {
        this.listaSubastas = new ArrayList<>();
    }

    public void crearSubasta(Coleccionista creador, double precioMinimo, ArrayList<String> nombresObjetos, ArrayList<String> descripciones) throws Exception {

            ArrayList<Objetos> objetosSubastados = new ArrayList<>();

            for(int i=0;i<nombresObjetos.size();i++){
                Objetos obj = new Objetos(
                        nombresObjetos.get(i),
                        descripciones.get(i)
                );
                objetosSubastados.add(obj);
            }

        Subastas subasta = new Subastas(creador, precioMinimo, objetosSubastados);
        this.listaSubastas.add(subasta);
    }

    public void crearSubasta(Vendedor creador, double precioMinimo, ArrayList<String> nombresObjetos, ArrayList<String> descripciones) throws Exception {

        ArrayList<Objetos> objetosSubastados = new ArrayList<>();

        for(int i=0;i<nombresObjetos.size();i++){
            Objetos obj = new Objetos(
                    nombresObjetos.get(i),
                    descripciones.get(i)
            );
            objetosSubastados.add(obj);
        }

        Subastas subasta = new Subastas(creador, precioMinimo, objetosSubastados);
        this.listaSubastas.add(subasta);
    }

    public void realizaroferta (Coleccionista oferente, Subastas subasta, double monto) throws Exception{
        if (subasta.getCreador().equals(oferente)){
            throw new Exception("El creador de la subasta no puede ofertar");
        }
        subasta.agregarOferta(oferente, monto);
    }

    public void crearOrdenAdjudicacion(){
        for(Subastas subasta: listaSubastas){
            subasta.actualizarVigencia();
            if(!subasta.isVigente() && !subasta.getListaOfertas().isEmpty()){
                OrdenAdjudicacion ordenAdjudicacion = new OrdenAdjudicacion(subasta.obtenerOfertaGanadora().getOferente().getNombre(),
                        subasta.getObjetosSubastados(), subasta.obtenerOfertaGanadora().getPrecioOfertado());
            }
        }

    }

    public ArrayList<Subastas> listarSubastas() {
        return this.listaSubastas;
    }

}