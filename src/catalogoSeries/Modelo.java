package catalogoSeries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/catalogoSeries";
	String login = "userSeries"; // Usuario
	String password = "Studium2023;"; // Clave de acceso
	Connection connection = null;

	public Modelo() {
		connection = this.conexion();
	}

	//Método para establecer una conexión a la base de datos
	private Connection conexion() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	//Método para desconectar la base de datos
	public void desconectar() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
		}
	}

	//Método para obtener los datos de los registros de cada serie
	public String mostrarSerieDatos(int id) {
		try {
			//Consulta SQL para obtener los datos de una serie por su ID
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM series WHERE idSerie = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				//Obtener los datos de la serie desde el resultado de la consulta
				String titulo = resultSet.getString("titulo");
				String director = resultSet.getString("director");
				String actores = resultSet.getString("actores");
				String genero = resultSet.getString("genero");
				int temporadas = resultSet.getInt("temporadas");
				int anyo = resultSet.getInt("anyo");

				//Formateo para los datos de la serie y mostrarlos en el text area
				String serieTexto = "Título: " + titulo + "\nDirector: " + director + "\nActores: " + actores
						+ "\nGénero: " + genero + "\nTemporadas: " + temporadas + "\nAño: " + anyo;
				return serieTexto;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		//Devuelve nulo si no se encontraron datos de la serie
		return null;
	}

	//Método para obtener la ruta de la imagen desde la base de datos
	public String obtenerRutaImagen(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT imagen FROM series WHERE idSerie = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				//Obtener la ruta de la imagen desde el resultado de la consulta
				return resultSet.getString("imagen");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		//Devuelve nulo si no se encontró la ruta de la imagen
		return null;
	}
}
