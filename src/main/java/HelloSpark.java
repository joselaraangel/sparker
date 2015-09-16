import static spark.Spark.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerRoute;

import java.util.*;

/**
 *
 * @author José Angel Lara Aguirre
 * @since 13/09/2015
 */

public class HelloSpark {
    
    //En esta linea llamamos a la clase conexion si no cuenta con una base de datos sustituya:
    //ArticlePostgresDao por ArticleServletDao
    
    public static ArticleDbService<Article> articleDbService = new ArticlePostgresDao<Article>();
    public static UsuariosDbService<Usuarios> usuariosDbService = new UsuariosPostgresDao<Usuarios>();
    
    

    public static void main(String[] args) {
         
        
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap<String, Object>();
                ArrayList<Article> articles = articleDbService.readAll();

                if (articles.isEmpty()) {
                    viewObjects.put("hasNoArticles", "Bienvenido parece que no hay nada, por favor\"publica algo\" para comenzar.");
                } else {
                    Deque<Article> showArticles = new ArrayDeque<Article>();

                    for (Article article : articles) {
                        if (article.readable()) {
                            showArticles.addFirst(article);
                        }
                    }

                    viewObjects.put("articles", showArticles);
                }

                viewObjects.put("templateName", "articleList.ftl");

                return modelAndView(viewObjects, "layout.ftl");
            }
        });
        
        //En esta parte se cargan las plantillas ftl y se les asigna una ruta

        get(new FreeMarkerRoute("/article/create") {
            @Override
            public Object handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap<String, Object>();

                viewObjects.put("templateName", "articleForm.ftl");

                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        post(new Route("/article/create") {
            @Override
            public Object handle(Request request, Response response) {
                String title = request.queryParams("article-title");
                String summary = request.queryParams("article-summary");
                String content = request.queryParams("article-content");

                Article article = new Article(title, summary, content, articleDbService.readAll().size());

                articleDbService.create(article);

                response.status(201);
                response.redirect("/");
                return "";
            }
        });

        get(new FreeMarkerRoute("/article/read/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                Map<String, Object> viewObjects = new HashMap<String, Object>();

                viewObjects.put("templateName", "articleRead.ftl");

                viewObjects.put("article", articleDbService.readOne(id));

                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/article/update/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                Map<String, Object> viewObjects = new HashMap<String, Object>();

                viewObjects.put("templateName", "articleForm.ftl");

                viewObjects.put("article", articleDbService.readOne(id));

                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        post(new Route("/article/update/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id      = Integer.parseInt(request.queryParams("article-id"));
                String title    = request.queryParams("article-title");
                String summary  = request.queryParams("article-summary");
                String content  = request.queryParams("article-content");

                articleDbService.update(id, title, summary, content);

                response.status(200);
                response.redirect("/");
                return "";
            }
        });

        get(new Route("/article/delete/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));

                articleDbService.delete(id);

                response.status(200);
                response.redirect("/");
                return "";
            }
        });
        
        get(new FreeMarkerRoute("/registro") {
            @Override
            public Object handle(Request request, Response response) {
                
                Map<String, Object> viewObjects = new HashMap<String, Object>();

                viewObjects.put("templateName", "registro.ftl");

                return modelAndView(viewObjects, "layout.ftl");
            }
        });
        
        get(new FreeMarkerRoute("/user/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                Map<String, Object> viewObjects = new HashMap<String, Object>();

                viewObjects.put("templateName", "profile.ftl");

                viewObjects.put("clientes", usuariosDbService.readOne(id));

                return modelAndView(viewObjects, "layout.ftl");
            }
        });
    }
}