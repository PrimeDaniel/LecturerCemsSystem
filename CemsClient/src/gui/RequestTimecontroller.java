package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This is a Java class that contains a method to get a Label object.
 */
public class RequestTimecontroller {

    @FXML
    private Label label;
   /**
    * The function returns a Label object.
    * 
    * @return A Label object is being returned.
    */
    public Label getLabel() {
        return label;

    }
}