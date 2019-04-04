package privatemoviecollection.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.PMCLogic;

/**
 * FXML Controller class
 *
 * @author DKE
 */
public class NewEditMovieController implements Initializable{
    
    private PrivateMovieCollectionController parent;
    @FXML
    private TextField movieNameFld;
    @FXML
    private TextField moviePersonalRatingFld;
    @FXML
    private TextField movieImdbRatingFld;
    @FXML
    private TextField movieLastviewFld;
    @FXML
    private TextField movieFilelinkFld;
    @FXML
    private Button movieSaveBtn;
    @FXML
    private Button movieCancelBtn;
    @FXML
    private Button addToMovieBtn;
    private PMCLogic bll;
    private Movie be;
    @FXML
    private ComboBox<Category> genres;
    private Movie selected = null;
    
    public NewEditMovieController(){
        bll = new PMCLogic();
        be = new Movie();
    }
    
    public void setParentWindowController(PrivateMovieCollectionController parent){
        this.parent = parent;
    }
    
    private void createComboBoxItems(){
        ObservableList<Category> catList =
                FXCollections.observableArrayList(
                bll.getAllCategories());
        genres.getItems().addAll(catList);
        genres.setPromptText("Select a category");
    }
    
    @FXML
    private void addToMovieBtn(ActionEvent e){
        List<String> sList = new ArrayList<>();
        
        if(selected != null){
            for(String s : sList){
                be.getCategoriesList().add(s + genres.getValue());
            }
        }
        
        parent.getCategoriesInMovie().getItems().clear();
        parent.getListCatsInMovie().add(be.getCategoriesList());
        parent.getCategoriesInMovie().getItems().addAll(parent.getListCatsInMovie());
    }
    
    @FXML
    private void handleSaveMovieButton(ActionEvent e){
        if(!movieNameFld.getText().equals(bll.getAllMovies())){
            List<String> genresList = new ArrayList();
            genresList.add(genres.getValue().getName());
            Movie m = new Movie(0, movieNameFld.getText(),
                Integer.parseInt(moviePersonalRatingFld.getText()),
                Integer.parseInt(movieImdbRatingFld.getText()),
                //String.valueOf((List<String>)genres.getValue()),
                movieLastviewFld.getText(),
                movieFilelinkFld.getText(),
                genresList);
            parent.addMovie(m);
        }else {
            parent.addMovie(null);
            parent.getInfoLbl().setText(
                    movieNameFld.getText()+
                        " already exists in PMC.");
        }
        Stage sm = (Stage)movieSaveBtn.getScene().getWindow();
        sm.close();
    }
    
    @FXML
    private void handleCancelMovieButton(ActionEvent e){
        Stage cm = (Stage)movieCancelBtn.getScene().getWindow();
        cm.close();
    }
    
    void setMovieToEdit(Movie selected){
        this.selected = selected;
        if(selected != null){
            movieNameFld.setText(selected.getName());
            moviePersonalRatingFld.setText(String.valueOf(selected.getPersonalRating()));
            movieImdbRatingFld.setText(String.valueOf(selected.getImdbRating()));
            movieLastviewFld.setText(selected.getLastview());
            movieFilelinkFld.setText(selected.getFilelink());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        createComboBoxItems();
    }
}