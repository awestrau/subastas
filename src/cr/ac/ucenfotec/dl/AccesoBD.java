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

    public int ejecutarStatementConRetornoId(String query) throws SQLException {
        this.statement = conexion.createStatement();
        this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = this.statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
}
