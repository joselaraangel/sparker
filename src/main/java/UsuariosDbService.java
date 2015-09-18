import java.util.ArrayList;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 16/09/2015
 */
public interface UsuariosDbService<A> {
    public Boolean create(A entity);
    public A readOne(int id);
    public ArrayList<A> readAll();
    public Boolean update(int id, String nombre, String usuario, String pass);
    public Boolean delete(int id);
}
