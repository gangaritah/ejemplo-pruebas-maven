package tests;

import java.sql.Connection;



public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    public void createUsuario(Usuario usuario) {
        usuarioRepository.create(usuario);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.read(email);
    }

    public void updateUsuario(Usuario usuario) {
        usuarioRepository.update(usuario);
    }

    public void deleteUsuario(String email) {
        usuarioRepository.delete(email);
    }
}

