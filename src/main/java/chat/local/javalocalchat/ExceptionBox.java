package chat.local.javalocalchat;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Exception box controller class
 * @author Infobezdar'
 * @version 1.0
 */
public class ExceptionBox {

    /**
     * Exception creation and animation procedure
     * @param root - element of the stage
     * @param exceptionText - text of the exception
     */
    public static void createExceptionBox(Pane root, String exceptionText) {
        // Creation of exception
        Label exceptionLabel = new Label(exceptionText);
        exceptionLabel.getStyleClass().add("exception-label");

        root.getChildren().add(exceptionLabel);
        exceptionLabel.setTranslateX(150);
        exceptionLabel.setTranslateY(400);
        // Appearance of the exception
        TranslateTransition exLabelTranslationAppear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationAppear.setByY(-100);
        exLabelTranslationAppear.play();
        // Disappearance of the exception
        TranslateTransition exLabelTranslationDisappear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationDisappear.setByY(100);
        exLabelTranslationDisappear.setDelay(Duration.millis(3000));
        exLabelTranslationDisappear.play();
    }
}