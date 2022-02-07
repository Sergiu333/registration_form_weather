package sample;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image1;

    @FXML
    private Button SingIn;

    @FXML
    private ImageView image2;

    @FXML
    private Button SingUpBizz;

    @FXML
    private Text TextSingUp;

    @FXML
    private Button SingUpEnter;

    @FXML
    private TextField SingUpName;

    @FXML
    private TextField SingUpLogin;

    @FXML
    private TextField SingUpCantry;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio1;

    @FXML
    private PasswordField SingUpPassword;

    @FXML
    private TextField SingUpLastName;

    @FXML
    void initialize() {



        SingUpEnter.setOnAction(actionEvent -> {

            signUpNewUser();


            SingUpEnter.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

        SingIn.setOnAction(actionEvent -> {
            SingIn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstname = SingUpName.getText();
        String lastname = SingUpLastName.getText();
        String username = SingUpLogin.getText();
        String password = SingUpPassword.getText();
        String location = SingUpCantry.getText();
        String gender = "";
            if(radio1.isSelected())
                gender = "M";
            else
                gender = "F";
            User user = new User(firstname, lastname, username, password, location, gender);


        dbHandler.SignUpUser(user);




    }
}
