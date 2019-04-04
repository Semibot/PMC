package privatemoviecollection.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DKE
 */
public class PMCMediaController implements Initializable{
    
    private PrivateMovieCollectionController parent;
    private String filePath;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button openFileBtn;
    @FXML
    private Button playBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private Slider slider;
    @FXML
    private Slider seekSlider;
    
    public void setParentWindowController(PrivateMovieCollectionController parent){
        this.parent = parent;
    }
    
    @FXML
    private void handleOpenFileBtn(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if(filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            slider.setValue(mediaPlayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener(){
                @Override
                public void invalidated(Observable observable){
                    mediaPlayer.setVolume(slider.getValue() / 100);
                }
            });
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue){
                    seekSlider.setValue(newValue.toSeconds());
                }
            });
            
            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent e){
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });
            
            mediaPlayer.play();
        }
    }
    
    @FXML
    private void playMovie(ActionEvent e){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    @FXML
    private void pauseMovie(ActionEvent e){
        mediaPlayer.pause();
    }
    
    @FXML
    private void stopMovie(ActionEvent e){
        mediaPlayer.stop();
    }
    
    @FXML
    private void exitMovie(ActionEvent e){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            Path dir = FileSystems.getDefault().getPath("./src/images/Openfile.png");
            Image image = new Image(dir.toUri().toURL().toExternalForm());
            openFileBtn.setGraphic(new ImageView(image));
            
            Path dir1 = FileSystems.getDefault().getPath("./src/images/Play-icon.png");
            Image image1 = new Image(dir1.toUri().toURL().toExternalForm());
            playBtn.setGraphic(new ImageView(image1));
            
            Path dir2 = FileSystems.getDefault().getPath("./src/images/Pause-icon.png");
            Image image2 = new Image(dir2.toUri().toURL().toExternalForm());
            pauseBtn.setGraphic(new ImageView(image2));
            
            Path dir3 = FileSystems.getDefault().getPath("./src/images/Stop-icon.png");
            Image image3 = new Image(dir3.toUri().toURL().toExternalForm());
            stopBtn.setGraphic(new ImageView(image3));
        }catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }
}