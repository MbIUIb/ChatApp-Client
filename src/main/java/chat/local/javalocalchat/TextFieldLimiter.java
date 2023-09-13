package chat.local.javalocalchat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**Class limits the number of characters in the field
 * @author Infobezdar'
 * @version 1.0
 */
public class TextFieldLimiter {
    /**Class limits the number of characters in the field
     * @param textField - the field to limit
     * @param maxLength - maximum length of the field
     */
    public static void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            /**Class limits the number of characters in the field
             */
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (textField.getText().length() > maxLength) {
                    String textBeforeLimit = textField.getText().substring(0, maxLength);
                    textField.setText(textBeforeLimit);
                }
            }
        });
    }
}