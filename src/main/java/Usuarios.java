/**
 *
 * @author José Angel Lara Aguirre
 * @since 13/09/2015
 */
public class Usuarios {
    
    private String nombre;
    private String user;
    private String pass;
    private Integer id;

    public Usuarios(String nombre, String user, String pass) {
        this.nombre = nombre;
        this.user = user;
        this.pass = pass;
    }

    public Usuarios(String nombre, String user, String pass, Integer id) {
       this.nombre = nombre;
        this.user = user;
        this.pass = pass;
        this.id = id;
    }

    public String getNomb() {
        return nombre;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setNomb(String nombre) {
        this.nombre = nombre;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public String getProfile() {
        return "<a href='/user/" + this.id + "'>" + this.nombre + "</a>";
    }
    
}
