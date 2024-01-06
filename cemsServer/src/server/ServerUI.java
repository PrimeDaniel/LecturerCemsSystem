package server;

import gui.ServerStartScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerUI extends Application {

    @Override
	public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ServerStartScreen.fxml"));
            Parent root = loader.load();
            ServerStartScreenController controller = loader.getController();
            EchoServer server = new EchoServer(5555);
            controller.setServer(server);
            server.setController(controller);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
