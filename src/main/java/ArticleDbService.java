import java.util.ArrayList;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 13/09/2015
 */

public interface ArticleDbService<T> {
    public Boolean create(T entity);
    public T readOne(int id);
    public ArrayList<T> readAll();
    public Boolean update(int id, String title, String summary, String content);
    public Boolean delete(int id);
}
