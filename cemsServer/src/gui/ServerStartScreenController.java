package gui;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import logic.LoggedUsers;
import server.EchoServer;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerStartScreenController implements Initializable {

	private EchoServer server;


	@FXML
	private Button startBtn;
	@FXML
	private Button exitBtn;
	@FXML
	private TableColumn<LoggedUsers, Integer> id;
	@FXML
	private TableColumn<LoggedUsers, String> firstName;
	@FXML
	private TableColumn<LoggedUsers, String> lastName;
	@FXML
	private TableColumn<LoggedUsers, String> userName;
	@FXML
	private TableColumn<LoggedUsers, Integer> role;
	@FXML
	private TableView<LoggedUsers> table;
    @FXML
    private TextField serveripfield;
	@FXML
    private TextField serverportfield;
	@FXML
	private Circle colorCircle;
	@FXML
    private Button stopBtn;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		role.setCellValueFactory(new PropertyValueFactory<>("role"));
		updateServerip();
		serveripfield.setFocusTraversable(false);
		serveripfield.setMouseTransparent(true);
		serveripfield.setEditable(false);

	

	}

	public void UpadteOnlineUsers(ArrayList<LoggedUsers> usersArray) {
		table.getItems().clear();
		table.getItems().addAll(usersArray);

	}

	@FXML
	public void startServer() {
		String port="5555";

			if (server != null  ) {
				try {
					//set port or use defult 5555
					if(serverportfield.getText().trim().isEmpty()){
						port="5555";
					}
					else{
						port =serverportfield.getText();
						
					}
					try{
						server.setPort(Integer.parseInt(port));
					} catch (RuntimeException e) {
						e.printStackTrace();
					}

                // Start listening for connections
					server.listen();
				} catch (IOException e) {
					e.printStackTrace();
				}
				colorCircle.setFill(Color.web("#21ff25"));;
			} else {
				System.out.println("ERROR - Server is not initialized!");
				 colorCircle.setFill(Color.RED);
			}
			
	}
	@FXML
    private void updateServerip(){
		try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            serveripfield.setText(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

	}


	public void setServer(EchoServer server) {
		this.server = server;
	}

	@FXML
	public void stopServer() {
		server.stopListening();
		colorCircle.setFill(Color.web("RED"));;
	}


	@FXML
	public void exitApplication() {
		System.exit(0);
	}

}
