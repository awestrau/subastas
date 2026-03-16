package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.GestorUsuarios;
import cr.ac.ucenfotec.bl.usuarios.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private GestorUsuarios gestor;
    private Scanner scanner;

    public Menu() {
        gestor = new GestorUsuarios();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Sistema de Subastas ===");
        verificarModerador();

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 7);
    }

    private void verificarModerador() {
        if (!gestor.existeModerador()) {
            System.out.println("No existe un moderador en el sistema. Debe registrar uno para continuar.");
            registrarUsuario(1);
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Registro de usuarios");
        System.out.println("2. Listado de usuarios");
        System.out.println("3. Creación de subastas");
        System.out.println("4. Listado de subastas");
        System.out.println("5. Creación de ofertas");
        System.out.println("6. Listado de ofertas");
        System.out.println("7. Salir");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarUsuario(0);
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                System.out.println("Opción en desarrollo: Creación de subastas.");
                break;
            case 4:
                System.out.println("Opción en desarrollo: Listado de subastas.");
                break;
            case 5:
                System.out.println("Opción en desarrollo: Creación de ofertas.");
                break;
            case 6:
                System.out.println("Opción en desarrollo: Listado de ofertas.");
                break;
            case 7:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    private void registrarUsuario(int tipoForzado) {
        try {
            int tipo = tipoForzado;
            if (tipo == 0) {
                System.out.println("\n--- Registro de usuario ---");
                System.out.println("¿Qué tipo de usuario desea registrar?");
                System.out.println("1. Vendedor");
                System.out.println("2. Coleccionista");
                tipo = leerEntero("Seleccione una opción: ");
                
                if (tipo != 1 && tipo != 2) {
                    System.out.println("Opción inválida.");
                    return;
                }
                tipo = (tipo == 1) ? 2 : 3;
            }

            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Identificación: ");
            String id = scanner.nextLine();
            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();
            
            LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento (YYYY-MM-DD): ");

            if (tipo == 1) {
                gestor.registrarModerador(nombre, id, password, fechaNacimiento, correo);
                System.out.println("Moderador registrado exitosamente.");
            } else if (tipo == 2) {
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                gestor.registrarVendedor(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion);
                System.out.println("Vendedor registrado exitosamente.");
            } else if (tipo == 3) {
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                gestor.registrarColeccionista(nombre, id, password, fechaNacimiento, correo, puntuacion, direccion);
                System.out.println("Coleccionista registrado exitosamente.");
            }

        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- Listado de Usuarios ---");
        ArrayList<Usuario> usuarios = gestor.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u.toString());
            }
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(mensaje);
            try {
                String input = scanner.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente de nuevo con formato YYYY-MM-DD.");
            }
        }
    }
}
