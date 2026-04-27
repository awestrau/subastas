package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.bl.logic.GestorSubastas;
import cr.ac.ucenfotec.bl.logic.GestorUsuarios;
import cr.ac.ucenfotec.tl.Controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    // Los constructores ya no son necesarios ya que todas las clases son estáticas.
    // Los gestores GestorUsuarios y GestorSubastas ahora se llaman directamente con sus métodos estáticos.

    public static void iniciar() throws Exception {
        System.out.println("=== Sistema de Subastas ===");
        verificarModerador();

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 10);
    }

    private static void verificarModerador() throws Exception {
        if (!Controlador.existeModerador()) {
            System.out.println(
                "No existe un moderador en el sistema. Debe registrar uno para continuar."
            );
            Controlador.registrarUsuario(1);
        }
    }

    private static void mostrarMenu() {
        System.out.println(
            "\n--- Menú Principal ---\n" +
                "1. Registro de usuarios\n" +
                "2. Listado de usuarios\n" +
                "3. Creación de subastas\n" +
                "4. Listado de subastas\n" +
                "5. Creación de ofertas\n" +
                "6. Listado de ofertas\n" +
                "7. Listado de objetos\n" +
                "8. Actualizar estado de objeto\n" +
                "9. Actualizar estado de subasta\n" +
                "10. Salir"
        );
    }

    private static void procesarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                Controlador.registrarUsuario(0);
                break;
            case 2:
                Controlador.listarUsuarios();
                break;
            case 3:
                Controlador.crearSubasta();
                break;
            case 4:
                Controlador.listarSubastas();
                break;
            case 5:
                Controlador.crearOferta();
                break;
            case 6:
                Controlador.listarOfertas();
                break;
            case 7:
                Controlador.listarObjetos();
                break;
            case 8:
                Controlador.actualizarEstadoObjeto();
                break;
            case 9:
                Controlador.actualizarEstadoSubasta();
                break;
            case 10:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    private static void registrarUsuario(int tipoForzado) {
        try {
            int tipo = tipoForzado;
            if (tipo == 0) {
                System.out.println(
                    "\n--- Registro de usuario ---\n" +
                        "1. Vendedor\n" +
                        "2. Coleccionista"
                );
                tipo = leerEntero("Seleccione una opción: ");

                if (tipo != 1 && tipo != 2) {
                    System.out.println("Opción inválida.");
                    return;
                }
                tipo = (tipo == 1) ? 2 : 3; // Mapeo para moderador (1), vendedor (2), coleccionista (3)
            }

            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Identificación: ");
            String id = scanner.nextLine();
            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            LocalDate fechaNacimiento = leerFecha(
                "Fecha de nacimiento (YYYY-MM-DD): "
            );

            if (tipo == 1) {
                // Moderador
                GestorUsuarios.registrarModerador(
                    nombre,
                    id,
                    password,
                    fechaNacimiento,
                    correo
                );
                System.out.println("Moderador registrado exitosamente.");
            } else if (tipo == 2) {
                // Vendedor
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                GestorUsuarios.registrarVendedor(
                    nombre,
                    id,
                    password,
                    fechaNacimiento,
                    correo,
                    puntuacion,
                    direccion
                );
                System.out.println("Vendedor registrado exitosamente.");
            } else if (tipo == 3) {
                // Coleccionista
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                GestorUsuarios.registrarColeccionista(
                    nombre,
                    id,
                    password,
                    fechaNacimiento,
                    correo,
                    puntuacion,
                    direccion
                );
                System.out.println("Coleccionista registrado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n--- Listado de Usuarios ---");
        try {
            ArrayList<Usuario> usuarios = GestorUsuarios.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println(u.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(mensaje);
            try {
                String input = scanner.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Formato de fecha inválido. Intente de nuevo con formato YYYY-MM-DD."
                );
            }
        }
    }

}
