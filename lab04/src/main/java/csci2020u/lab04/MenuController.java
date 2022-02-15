package csci2020u.lab04;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.event.HyperlinkEvent;
import java.text.DecimalFormat;
import java.text.MessageFormat;

public class MenuController {

    @FXML private Label response;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private DatePicker dob;

    private String phoneFormat = "(\\d{3})(\\d{3})(\\d+)";
    private String phoneOutput = "($1)-$2-$3";


    @FXML
    protected void onRegisterButtonClick() {
        //ToDo: Create everything
        response.setText("");
        EmailValidator ev = new EmailValidator(false, false, DomainValidator.getInstance());
        if(!ev.isValid(email.getText())){
            response.setText("Invalid Email Address!");
        }else{
            System.out.printf("Username %s | Password: %s | Email: %s | Phone #: %s | Date Of Birth: %s\n", username.getText(), password.getText(), email.getText(), phone.getText(), dob.getValue());
            response.setText("Registered!");
        }

    }

    @FXML
    public void onPhoneAction(ActionEvent actionEvent) {
        String fString = phone.getText().replaceFirst(phoneFormat, phoneOutput);
        phone.setText(fString);
    }
}