package gui;

import java.io.IOException;
import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//hi guys from tamer
// The `public class ConnectToServerScreenController` is a Java class that serves as the controller for
// the ConnectToServerScreen GUI. It contains methods that handle user actions, such as connecting to
// the server, setting up the chat client, and showing the login form. It also contains references to
// various GUI components, such as buttons, text fields, and labels, which are used to interact with
// the user.
public class ConnectToServerScreenController  {
	ChatClient client= null;
	
	@FXML
	private Button sendbtn ;
    @FXML
    private TextField ipTextField;
    @FXML
    private Label wrongIpText;
    @FXML
	/**
	 * the method can connects between the client and server
	 */
    public void Connect(ActionEvent event) throws Exception {
        String ip = ipTextField.getText();
        try {
            	ConnectToServerScreenControllers(ip, 5555);
            client.openConnection();
            wrongIpText.setText("");
            if (client.isConnected()) {
            	showLogin();
            } else {
                System.out.println("Failed to connect to the server.");
            }
        } catch (Exception e) {
        	wrongIpText.setText("wrong ip!!,try again");
        }
    }

    /**
	 * sets up the chatclient for our connection
	 */
	public void ConnectToServerScreenControllers(String host, int port) 
	  {
	    try 
	    {
	    	if (client==null || !client.isConnected())
	    		client= new ChatClient(host, port, this);
	    } 
	    catch(IOException exception) 
	    {
	      System.out.println("Error: Can't setup connection!"+ " Terminating client.");
	      System.exit(1);
	    }
	  }
	/**
	 * shows the login form
	 */
	private void showLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScreen.fxml")); // specify the path to the new fxml file
		Parent parent=null;
		try {
			parent = loader.load();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	    Scene nextScene = new Scene(parent);
	    
	    // Get the new scene's controller and pass the ChatClient instance to it
	    LoginScreenController controller = loader.getController();
	    controller.setClient(this.client,controller);

	    // Get the Stage information
	    Stage window = (Stage) sendbtn.getScene().getWindow();
	    window.setScene(nextScene);
	    window.show();
	}



	
	
}
