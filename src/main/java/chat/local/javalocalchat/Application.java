package chat.local.javalocalchat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static chat.local.javalocalchat.ChangeWindow.styleName;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Sign_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Secret Chat №1");
        stage.setResizable(false);
        stage.setScene(scene);
        String stylesheet = getClass().getResource("Registration_" + styleName + ".css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}