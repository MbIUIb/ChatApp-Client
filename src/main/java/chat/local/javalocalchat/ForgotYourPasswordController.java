package chat.local.javalocalchat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

public class ForgotYourPasswordController {

    private final ChangeWindow  ChangeWindow= new ChangeWindow();

    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9_]");

    @FXML
    private Button backButton;

    @FXML
    private Button confirmLoginButton;

    @FXML
    private TextField loginField;

    @FXML
    private Pane sideBackground;

    @FXML
    void initialize() {

        TextFieldLimiter.addTextLimiter(loginField, 40);


        backButton.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml"));


        confirmLoginButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty()) {

                if (LOGIN_PATTERN.matcher(loginField.getText()).find()) {

                    Client.setUsername(loginField.getText().trim());

                    try {

                        Client.startClient();

                        Client.sendMessage(Client.getUsername());
                        Client.sendMessage("password_recovery" +
                                "|" + Client.getUsername());

                        String answer = Client.waitMessage();
                        if (answer.equals("begin_password_recovery")) {
                            ChangeWindow.changeWindowTo(sideBackground, "New_password.fxml", true);
                        } else {
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "                   Incorrect login");
                            Client.closeEverything();
                        }
                    } catch (IOException e) {
                        Client.closeEverything();
                        ExceptionBox.createExceptionBox(sideBackground,
                                "        Unable to connect to server" +
                                        "\n         Please try again later");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "                    Invalid Login");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}