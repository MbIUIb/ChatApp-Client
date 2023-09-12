package chat.local.javalocalchat;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ExceptionBox {

    public static void createExceptionBox(Pane background, String exceptionText) {

        Label exceptionLabel = new Label(exceptionText);
        exceptionLabel.getStyleClass().add("exception-label");

        background.getChildren().add(exceptionLabel);
        exceptionLabel.setTranslateX(150);
        exceptionLabel.setTranslateY(400);

        TranslateTransition exLabelTranslationAppear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationAppear.setByY(-100);
        exLabelTranslationAppear.play();

        TranslateTransition exLabelTranslationDisappear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationDisappear.setByY(100);
        exLabelTranslationDisappear.setDelay(Duration.millis(3000));
        exLabelTranslationDisappear.play();
    }
}