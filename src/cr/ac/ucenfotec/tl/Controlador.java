package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.logic.GestorColeccionista;
import cr.ac.ucenfotec.bl.logic.GestorModerador;
import cr.ac.ucenfotec.bl.logic.GestorUsuarios;
import cr.ac.ucenfotec.bl.logic.GestorVendedor;
import cr.ac.ucenfotec.bl.logic.GestorObjeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.usuarios.Usuario;
import cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.logic.GestorSubastas;
import cr.ac.ucenfotec.bl.logic.GestorOferta;

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

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
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

    public static void registrarUsuario(int tipoForzado) {
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
                System.out.println(GestorVendedor.registrarVendedor(nombre, id, password, fechaNacimiento, correo,
                        puntuacion, direccion));
            } else if (tipo == 3) {
                // Coleccionista
                int puntuacion = leerEntero("Puntuación inicial: ");
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                System.out.println(GestorColeccionista.registrarColeccionista(nombre, id, password, fechaNacimiento,
                        correo, puntuacion, direccion));
            }
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    public static void listarUsuarios() throws Exception {
        System.out.println("\n--- Listado de Usuarios ---");
        GestorModerador.listarModeradores();
        GestorVendedor.listarVendedores();
        java.util.ArrayList<Coleccionista> coleccionistas = GestorColeccionista.listarColeccionistas();
        if (coleccionistas.isEmpty()) {
            System.out.println("\n***No hay coleccionistas***");
        } else {
            for (Coleccionista c : coleccionistas) {
                System.out.println(c);
            }
        }
    }

    public static boolean existeModerador() throws Exception {
        return GestorModerador.existeModerador();
    }

    public static void crearObjeto() {
        try {
            System.out.print("Nombre del objeto: ");
            String nombre = scanner.nextLine();
            System.out.print("Descripción del objeto: ");
            String descripcion = scanner.nextLine();
            System.out.println(GestorObjeto.crearObjeto(nombre, descripcion));
        } catch (Exception e) {
            System.out.println("Error al crear objeto: " + e.getMessage());
        }
    }

    public static void listarObjetos() {
        try {
            System.out.println("\n--- Listado de Objetos ---");
            java.util.ArrayList<cr.ac.ucenfotec.bl.entities.Objeto> objetos = GestorObjeto.listarObjetos();
            if (objetos.isEmpty()) {
                System.out.println("No hay objetos registrados.");
            } else {
                for (cr.ac.ucenfotec.bl.entities.Objeto o : objetos) {
                    System.out.println(o.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar objetos: " + e.getMessage());
        }
    }

    public static void actualizarEstadoObjeto() {
        try {
            System.out.println("\n--- Actualizar Estado de Objeto ---");
            listarObjetos();
            int id = leerEntero("Ingrese el ID del objeto a actualizar: ");
            System.out.print("Ingrese el nuevo estado (ej. Vendido, Retirado, Adjudicado): ");
            String estado = scanner.nextLine();
            System.out.println(GestorObjeto.actualizarEstadoObjeto(id, estado));
        } catch (Exception e) {
            System.out.println("Error al actualizar estado del objeto: " + e.getMessage());
        }
    }

    public static void crearSubasta() {
        try {
            System.out.println("\n--- Crear Subasta ---");
            java.util.ArrayList<cr.ac.ucenfotec.bl.entities.usuarios.Usuario> usuarios = GestorUsuarios.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println(i + " - " + usuarios.get(i).getNombre() + " (" + usuarios.get(i).getClass().getSimpleName() + ")");
            }
            int indice = leerEntero("Seleccione el creador de esta subasta: ");
            if (indice < 0 || indice >= usuarios.size()) {
                System.out.println("Índice inválido.");
                return;
            }
            cr.ac.ucenfotec.bl.entities.usuarios.Usuario creador = usuarios.get(indice);

            if (!(creador instanceof cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista) &&
                !(creador instanceof cr.ac.ucenfotec.bl.entities.usuarios.Vendedor)) {
                System.out.println("Solo los vendedores o coleccionistas pueden crear subastas.");
                return;
            }

            double precioMin = leerEntero("Precio mínimo: ");

            java.util.ArrayList<cr.ac.ucenfotec.bl.entities.Objeto> todosLosObjetos = GestorObjeto.listarObjetos();
            if (todosLosObjetos.isEmpty()) {
                System.out.println("No hay objetos registrados en el sistema. Debe crear objetos primero.");
                return;
            }

            System.out.println("Objetos disponibles:");
            for (cr.ac.ucenfotec.bl.entities.Objeto obj : todosLosObjetos) {
                System.out.println("ID: " + obj.getId() + " - " + obj.getNombre());
            }

            System.out.print("Ingrese los IDs de los objetos a subastar separados por coma (ej. 1, 2, 3): ");
            String idsInput = scanner.nextLine();
            String[] idsArray = idsInput.split(",");
            java.util.ArrayList<cr.ac.ucenfotec.bl.entities.Objeto> objetosSeleccionados = new java.util.ArrayList<>();
            for (String idStr : idsArray) {
                int objId = Integer.parseInt(idStr.trim());
                for (cr.ac.ucenfotec.bl.entities.Objeto obj : todosLosObjetos) {
                    if (obj.getId() == objId) {
                        objetosSeleccionados.add(obj);
                        break;
                    }
                }
            }

            if (objetosSeleccionados.isEmpty()) {
                System.out.println("No se seleccionaron objetos válidos.");
                return;
            }

            if (creador instanceof cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista) {
                System.out.println(cr.ac.ucenfotec.bl.logic.GestorSubastas.crearSubasta((cr.ac.ucenfotec.bl.entities.usuarios.Coleccionista) creador, precioMin, objetosSeleccionados));
            } else {
                System.out.println(cr.ac.ucenfotec.bl.logic.GestorSubastas.crearSubasta((cr.ac.ucenfotec.bl.entities.usuarios.Vendedor) creador, precioMin, objetosSeleccionados));
            }

        } catch (Exception e) {
            System.out.println("Error al crear subasta: " + e.getMessage());
        }
    }

    public static void listarSubastas() {
        try {
            System.out.println("\n--- Listado de Subastas ---");
            java.util.ArrayList<cr.ac.ucenfotec.bl.entities.Subasta> subastas = cr.ac.ucenfotec.bl.logic.GestorSubastas.listarSubastas();
            if (subastas.isEmpty()) {
                System.out.println("No hay subastas registradas.");
                return;
            }
            for (cr.ac.ucenfotec.bl.entities.Subasta s : subastas) {
                System.out.println(s.toString());
            }
        } catch (Exception e) {
            System.out.println("Error al listar subastas: " + e.getMessage());
        }
    }

    public static void actualizarEstadoSubasta() {
        try {
            System.out.println("\n--- Actualizar Estado de Subasta ---");
            listarSubastas();
            int id = leerEntero("Ingrese el ID de la subasta a actualizar: ");
            System.out.println("Seleccione el nuevo estado:");
            System.out.println("1. VIGENTE");
            System.out.println("2. CERRADA");
            int opcion = leerEntero("Opción: ");
            boolean vigente = (opcion == 1);
            System.out.println(cr.ac.ucenfotec.bl.logic.GestorSubastas.actualizarEstadoSubasta(id, vigente));
        } catch (Exception e) {
            System.out.println("Error al actualizar estado de la subasta: " + e.getMessage());
        }
    }

    public static void crearOferta() {
        try {
            System.out.println("\n--- Crear Oferta ---");

            ArrayList<Coleccionista> coleccionistas = GestorColeccionista.listarColeccionistas();

            if (coleccionistas.isEmpty()) {
                System.out.println("No hay coleccionistas registrados.");
                return;
            }

            System.out.println("Seleccione oferente:");
            for (int i = 0; i < coleccionistas.size(); i++) {
                System.out.println(i + " - " + coleccionistas.get(i).getNombre());
            }

            int indiceColeccionista = leerEntero("Opción: ");
            if (indiceColeccionista < 0 || indiceColeccionista >= coleccionistas.size()) {
                System.out.println("Opción inválida.");
                return;
            }
            Coleccionista oferente = coleccionistas.get(indiceColeccionista);

            ArrayList<Subasta> subastas = GestorSubastas.listarSubastas();

            if (subastas.isEmpty()) {
                System.out.println("No hay subastas disponibles.");
                return;
            }

            System.out.println("Seleccione subasta:");
            for (int i = 0; i < subastas.size(); i++) {
                System.out.println(i + " - " + subastas.get(i).toString());
            }

            int indiceSubasta = leerEntero("Opción: ");
            if (indiceSubasta < 0 || indiceSubasta >= subastas.size()) {
                System.out.println("Opción inválida.");
                return;
            }
            Subasta subasta = subastas.get(indiceSubasta);

            double monto = leerDouble("Monto de la oferta: ");

            System.out.println(GestorOferta.realizarOferta(oferente, subasta, monto));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarOfertas() {
        System.out.println("\n--- Listado de Ofertas ---");
        try {
            ArrayList<Subasta> subastas = GestorSubastas.listarSubastas();

            if (subastas.isEmpty()) {
                System.out.println("No hay subastas registradas.");
                return;
            }

            boolean hayOfertas = false;

            for (Subasta s : subastas) {
                ArrayList<Oferta> ofertasDeSubasta = GestorOferta.listarOfertasPorSubasta(s.getId());
                if (!ofertasDeSubasta.isEmpty()) {
                    System.out.println("\nSubasta: " + s.toString());

                    for (Oferta o : ofertasDeSubasta) {
                        System.out.println(o.toString());
                    }

                    hayOfertas = true;
                }
            }

            if (!hayOfertas) {
                System.out.println("No hay ofertas registradas.");
            }
        } catch (Exception e) {
            System.out.println("Error al listar ofertas: " + e.getMessage());
        }
    }
}
