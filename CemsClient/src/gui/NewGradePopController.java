/**
 * The NewGradePopController class is a JavaFX controller for a pop-up window that displays a new grade
 * and course name to a student.
 */
package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
/**
 * this is popout widow it will show to the student the new grade recieved and course name
 */
public class NewGradePopController {

    @FXML
    private Text gradeTxt;
    
   /**
    * This Java function sets the text of a gradeTxt object to a given string.
    * 
    * @param string The parameter "string" is a String type variable that is passed as an argument to
    * the method "setMes". It is used to set the text of a gradeTxt object.
    */
    public void setMes(String string) {
 
    	gradeTxt.setText(string);
    

    }

   /**
    * The function returns the current instance of the NewGradePopController class.
    * 
    * @return An instance of the class `NewGradePopController` is being returned.
    */
    public NewGradePopController getController() {
		return this;
	}
}
