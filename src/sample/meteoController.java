package sample;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class meteoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text meteo;

    @FXML
    private Button getData;

    @FXML
    private Text info;

    @FXML
    private Text temp_1;

    @FXML
    private Text temp_2;

    @FXML
    private Text temp_3;

    @FXML
    private Text temp_4;

    @FXML
    private ImageView img;

    @FXML
    public TextField city;

    @FXML
    public void initialize() {
        // При нажатии на кнопку
        getData.setOnAction(event -> {
            // Получаем данные из текстового поля
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) { // Если данные не пустые
                // Получаем данные о погоде с сайта openweathermap
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=3cfcc52d931cf5dbabce559462b81065&units=metric");

                if (!output.isEmpty()) { // Нет ошибки и такой город есть
                    JSONObject obj = new JSONObject(output);
                    // Обрабатываем JSON и устанавливаем данные в текстовые надписи
                    temp_1.setText("Temperatura: " + obj.getJSONObject("main").getDouble("temp"));
                    temp_2.setText("Se simte ca: " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_3.setText("Maximul: " + obj.getJSONObject("main").getDouble("temp_max"));
                    temp_4.setText("Minimul: " + obj.getJSONObject("main").getDouble("temp_min"));
                    //pemp_5.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }System.out.println(getUserCity);
        });
    }


    // Обработка URL адреса и получение данных с него
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println("Такой город был  не найден!");

            

        }
        return content.toString();
    }


}