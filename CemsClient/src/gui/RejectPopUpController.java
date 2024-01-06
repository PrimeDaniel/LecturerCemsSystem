package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * This is a Java class for a reject pop-up window with an image, label, and exit button.
 */
public class RejectPopUpController implements Initializable {
    @FXML
    private ImageView rejectImageView;;
    @FXML
    private Button exitbtn;
    @FXML
    private Label lbltst;
    private Popup popup;
    private String str;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * This function sets the popup for a given object.
     * 
     * @param popup The parameter "popup" is an object of the class "Popup". This method sets the value
     * of the instance variable "popup" to the value passed as the parameter.
     */
    public void setPopup(Popup popup) {
    	
        this.popup = popup;
    }

    /**
     * This function hides a popup window when the "exit" button is clicked in a JavaFX application.
     * 
     * @param event The event parameter in the exit() method is an object of the ActionEvent class. It
     * represents the user's action of clicking on the exit button or triggering the exit event.
     */
    @FXML
    public void exit(ActionEvent event) {
        popup.hide();

    }
}
