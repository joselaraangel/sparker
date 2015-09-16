import java.util.ArrayList;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 16/09/2015
 */
public interface UsuariosDbService<A> {
    public Boolean create(A entity);
    public A readOne(int id);

    
}
