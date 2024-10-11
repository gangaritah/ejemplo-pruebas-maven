package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class UsuarioRepositoryImplMockTest {

	private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        usuarioRepository = new UsuarioRepositoryImpl(mockConnection);
    }

    @Test
    public void testCreateUsuario() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        Usuario usuario = new Usuario("test@.com", "password123");
        usuarioRepository.create(usuario);

        verify(mockConnection).prepareStatement("INSERT INTO usuarios (email, password) VALUES (?, ?)");
        verify(mockStatement).setString(1, "test@gmail.com");
        verify(mockStatement).setString(2, "password123");
        verify(mockStatement).executeUpdate();
    }

    @Test
    public void testReadUsuario() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("email")).thenReturn("test@gmail.com");
        when(mockResultSet.getString("password")).thenReturn("password123");

        Usuario usuario = usuarioRepository.read("test@gmail.com");

        verify(mockConnection).prepareStatement("SELECT * FROM usuarios WHERE email = ?");
        verify(mockStatement).setString(1, "test@gmail.com");
        verify(mockStatement).executeQuery();

        assertNotNull(usuario);
        assertEquals("test@gmail.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
    }

    @Test
    public void testUpdateUsuario() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        Usuario usuario = new Usuario("test@gmail.com", "newpassword");
        usuarioRepository.update(usuario);

        verify(mockConnection).prepareStatement("UPDATE usuarios SET password = ? WHERE email = ?");
        verify(mockStatement).setString(1, "newpassword");
        verify(mockStatement).setString(2, "test@gmail.com");
        verify(mockStatement).executeUpdate();
    }

    @Test
    public void testDeleteUsuario() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        usuarioRepository.delete("test@gmail.com");

        verify(mockConnection).prepareStatement("DELETE FROM usuarios WHERE email = ?");
        verify(mockStatement).setString(1, "test@gmail.com");
        verify(mockStatement).executeUpdate();
    }

}
