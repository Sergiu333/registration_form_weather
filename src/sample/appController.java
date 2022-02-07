package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class appController extends Controller{


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text textB;

    @FXML
    private Text appUserHello;

    @FXML
    void initialize() {






    }

    private void UserHello(String name) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        user.setUserName(name);

        dbHandler.getUser(user);
        ResultSet result = dbHandler.getUser(user);





    }


}
