package cr.ac.ucenfotec.dl;

import java.sql.*;

public class AccesoBD {
    private Connection conexion = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement;

    public AccesoBD(String direccion, String usuario, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, password);
    }

    public void ejecutarStatement(String statement) throws SQLException {
        this.statement = conexion.createStatement();
        this.statement.executeUpdate(statement);
    }

    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}
