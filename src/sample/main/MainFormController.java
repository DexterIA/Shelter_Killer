package sample.main;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;
import sample.admin.Admin;
import sample.Connection.Conn;
import sample.login.Main;
import sample.Room;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    private Stage primaryStage;
    public TableView<Room> FreeRooms;
    public TableColumn<Room, String> numColumn;
    public TableColumn<Room, String> priceColumn;
    public TableColumn<Room, String> typeColumn;
    @FXML
    private Label login;
    static String log;

    public static void setLog(String s) {
        log = s;
    }

    public void setStage(Stage st) {
        primaryStage = st;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setText("Вход выполнен : " + log);

        numColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> p) {
                return p.getValue().numProperty();
            }
        });
        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> p) {
                return p.getValue().priceProperty();
            }
        });
        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> p) {
                return p.getValue().typeProperty();
            }
        });

        try {
            Conn conn = new Conn();
            FreeRooms.setItems(conn.getFreeRooms());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void adminClick(ActionEvent actionEvent) {
        if (!log.equals("admin")) {
            Dialogs.create()
                    .owner(primaryStage)
                    .title("Внимание!")
                    .masthead("Доступ запрещён")
                    .message("Данный раздел доступен только админу")
                    .showError();
        } else {
            Admin admin = new Admin();
            Stage stage = new Stage();
            try {
                admin.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void changeUser(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Main main = new Main();
        try {
            main.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.close();
    }
}
