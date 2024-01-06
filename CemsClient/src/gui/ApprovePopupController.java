package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

/**
 * this class is responsble for controlling the approve popout screen ' which will loads,
 * when the head department clicks on approve button for time requests.
 */
public class ApprovePopupController implements Initializable {
    @FXML
    private ImageView approveImageView;;
    @FXML
    private Button exitbtn;
    @FXML
    private Label lbltst;

    private Popup popup;
    private String str;
    @Override
    // The `initialize` method is called when the FXML file is
    // loaded and the controller is initialized. In this specific code, the `initialize` method sets
    // the text of the `lbltst` label to the value of the `str` variable. This means that when the
    // popup is displayed, the text of the label will be set to the value of `str`.
    public void initialize(URL location, ResourceBundle resources) {
       lbltst.setText(str);
    }
    /**
     * This function sets the Popup object for the current instance.
     * 
     * @param popup The parameter "popup" is an object of the class "Popup". The method "setPopup" sets
     * the value of the instance variable "popup" to the value passed as the parameter.
     */
    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    /**
     * This Java function sets the value of a string variable.
     * 
     * @param str str is a parameter of type String that is used to set the value of the instance
     * variable "str" in the class.
     */
    public void setStr(String str) {
    	this.str=str;

    }

  

    /**
     * This function hides a popup window when the "exit" button is clicked in a JavaFX application.
     * 
     * @param event The event parameter in the exit() method is an object of the ActionEvent class. It
     * represents the user's action of clicking on the exit button or triggering the exit event in some
     * other way. The event object contains information about the event, such as the source of the
     * event and any additional data associated
     */
    @FXML
    public void exit(ActionEvent event) {
        popup.hide();

    }

  
}
