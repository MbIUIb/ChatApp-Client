package chat.local.javalocalchat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Sign in window controller class
 * @author Infobezdar'
 * @version 1.0
 */
public class SignInController {

    private final ChangeWindow  ChangeWindow= new ChangeWindow();

    /**
     * Field regular expression for login
     */
    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9_]");

    /**
     * Field regular expression for IP-address
     */
    private static final Pattern IP_PATTERN = Pattern.compile(
            "^(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                  "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                  "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                  "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    );

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField loginField;

    @FXML
    private TextField IPAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private Pane sideBackground;

    @FXML
    private Button signInButton;

    /**
     * Window initialization and control procedure
     */
    @FXML
    void initialize() {

        // Adding a limiter (40) to the password field
        TextFieldLimiter.addTextLimiter(passwordField, 40);
        // Adding a limiter (40) to the login field
        TextFieldLimiter.addTextLimiter(loginField, 40);

        // The event listener for the registration button opens a Sign_up window
        registrationButton.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Sign_up.fxml"));

        // The event listener for the forgot password button opens a Forgot_your_password window
        forgotPassword.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Forgot_your_password.fxml"));

        // The event listener for the enter button can open a Chat window
        signInButton.setOnAction(event ->{

            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() &&
                passwordField.getText() != null && !passwordField.getText().trim().isEmpty() &&
                IPAddressField.getText() != null && !IPAddressField.getText().trim().isEmpty()) {

                if (LOGIN_PATTERN.matcher(loginField.getText()).find()) {

                    if (IP_PATTERN.matcher(IPAddressField.getText()).find()) {

                        Client.setUsername(loginField.getText().trim());
                        Client.setPassword(passwordField.getText().trim());
                        Client.setIPAddress(IPAddressField.getText().trim());

                        try {
                            // Start connection to server
                            Client.startClient();
                            // Send enter to chat request to server
                            Client.sendMessage(Client.getUsername());
                            Client.sendMessage(
                                    "sign_in" +
                                    "|" + Client.getUsername() +
                                    "|" + Client.getPassword()
                            );

                            String answer = Client.waitMessage();
                            if (answer.equals("successful_sign_in")) {
                                ChangeWindow.changeWindowTo(sideBackground, "Chat.fxml");

                            } else {
                                ExceptionBox.createExceptionBox(sideBackground,
                                        "         Incorrect login or password");
                                Client.closeEverything();
                            }
                        } catch (IOException e) {
                            Client.closeEverything();
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "        Unable to connect to server" +
                                            "\n             Please try again later");
                        }
                    } else {
                        ExceptionBox.createExceptionBox(sideBackground, "                Invalid IP Address");
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