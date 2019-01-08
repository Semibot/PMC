package privatemoviecollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    private TextField movieRatingFld;
    @FXML
    private TextField movieLastviewFld;
    @FXML
    private TextField movieFilelinkFld;
    @FXML
    private Button movieSaveBtn;
    @FXML
    private Button movieCancelBtn;
    private PMCLogic bll;
    @FXML
    private ComboBox<Category> genres;
    private Movie selected = null;
    private ListView<Category> categories;
    
    public NewEditMovieController(){
        bll = new PMCLogic();
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
    private void handleSaveMovieButton(ActionEvent e){
        Movie m = new Movie(0, movieNameFld.getText(),
                Integer.parseInt(movieRatingFld.getText()),
                movieLastviewFld.getText(),
                movieFilelinkFld.getText());
        parent.addMovie(m);
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
            movieRatingFld.setText(String.valueOf(selected.getRating()));
            movieLastviewFld.setText(selected.getLastview());
            movieFilelinkFld.setText(selected.getFilelink());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        createComboBoxItems();
    }
}