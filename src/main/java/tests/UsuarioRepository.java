package tests;

public interface UsuarioRepository {
    void create(Usuario usuario);
    Usuario read(String email);
    void update(Usuario usuario);
    void delete(String email);
}
