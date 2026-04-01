package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Moderador;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GestorUsuarios {
    private static ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public boolean existeModerador() {
        for (Usuario u : usuarios) {
            if (u instanceof Moderador) {
                return true;
            }
        }
        return false;
    }

    private void validarEdadConfiguracion(LocalDate fechaNacimiento) throws Exception {
        if (fechaNacimiento == null) {
            throw new Exception("La fecha de nacimiento es requerida.");
        }
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 18) {
            throw new Exception("El usuario debe ser mayor de edad (18 años o más).");
        }
    }

    public void registrarModerador(String nombre, String id, String password, LocalDate fechaNacimiento, String correo) throws Exception {
        if (existeModerador()) {
            throw new Exception("Ya existe un moderador registrado en el sistema.");
        }
        validarEdadConfiguracion(fechaNacimiento);
        Moderador nModerador = new Moderador(nombre, id, password, fechaNacimiento, correo);
        usuarios.add(nModerador);
    }

    public void registrarVendedor(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacionVendedor, String direccion) throws Exception {
        validarEdadConfiguracion(fechaNacimiento);
        Vendedor nVendedor = new Vendedor(nombre, id, password, fechaNacimiento, correo, puntuacionVendedor, direccion);
        usuarios.add(nVendedor);
    }

    public void registrarColeccionista(String nombre, String id, String password, LocalDate fechaNacimiento, String correo, int puntuacionColeccionista, String direccion) throws Exception {
        validarEdadConfiguracion(fechaNacimiento);
        Coleccionista nColeccionista = new Coleccionista(nombre, id, password, fechaNacimiento, correo, puntuacionColeccionista, direccion);
        usuarios.add(nColeccionista);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }
}
