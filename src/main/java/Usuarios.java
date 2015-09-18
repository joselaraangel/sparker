
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 13/09/2015
 */

// Se definen los atributos de los usuarios
public class Usuarios {
    
      private String nombre;
    private String pass;
    private Date createdAt;
    private String usuario;
    private Integer id;
    private Boolean deleted;

    public Usuarios (String nombre, String usuario, String pass, Integer size) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.createdAt = new Date();
        this.id = size;
        this.deleted = false;
    }

    public Usuarios (String nombre, String usuario, String pass, Integer id, Date createdAt, Boolean deleted) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.createdAt = createdAt;
        this.id = id;
        this.deleted = deleted;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void delete() {
        this.deleted = true;
    }

    public Boolean readable() {
        return !this.deleted;
    }

    public String getCreatedAt() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(this.createdAt);
    }

    public String getEditLink() {
        return "<a href='/article/update/" + this.id + "'>Edit</a>";
    }

    public String getDeleteLink() {
        return "<a href='/article/delete/" + this.id + "'>Delete</a>";
    }

    public String getSummaryLink() {
        return "<a href='/article/read/" + this.id + "'>" + this.usuario + "</a>";
    }
    
}
