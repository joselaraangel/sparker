import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 13/09/2015
 */
//Conexion con la base de datos de usuarios
public class UsuariosPostgresDao <A extends Usuarios>  implements UsuariosDbService<A> { 
    //clase que contiene la conexion necesaria para postgre sql

    // PostgreSQL connection to the database
    private Connection conn;
    // A raw SQL query used without parameters
    private Statement stmt;

    public UsuariosPostgresDao() {
        // The account names setup from the command line interface
        String user = "postgres";
        String passwd = "medico";
        String dbName = "sparkledb";
        // DB connection on localhost via JDBC
        String uri = "jdbc:postgresql://localhost/" + dbName;

        // Standard SQL CREATE TABLE query
        // The primary key is not auto incremented
        String createTableQuery =
                "CREATE TABLE IF NOT EXISTS clientes(" +
                        "id         INT         PRIMARY KEY NOT NULL," +
                        "title      VARCHAR(120) NOT NULL," +
                        "content    VARCHAR(620)NOT NULL," +
                        "summary    VARCHAR(220) NOT NULL," +
                        "deleted    BOOLEAN     DEFAULT FALSE," +
                        "createdAt  DATE        NULL" +
                        ");"
                ;

        // Create the article table within sparkledb and close resources if an exception is thrown
        try {
            conn = DriverManager.getConnection(uri, user, passwd);
            stmt = conn.createStatement();
            stmt.execute(createTableQuery);

            System.out.println("database table clientes created");
        } catch(Exception e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    

    @Override
    public Boolean create(A entity) {
        try {
            String insertQuery = "INSERT INTO clientes(id, title, content, summary, createdAt) VALUES(?, ?, ?, ?, ?);";

            // Prepared statements allow us to avoid SQL injection attacks
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

            // JDBC binds every prepared statement argument to a Java Class such as Integer and or String
            pstmt.setInt(1, entity.getId());
            pstmt.setString(2, entity.getTitle());
            pstmt.setString(3, entity.getContent());
            pstmt.setString(4, entity.getSummary());

            java.sql.Date sqlNow = new Date(new java.util.Date().getTime());
            pstmt.setDate(5, sqlNow);

            pstmt.executeUpdate();

            // Unless closed prepared statement connections will linger
            // Not very important for a trivial app but it will burn you in a professional large codebase
            pstmt.close();

            return true;
        } 
        
        
        
        catch (SQLException e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            return false;
        }
    }

 
    
    @Override
    @SuppressWarnings("unchecked")
    public A readOne(int id) {
        try {
            String selectQuery = "SELECT * FROM clientes where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, id);

            pstmt.executeQuery();

            // A ResultSet is Class which represents a table returned by a SQL query
            ResultSet resultSet = pstmt.getResultSet();

            if(resultSet.next()) {
                Usuarios entity = new Usuarios(
                        // You must know both the column name and the type to extract the row
                        resultSet.getString("title"),
                        resultSet.getString("summary"),
                        resultSet.getString("content"),
                        resultSet.getInt("id"),
                        resultSet.getDate("createdat"),
                        resultSet.getBoolean("deleted")
                );

                pstmt.close();

                return (A) entity;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return null;
    }


    @Override
    @SuppressWarnings("unchecked") //Tells the compiler to ignore unchecked type casts
    public ArrayList<A> readAll() {
        // Type cast the generic T into an Article
        ArrayList<Usuarios> results = (ArrayList<Usuarios>) new ArrayList<A>();

        try {
            String query = "SELECT * FROM clientes;";

            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while(resultSet.next()) {
                Usuarios entity = new Usuarios(
                        resultSet.getString("title"),
                        resultSet.getString("summary"),
                        resultSet.getString("content"),
                        resultSet.getInt("id"),
                        resultSet.getDate("createdat"),
                        resultSet.getBoolean("deleted")
                );

                results.add(entity);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        // The interface ArticleDbService relies upon the generic type T so we cast it back
        return (ArrayList<A>) results;
    }

    @Override
    public Boolean update(int id, String title, String summary, String content) {
        try {
            String updateQuery =
                "UPDATE clientes SET title = ?, summary = ?, content = ?" +
                        "WHERE id = ?;"
                ;

            PreparedStatement pstmt = conn.prepareStatement(updateQuery);

            pstmt.setString(1, title);
            pstmt.setString(2, summary);
            pstmt.setString(3, content);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public Boolean delete(int id) {
        try {
            String deleteQuery = "DELETE FROM clientes WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return true;
        
    }
}