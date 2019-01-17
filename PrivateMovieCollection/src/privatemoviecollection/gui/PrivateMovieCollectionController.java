package privatemoviecollection.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import privatemoviecollection.be.CatMovie;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.PMCLogic;

/**
 *
 * @author DKE
 */
public class PrivateMovieCollectionController implements Initializable{
    private ObservableList listMovie = FXCollections.observableArrayList();
    private ObservableList listCategory = FXCollections.observableArrayList();
    private ObservableList listCatsInMovie = FXCollections.observableArrayList();
    @FXML
    private Button searchBtn;
    @FXML
    private Button arrowRightBtn;
    @FXML
    private Label infoLbl;
    @FXML
    private ListView<Movie> movies;
    @FXML
    private ListView<Category> categories;
    @FXML
    private ListView<String> categoriesInMovie;
    private PMCLogic bll;
    @FXML
    private TextField searchFld;
    private Movie selected = null;
    private Movie be;
    private PMCMediaController pmcmc;
    private MediaPlayer mp;
    
    public PrivateMovieCollectionController(){
        bll = new PMCLogic();
        be = new Movie();
        pmcmc = new PMCMediaController();
    }
    
    public ListView<String> getCategoriesInMovie(){
        return categoriesInMovie;
    }
    
    public Label getInfoLbl(){
        return infoLbl;
    }
    
    public ObservableList getListCatsInMovie(){
        return listCatsInMovie;
    }
    
    @FXML
    private void openMediaWindow(ActionEvent e) throws IOException{
        Stage newMediaWindow = new Stage();
        newMediaWindow.initModality(Modality.NONE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PMCMedia.fxml"));
        Parent root = loader.load();
        PMCMediaController mc = loader.getController();
        mc.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent doubleClicked){
                if(doubleClicked.getClickCount() == 2){
                    newMediaWindow.setFullScreen(true);
                }
            }
        });
        newMediaWindow.setTitle("Media Playing!");
        newMediaWindow.setScene(scene);
        newMediaWindow.showAndWait();
    }
    
    @FXML
    private void handleAddCategoryButton(ActionEvent e) throws IOException{
        Stage newCategoryWindow = new Stage();
        newCategoryWindow.initModality(Modality.NONE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewCategory.fxml"));
        Parent root = loader.load();
        NewCategoryController ncc = loader.getController();
        ncc.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        newCategoryWindow.setTitle("New Category");
        newCategoryWindow.setScene(scene);
        newCategoryWindow.showAndWait();
    }
    
    public void addCategory(String name){
        try {
            Category category = bll.createCategory(name);
            categories.getItems().clear();
            listCategory.addAll(category);
            categories.getItems().addAll(listCategory);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void categoryRightArrowBtn(ActionEvent e) throws SQLException{
        List<String> sList = new ArrayList<>();
        Category c = categories.getSelectionModel().getSelectedItem();
        categoriesInMovie.getItems().clear();
        
        if(selected != null){
            for(String s : sList){
                be.getCategoriesList().add(s);
                categoriesInMovie.getItems().add(s);
            }
        }
        
        listCatsInMovie.add(be.getCategoriesList());
        categoriesInMovie.getItems().addAll(listCatsInMovie);
        categoriesInMovie.getItems().add(c.getName());
//        be.getCategoriesList().add(c.getName());
        bll.createCatMovie(new CatMovie(1, c.getId(), be.getId()));
    }
    
    @FXML
    private void handleDeleteCategoryButton(ActionEvent e){
        try {
            Category selected =
                    categories.getSelectionModel().getSelectedItem();
            bll.deleteCategory(selected);
            categories.getItems().remove(selected);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handleAddEditMovieButton(ActionEvent e) throws IOException{
        openAddEditMovieWindow(e, null);
    }
    
    private void openAddEditMovieWindow(ActionEvent e, Movie selected) throws IOException{
        Stage addEditMovieWindow = new Stage();
        addEditMovieWindow.initModality(Modality.NONE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditMovie.fxml"));
        Parent root = loader.load();
        NewEditMovieController nmc = loader.getController();
        nmc.setParentWindowController(this);
        nmc.setMovieToEdit(selected);
        
        Scene scene = new Scene(root);
        addEditMovieWindow.setTitle("New / Edit Movie");
        addEditMovieWindow.setScene(scene);
        addEditMovieWindow.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            bll.alertBox();
            //Set image on buttons
            Path dir = FileSystems.getDefault().getPath("./src/images/Search-icon.png");
            Image image = new Image(dir.toUri().toURL().toExternalForm());
            searchBtn.setGraphic(new ImageView(image));
            
            Path dir1 = FileSystems.getDefault().getPath("./src/images/Arrow-right.png");
            Image image1 = new Image(dir1.toUri().toURL().toExternalForm());
            arrowRightBtn.setGraphic(new ImageView(image1));
            
            //Add movie
            String a = "Title \t\t\t\t\t PersRating \t   ImdbRating \t\t\t Lastview";
            listMovie.add(a);
            List<Movie> listm = bll.getAllMovies();
            listMovie.addAll(listm);
            movies.getItems().addAll(listMovie);
            movies.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>(){
                @Override
                public void changed(ObservableValue<? extends Movie> observable, Movie oldValue, Movie newValue){
                    be = newValue;
                    categoriesInMovie.getItems().clear();
                    categoriesInMovie.getItems().addAll(be.getCategoriesList());
                }
            });
            movies.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent doubleClicked){
                        if(doubleClicked.getClickCount() == 2){
                            mp.getMedia();
                        }
                    }
            });
            
            //Add category
            String b = "All categories in PMC";
            listCategory.add(b);
            List<Category> listc = bll.getAllCategories();
            listCategory.addAll(listc);
            categories.getItems().addAll(listCategory);
            
            //Add categories to a movie
            String c = "Categories in movie";
            listCatsInMovie.add(c);
            List<String> list = be.getCategoriesList();
            listCatsInMovie.add(list);
            categoriesInMovie.getItems().addAll(listCatsInMovie);
            
        }catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }
    
    public void addMovie(Movie m){
        try {
            Movie movie = bll.createMovie(m);
            movies.getItems().clear();
            listMovie.addAll(movie);
            movies.getItems().addAll(listMovie);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deleteMovie(ActionEvent e){
        try {
            Movie selected =
                    movies.getSelectionModel().getSelectedItem();
            bll.deleteMovie(selected);
            movies.getItems().remove(selected);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void editMovie(ActionEvent e) throws IOException{
        Movie selected =
                movies.getSelectionModel().getSelectedItem();
        openAddEditMovieWindow(e, selected);
    }
    
    @FXML
    private void findInSearch(ActionEvent e){
        List<Movie> mov = bll.getAllMovies();
        List<Category> cat = bll.getAllCategories();
        movies.getItems().clear();
        categories.getItems().clear();
        mov.sort(Comparator.comparing(Movie::getName));
        
        for(Movie movie : mov){
            if(movie.getName().contains(searchFld.getText())){
                movies.getItems().add(movie);
            }
        }
        
        for(Category category : cat){
            if(category.getName().contains(searchFld.getText())){
                categories.getItems().add(category);
            }
        }
    }
}