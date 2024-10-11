package tests;
import java.sql.*;



public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final Connection connection;

    public UsuarioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO usuarios (email, password) VALUES (?, ?)")) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario read(String email) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM usuarios WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Usuario usuario) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE usuarios SET password = ? WHERE email = ?")) {
            stmt.setString(1, usuario.getPassword());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM usuarios WHERE email = ?")) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}