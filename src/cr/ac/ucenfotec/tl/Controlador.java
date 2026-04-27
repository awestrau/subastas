package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.logic.GestorColeccionista;
import cr.ac.ucenfotec.bl.logic.GestorModerador;
import cr.ac.ucenfotec.bl.logic.GestorUsuarios;
import cr.ac.ucenfotec.bl.logic.GestorVendedor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    private static Scanner scanner = new Scanner(System.in);

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

    public static void registrarUsuario(int tipoForzado){
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
                System.out.println(GestorModerador.registrarModerador(nombre, id, password, fechaNacimiento, correo));
            } else if (tipo == 2) {
                // Vendedor
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                System.out.println(GestorVendedor.registrarVendedor(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion));
            } else if (tipo == 3) {
                // Coleccionista
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                System.out.println(GestorColeccionista.registrarColeccionista(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion));
            }
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    public static void listarUsuarios() throws Exception {
        System.out.println("\n--- Listado de Usuarios ---");
        GestorModerador.listarModeradores();
        GestorVendedor.listarVendedores();
        GestorColeccionista.listarColeccionistas();
    }

    public static boolean existeModerador() throws Exception {
        return GestorModerador.existeModerador();
    }
}
