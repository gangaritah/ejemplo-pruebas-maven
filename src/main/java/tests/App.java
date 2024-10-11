package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
	private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_URL =  dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    public static void main(String[] args) {
    	
        try (Connection connection = DriverManager.getConnection(
        		DB_URL, DB_USER, DB_PASSWORD)) {

            // Crear el servicio con la conexión
            UsuarioService usuarioService = new UsuarioService(connection);

            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario("correo@example.com", "password123");
            usuarioService.createUsuario(nuevoUsuario);
            /*
            // Obtener un usuario por email
            Usuario usuarioObtenido = usuarioService.getUsuarioByEmail("correo@example.com");
            System.out.println("Usuario obtenido: " + usuarioObtenido.getEmail());

            // Actualizar el usuario
            usuarioObtenido.setPassword("nuevaPassword");
            usuarioService.updateUsuario(usuarioObtenido);

            // Eliminar el usuario
            usuarioService.deleteUsuario("correo@example.com");
			*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}