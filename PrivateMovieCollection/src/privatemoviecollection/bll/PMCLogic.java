package privatemoviecollection.bll;

import java.sql.SQLException;
import java.util.List;
<<<<<<< HEAD
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import privatemoviecollection.be.CatMovie;
=======
>>>>>>> parent of 145f406... Add files via upload
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
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
<<<<<<< HEAD
    
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
    
    public void alertBox(){
        Stage abWindow = new Stage();
        
        abWindow.initModality(Modality.APPLICATION_MODAL);
        abWindow.setTitle("AlertBox");
        abWindow.setMinWidth(550);
        abWindow.setMinHeight(300);
        
        Label abLbl = new Label();
        abLbl.setFont(Font.font(null, FontWeight.BOLD, 16));
        abLbl.setText("Remember to delete movies, "+
           "that have a personal rating below 6\nand "+
             "have not been watched in over 2 years.");
        Button closeBtn = new Button("Close the window");
        closeBtn.setOnAction(e -> abWindow.close());
        
        VBox abLayout = new VBox(10);
        abLayout.getChildren().addAll(abLbl, closeBtn);
        abLayout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(abLayout);
        abWindow.setScene(scene);
        abWindow.showAndWait();
    }
=======
>>>>>>> parent of 145f406... Add files via upload
}