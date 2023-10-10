package catalogoSeries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/catalogoSeries";
    String login = "userSeries"; //Usuario
    String password = "Studium2023;"; //Clave de acceso
    //String sentencia = "";
    Connection connection = null;//Objeto de conexion a BD

    public Modelo() {
        connection = this.conexion();
    }

    //Método para conectar con la base datos
    private Connection conexion() {
        try {
            // Cargar los controladores para el acceso a la BD
            Class.forName(driver);
            // Establecer la conexión con la BD
            return DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
  //Método para desconectar con la base datos
    public void desconectar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {}
    }

    //Método para sacar datos de la BD, se pasa por parámetros el id para sacar uno a uno los registros
    public String mostrarSerieDatos(int id) {
        try {
        	//Hacemos consulta a la base de datos segun el ID para diferenciar entre series
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM series WHERE idSerie = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            //Creamos variables para guardar datos uno a uno
            if (resultSet.next()) {
            	//Guardamos los datos de la BD
                String titulo = resultSet.getString("titulo");
                String director = resultSet.getString("director");
                String actores = resultSet.getString("actores");
                String genero = resultSet.getString("genero");
                int temporadas = resultSet.getInt("temporadas");
                int anyo = resultSet.getInt("anyo");

                //Crear un formato de texto para mostrar la información en un TextArea
                String serieTexto = "Título: " + titulo + "\nDirector: " + director + "\nActores: " + actores
                        + "\nGénero: " + genero + "\nTemporadas: " + temporadas + "\nAño: " + anyo;
                return serieTexto;//Devuelve el texto formateado para el text area
            }
        } catch (SQLException ex) {//Controla errores
            ex.printStackTrace();
        }
        return null;//Devuelve nulo sino se encontraron los datos
    }

    
	/*public String obtenerPortadaSerie1() {
		// TODO Auto-generated method stub
		return "img/1.jpg";
	}*/
}
