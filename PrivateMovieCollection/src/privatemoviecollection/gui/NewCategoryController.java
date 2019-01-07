package privatemoviecollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DKE
 */
public class NewCategoryController implements Initializable{
    
    private PrivateMovieCollectionController parent;
    @FXML
    private TextField nameCategoryFld;
    @FXML
    private Button saveCatBtn;
    @FXML
    private Button cancelCatBtn;
    
    public void setParentWindowController(PrivateMovieCollectionController parent){
        this.parent = parent;
    }
    
    @FXML
    private void saveCategory(ActionEvent e){
        parent.addCategory(nameCategoryFld.getText());
        Stage sc = (Stage)saveCatBtn.getScene().getWindow();
        sc.close();
    }
    
    @FXML
    private void cancelCategory(ActionEvent e){
        Stage cc = (Stage)cancelCatBtn.getScene().getWindow();
        cc.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}