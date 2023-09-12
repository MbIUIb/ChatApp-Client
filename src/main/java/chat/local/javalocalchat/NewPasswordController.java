package chat.local.javalocalchat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class NewPasswordController {

    private final ChangeWindow  ChangeWindow= new ChangeWindow();

    @FXML
    private Button backButton;

    @FXML
    private PasswordField confirmNewPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField secretCodeField;

    @FXML
    private Button setNewPasswordButton;

    @FXML
    private Pane sideBackground;

    @FXML
    void initialize() {

        TextFieldLimiter.addTextLimiter(secretCodeField, 20);
        TextFieldLimiter.addTextLimiter(newPasswordField, 40);
        TextFieldLimiter.addTextLimiter(confirmNewPasswordField, 40);


        backButton.setOnAction(event -> {
            ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml");
            Client.sendMessage("back");
        });


        setNewPasswordButton.setOnAction(event ->{

            if (newPasswordField.getText() != null && !newPasswordField.getText().trim().isEmpty() &&
                confirmNewPasswordField.getText() != null && !confirmNewPasswordField.getText().trim().isEmpty() &&
                secretCodeField.getText() != null && !secretCodeField.getText().trim().isEmpty()) {

                if (newPasswordField.getText().equals(confirmNewPasswordField.getText())) {

                    Client.sendMessage("new_password +" +
                            "|" + Client.getUsername() +
                            "|" + confirmNewPasswordField.getText().trim() +
                            "|" + secretCodeField.getText().trim());

                    String answer = Client.waitMessage();
                    if (answer.equals("successful_password_recovery")) {
                        ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml");
                    } else {
                        ExceptionBox.createExceptionBox(sideBackground,
                                "                 Invalid secret code");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "             Passwords must match");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}