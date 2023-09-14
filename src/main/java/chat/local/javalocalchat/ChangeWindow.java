package chat.local.javalocalchat;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Change window process controller class
 * @author Infobezdar'
 * @version 1.0
 */
public class ChangeWindow {

    // Field will be removed soon
    public static String styleName = "dark_DS";

    /**
     * Window remove and create procedure
     * @param lastStageRoot - element of the last stage
     * @param sendBack - flag to send to server
     * @param windowName - name of new window
     */
    public void changeWindowTo(Pane lastStageRoot, String windowName, boolean sendBack) {
        Stage lastStage = (Stage) lastStageRoot.getScene().getWindow();
        try {
            // Loading new stage
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowName));
            loader.load();
            // Customizing new stage
            Stage newStage = new Stage();
            Parent newStageRoot = loader.getRoot();
            newStage.setScene(new Scene(newStageRoot));
            newStage.setTitle("Secret Chat №1");
            newStage.setResizable(false);
            // Send back to server
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if (sendBack) {
                        Client.sendMessage("back");
                    }
                    System.exit(0);
                }
            });
            // Applying styles to a scene
            Scene scene = newStage.getScene();
            String stylesheet = getClass().getResource("Registration_" + styleName + ".css").toExternalForm();

            if (windowName.equals("Chat.fxml")) {
                stylesheet = getClass().getResource("Chat_" + styleName + ".css").toExternalForm();
            }

            scene.getStylesheets().add(stylesheet);
            newStage.show();

            lastStage.close();

        } catch (IOException e) {
            ExceptionBox.createExceptionBox(lastStageRoot, "    Can not find required system file");
        }
    }

    /**
     * Overloaded window remove and create procedure
     * @param lastStageRoot - element of the last stage
     * @param windowName - name of new window
     */
    public void changeWindowTo(Pane lastStageRoot, String windowName) {
        changeWindowTo(lastStageRoot, windowName, false);
    }
}