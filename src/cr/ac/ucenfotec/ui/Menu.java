package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.GestorSubastas;
import cr.ac.ucenfotec.bl.GestorUsuarios;
import cr.ac.ucenfotec.bl.Oferta;
import cr.ac.ucenfotec.bl.Subastas;
import cr.ac.ucenfotec.bl.usuarios.Coleccionista;
import cr.ac.ucenfotec.bl.usuarios.Usuario;
import cr.ac.ucenfotec.bl.usuarios.Vendedor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private GestorUsuarios gestor;
    private Scanner scanner;
    private GestorSubastas gestorSubastas;

    public Menu() {
        gestor = new GestorUsuarios();
        gestorSubastas = new GestorSubastas();
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
        System.out.println("\n--- Menú Principal ---" +
            "\n1. Registro de usuarios" +
            "\n2. Listado de usuarios" +
            "\n3. Creación de subastas" +
            "\n4. Listado de subastas" +
            "\n5. Creación de ofertas" +
            "\n6. Listado de ofertas" +
            "\n7. Salir");
    }

    private void procesarOpcion(int opcion){
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

    private void registrarUsuario(int tipoForzado) {
        try {
            int tipo = tipoForzado;
            if (tipo == 0) {
                System.out.println("\n--- Registro de usuario ---" +
                    "\n1. Vendedor" +
                    "\n2. Coleccionista");
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

    private void crearSubasta(){
        try{

            System.out.println("\n--- Crear Subasta ---");

            ArrayList<Usuario> usuarios = gestor.listarUsuarios();

            for(int i=0;i<usuarios.size();i++){
                System.out.println(i + " - " + usuarios.get(i).getNombre());
            }

            int indice = leerEntero("Seleccione el creador de esta subasta: ");
            Usuario creador = usuarios.get(indice);

            // VALIDACIÓN DEL TIPO DE USUARIO
            if(!(creador instanceof Coleccionista) && !(creador instanceof Vendedor)){
                System.out.println("Solo los vendedores o coleccionistas pueden crear subastas.");
                return;
            }

            double precioMin = leerEntero("Precio mínimo: ");

            int cantidad = leerEntero("Cantidad de objetos: ");


            ArrayList<String> nombres = new ArrayList<>();
            ArrayList<String> descripciones = new ArrayList<>();

            for(int i=0;i<cantidad;i++){

                System.out.print("Nombre objeto: ");
                nombres.add(scanner.nextLine());

                System.out.print("Descripción: ");
                descripciones.add(scanner.nextLine());
            }

            if(creador instanceof Coleccionista){
                gestorSubastas.crearSubasta(
                        (Coleccionista) creador,
                        precioMin,
                        nombres,
                        descripciones
                );

            }else {
                gestorSubastas.crearSubasta(
                        (Vendedor) creador,
                        precioMin,
                        nombres,
                        descripciones
                );
            }

            System.out.println("Subasta creada correctamente.");

        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarSubastas(){
        System.out.println("\n--- Listado de Subastas ---");

        ArrayList<Subastas> subastas = gestorSubastas.listarSubastas();

        if(subastas.isEmpty()){
            System.out.println("No hay subastas registradas.");
            return;
        }

        for(int i = 0; i < subastas.size(); i++){
            System.out.println(i + " - " + subastas.get(i).toString());
        }
    }

    private void crearOferta(){
            try{
                System.out.println("\n--- Crear Oferta ---");

                ArrayList<Usuario> usuarios = gestor.listarUsuarios();
                ArrayList<Coleccionista> coleccionistas = new ArrayList<>();

                for(Usuario u : usuarios){
                    if(u instanceof Coleccionista){
                        coleccionistas.add((Coleccionista) u);
                    }
                }

                if(coleccionistas.isEmpty()){
                    System.out.println("No hay coleccionistas registrados.");
                    return;
                }

                System.out.println("Seleccione oferente:");

                for(int i = 0; i < coleccionistas.size(); i++){
                    System.out.println(i + " - " + coleccionistas.get(i).getNombre());
                }

                int indiceColeccionista = leerEntero("Opción: ");
                Coleccionista oferente = coleccionistas.get(indiceColeccionista);

                ArrayList<Subastas> subastas = gestorSubastas.listarSubastas();

                if(subastas.isEmpty()){
                    System.out.println("No hay subastas disponibles.");
                    return;
                }

                System.out.println("Seleccione subasta:");

                for(int i = 0; i < subastas.size(); i++){
                    System.out.println(i + " - " + subastas.get(i));
                }

                int indiceSubasta = leerEntero("Opción: ");
                Subastas subasta = subastas.get(indiceSubasta);

                double monto = leerEntero("Monto de la oferta: ");

                gestorSubastas.realizaroferta(oferente, subasta, monto);

                System.out.println("Oferta registrada correctamente.");

            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
    }

    private void listarOferta(){
            System.out.println("\n--- Listado de Ofertas ---");

            ArrayList<Subastas> subastas = gestorSubastas.listarSubastas();

            if(subastas.isEmpty()){
                System.out.println("No hay subastas registradas.");
                return;
            }

            boolean hayOfertas = false;

            for(Subastas s : subastas){

                if(!s.getListaOfertas().isEmpty()){

                    System.out.println("\nSubasta: " + s.toString());

                    for(Oferta o : s.getListaOfertas()){
                        System.out.println(o.toString());
                    }

                    hayOfertas = true;
                }
            }

            if(!hayOfertas){
                System.out.println("No hay ofertas registradas.");
            }
    }

}
