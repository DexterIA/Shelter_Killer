package sample.admin;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;
import sample.Connection.Conn;
import sample.Employer;
import sample.newUser.NewUser;
import sample.Room;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    private Stage primaryStage;

    public void setStage(Stage st) {
        primaryStage = st;
    }

    public TableView<Room> Rooms;
    public TableColumn<Room, String> numColumn;
    public TableColumn<Room, String> priceColumn;
    public TableColumn<Room, String> typeColumn;

    public TableView<Employer> employers;
    public TableColumn<Employer, String> tabNoColumn;
    public TableColumn<Employer, String> loginColumn;
    public TableColumn<Employer, String> passColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            Rooms.setItems(conn.getFreeRooms());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tabNoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().tabNoProperty();
            }
        });
        loginColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().loginProperty();
            }
        });
        passColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().passProperty();
            }
        });

        try {
            Conn conn = new Conn();
            employers.setItems(conn.getEmployers());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteUser(ActionEvent actionEvent) {
        try {
            Conn conn = new Conn();
            Optional<String> response = Dialogs.create()
                    .owner(primaryStage)
                    .title("Удаление пользователя")
                    .masthead("Удалить пользователя?")
                    .message("Введите логин пользователя:")
                    .showTextInput("");

            if (response.isPresent()) {
                if (conn.findUser(response.get())) {
                    conn.deleteUser(response.get());
                    update();
                } else {
                    Dialogs.create()
                            .owner(primaryStage)
                            .title("Внимание!")
                            .masthead("Не найден логин!")
                            .message("Не правильно задан логин, либо его не существует")
                            .showError();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        tabNoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().tabNoProperty();
            }
        });
        loginColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().loginProperty();
            }
        });
        passColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employer, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Employer, String> p) {
                return p.getValue().passProperty();
            }
        });

        try {
            Conn conn = new Conn();
            employers.setItems(conn.getEmployers());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void newUser(ActionEvent actionEvent) {
        Stage stage = new Stage();
        NewUser newUser = new NewUser();
        try {
            newUser.start(stage);
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
