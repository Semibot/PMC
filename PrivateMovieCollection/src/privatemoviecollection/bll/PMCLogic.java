package privatemoviecollection.bll;

import java.sql.SQLException;
import java.util.List;
import privatemoviecollection.be.CatMovie;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.CatMovieDAO;
import privatemoviecollection.dal.CategoryDAO;
import privatemoviecollection.dal.MovieDAO;

/**
 *
 * @author DKE
 */
public class PMCLogic{
    
    public Movie createMovie(Movie m) throws SQLException{
        MovieDAO mdao = new MovieDAO();
        return mdao.createMovie(0, m);
    }
    
    public Movie getMovie(int id){
        MovieDAO mdao = new MovieDAO();
        return mdao.getMovie(id);
    }
    
    public void updateMovie(Movie m) throws SQLException{
        MovieDAO mdao = new MovieDAO();
        mdao.updateMovie(m);
    }
    
    public void deleteMovie(Movie m) throws SQLException{
        MovieDAO mdao = new MovieDAO();
        mdao.deleteMovie(m);
    }
    
    public List<Movie> getAllMovies(){
        MovieDAO mdao = new MovieDAO();
        return mdao.getAllMovies();
    }
    
    public Category createCategory(String name) throws SQLException{
        CategoryDAO cdao = new CategoryDAO();
        return cdao.createCategory(0, name);
    }
    
    public Category getCategory(int id){
        CategoryDAO cdao = new CategoryDAO();
        return cdao.getCategory(id);
    }
    
    public void updateCategory(Category c) throws SQLException{
        CategoryDAO cdao = new CategoryDAO();
        cdao.updateCategory(c);
    }
    
    public void deleteCategory(Category c) throws SQLException{
        CategoryDAO cdao = new CategoryDAO();
        cdao.deleteCategory(c);
    }
    
    public List<Category> getAllCategories(){
        CategoryDAO cdao = new CategoryDAO();
        return cdao.getAllCategories();
    }
    
    public CatMovie createCatMovie(CatMovie cm) throws SQLException{
        CatMovieDAO cmdao = new CatMovieDAO();
        return cmdao.createCatMovie(0, cm);
    }
    
    public CatMovie getCatMovie(int id){
        CatMovieDAO cmdao = new CatMovieDAO();
        return cmdao.getCatMovie(id);
    }
    
    public void updateCatMovie(CatMovie cm) throws SQLException{
        CatMovieDAO cmdao = new CatMovieDAO();
        cmdao.updateCatMovie(cm);
    }
    
    public void deleteCatMovie(CatMovie cm) throws SQLException{
        CatMovieDAO cmdao = new CatMovieDAO();
        cmdao.deleteCatMovie(cm);
    }
    
    public List<CatMovie> getAllCatMovies(){
        CatMovieDAO cmdao = new CatMovieDAO();
        return cmdao.getAllCatMovies();
    }
}