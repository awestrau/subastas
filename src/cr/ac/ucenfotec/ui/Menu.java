package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Vendedor;
import cr.ac.ucenfotec.bl.logic.GestorSubastas;
import cr.ac.ucenfotec.bl.logic.GestorUsuarios;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    // Los constructores ya no son necesarios ya que todas las clases son estáticas.
    // Los gestores GestorUsuarios y GestorSubastas ahora se llaman directamente con sus métodos estáticos.

    public static void iniciar() {
        System.out.println("=== Sistema de Subastas ===");
        verificarModerador();

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 7);
    }

    private static void verificarModerador() {
        if (!GestorUsuarios.existeModerador()) {
            System.out.println(
                "No existe un moderador en el sistema. Debe registrar uno para continuar."
            );
            registrarUsuario(1);
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
                "7. Salir"
        );
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarUsuario(0);
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                crearSubasta();
                break;
            case 4:
                listarSubastas();
                break;
            case 5:
                crearOferta();
                break;
            case 6:
                listarOferta();
                break;
            case 7:
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
        ArrayList<Usuario> usuarios = GestorUsuarios.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u.toString());
            }
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

    private static void crearSubasta() {
        try {
            System.out.println("\n--- Crear Subasta ---");

            ArrayList<Usuario> usuarios = GestorUsuarios.listarUsuarios();

            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(i + " - " + usuarios.get(i).getNombre());
            }

            int indice = leerEntero("Seleccione el creador de esta subasta: ");
            Usuario creador = usuarios.get(indice);

            // VALIDACIÓN DEL TIPO DE USUARIO
            if (
                !(creador instanceof Coleccionista) &&
                !(creador instanceof Vendedor)
            ) {
                System.out.println(
                    "Solo los vendedores o coleccionistas pueden crear subastas."
                );
                return;
            }

            double precioMin = leerEntero("Precio mínimo: ");

            int cantidad = leerEntero("Cantidad de objetos: ");

            ArrayList<String> nombres = new ArrayList<>();
            ArrayList<String> descripciones = new ArrayList<>();

            for (int i = 0; i < cantidad; i++) {
                System.out.print("Nombre objeto: ");
                nombres.add(scanner.nextLine());

                System.out.print("Descripción: ");
                descripciones.add(scanner.nextLine());
            }

            if (creador instanceof Coleccionista) {
                GestorSubastas.crearSubasta(
                    (Coleccionista) creador,
                    precioMin,
                    nombres,
                    descripciones
                );
            } else {
                GestorSubastas.crearSubasta(
                    (Vendedor) creador,
                    precioMin,
                    nombres,
                    descripciones
                );
            }

            System.out.println("Subasta creada correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarSubastas() {
        System.out.println("\n--- Listado de Subastas ---");

        ArrayList<Subasta> subastas = GestorSubastas.listarSubastas();

        if (subastas.isEmpty()) {
            System.out.println("No hay subastas registradas.");
            return;
        }

        for (int i = 0; i < subastas.size(); i++) {
            System.out.println(i + " - " + subastas.get(i).toString());
        }
    }

    private static void crearOferta() {
        try {
            System.out.println("\n--- Crear Oferta ---");

            ArrayList<Usuario> usuarios = GestorUsuarios.listarUsuarios();
            ArrayList<Coleccionista> coleccionistas = new ArrayList<>();

            for (Usuario u : usuarios) {
                if (u instanceof Coleccionista) {
                    coleccionistas.add((Coleccionista) u);
                }
            }

            if (coleccionistas.isEmpty()) {
                System.out.println("No hay coleccionistas registrados.");
                return;
            }

            System.out.println("Seleccione oferente:");

            for (int i = 0; i < coleccionistas.size(); i++) {
                System.out.println(
                    i + " - " + coleccionistas.get(i).getNombre()
                );
            }

            int indiceColeccionista = leerEntero("Opción: ");
            Coleccionista oferente = coleccionistas.get(indiceColeccionista);

            ArrayList<Subasta> subastas = GestorSubastas.listarSubastas();

            if (subastas.isEmpty()) {
                System.out.println("No hay subastas disponibles.");
                return;
            }

            System.out.println("Seleccione subasta:");

            for (int i = 0; i < subastas.size(); i++) {
                System.out.println(i + " - " + subastas.get(i));
            }

            int indiceSubasta = leerEntero("Opción: ");
            Subasta subasta = subastas.get(indiceSubasta);

            double monto = leerEntero("Monto de la oferta: ");

            GestorSubastas.realizaroferta(oferente, subasta, monto);

            System.out.println("Oferta registrada correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarOferta() {
        System.out.println("\n--- Listado de Ofertas ---");

        ArrayList<Subasta> subastas = GestorSubastas.listarSubastas();

        if (subastas.isEmpty()) {
            System.out.println("No hay subastas registradas.");
            return;
        }

        boolean hayOfertas = false;

        for (Subasta s : subastas) {
            if (!s.getListaOfertas().isEmpty()) {
                System.out.println("\nSubasta: " + s.toString());

                for (Oferta o : s.getListaOfertas()) {
                    System.out.println(o.toString());
                }

                hayOfertas = true;
            }
        }

        if (!hayOfertas) {
            System.out.println("No hay ofertas registradas.");
        }
    }
}
