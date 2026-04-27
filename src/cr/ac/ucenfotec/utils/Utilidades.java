package cr.ac.ucenfotec.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utilidades {
    public static String[] getProperties() throws Exception{

        String[] propiedades = new String[5];
        Properties lectura = new Properties();
        String ruta = "src/cr/ac/ucenfotec/utils/bd.properties";

        try {
            lectura.load(new FileInputStream(ruta));
            propiedades[0] = lectura.getProperty("driver");
            propiedades[1] = lectura.getProperty("server");
            propiedades[2] = lectura.getProperty("dataBase");
            propiedades[3] = lectura.getProperty("user");
            propiedades[4] = lectura.getProperty("password");
            return propiedades;
        } catch(Exception e) {
            System.out.println("Se dio un error inesperado al cargar las credenciales de acceso a la base de datos.");
            throw e;
        }
    }
}
