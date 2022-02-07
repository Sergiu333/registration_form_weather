package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView image1;

    @FXML
    private Button SignUp;

    @FXML
    private ImageView image2;

    @FXML
    private Button Bizz;

    @FXML
    private Text text2;

    @FXML
    private Button Enter;

    @FXML
    public TextField login;
    @FXML
    private Text textErroir;

    @FXML
    void initialize() {
        Enter.setOnAction(actionEvent -> {
            String  logintext = login.getText().trim();
            String  passtext = password.getText().trim();

            if(!logintext.equals("") && !passtext.equals("")){
                loginUser(logintext, passtext);
            }else System.out.println("login and password is empty");

        });


        SignUp.setOnAction(event ->{

            openNewScen("/sample/SignUp.fxml");

        });

        Bizz.setOnAction(event ->{
            openNewScen("/sample/meteo.fxml");



        });

    }

    private void loginUser(String logintext, String passtext) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(logintext);
        user.setPassword(passtext);
        dbHandler.getUser(user);
        ResultSet result = dbHandler.getUser(user);

        int counter =0;
        while(true){
            try {
                if (!result.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;

        }
        if(counter>=1){

            openNewScen("/sample/app.fxml");
           // System.out.println(appController.getsergiu());

        }else{
            Shake userLoginAnim = new Shake(login);
            Shake userPassAnim = new Shake(password);
            Shake text = new Shake(textErroir);
            textErroir.setText("Error in autentification !");
            // userLoginAnim.playAnim();  //is stop
            // userPassAnim.playAnim();   //is stop
            text.playAnim();

        }
    }
    public void openNewScen(String window){

        SignUp.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }


}
