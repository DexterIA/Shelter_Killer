package sample.newUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import sample.Connection.Conn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {
    public TextField edit1;
    public TextField edit2;
    private Stage primaryStage;

    public void setStage(Stage st) {
        primaryStage = st;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addClick(ActionEvent actionEvent) {
        if ((!edit1.getText().equals("")) && (!edit2.getText().equals(""))) {
            try {
                Conn conn = new Conn();
                if (conn.findUser(edit1.getText())) {
                    Dialogs.create()
                            .owner(primaryStage)
                            .title("Внимание!")
                            .masthead("Не верный логин!")
                            .message("Пользователь с таким именем уже существует")
                            .showError();
                } else {
                    conn.addUser(edit1.getText(), edit2.getText());
                    primaryStage.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
