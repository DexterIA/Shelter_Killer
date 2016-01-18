package sample.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Connection.Conn;
import sample.main.MainForm;
import sample.main.MainFormController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Stage primaryStage;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text actiontarget;

    @FXML
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        try {
            Conn conn = new Conn();
            String s = conn.checkUser(loginField.getText(), passwordField.getText());
            if (!s.equals("")) {
                MainForm main = new MainForm();
                MainFormController.setLog(s);
                Stage stage = new Stage();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                primaryStage.close();
            } else {
                actiontarget.setText("Не верно!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage st) {
        primaryStage = st;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
